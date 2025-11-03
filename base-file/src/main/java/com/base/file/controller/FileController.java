package com.base.file.controller;

import com.xxx.common.model.R;
import com.xxx.file.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/file")
@Tag(name = "附件管理", description = "文件上传/下载/删除")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    @Operation(summary = "单文件上传")
    public R<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        String url = fileService.upload(file);
        return R.ok(url);
    }

    @GetMapping("/download/{fileId}")
    @Operation(summary = "文件下载")
    public void download(@PathVariable String fileId, HttpServletResponse response) throws IOException {
        fileService.download(fileId, response);
    }

    @DeleteMapping("/{fileId}")
    @Operation(summary = "删除文件")
    public R<Void> delete(@PathVariable String fileId) {
        fileService.delete(fileId);
        return R.ok();
    }
}