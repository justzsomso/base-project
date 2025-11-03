# 简化版Spring Boot项目

这是一个最简化的Spring Boot项目，用于演示REST API的基本用法。

## 启动步骤

1. 运行以下命令构建项目：
   ```
   mvn clean package
   ```

2. 运行生成的JAR文件：
   ```
   java -jar base-admin/target/base-admin-1.0.0.jar
   ```

3. 访问API端点：
   ```
   curl http://localhost:8080/test
   ```

## 项目结构

- base-project: 根目录，包含父POM
- base-admin: Spring Boot应用程序模块