package com.base.admin.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Pattern;

/**
 * 密码工具类
 */
public class PasswordUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * 对密码进行加密
     *
     * @param rawPassword 原始密码
     * @return 加密后的密码
     */
    public static String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    /**
     * 验证明文密码与加密密码是否匹配
     *
     * @param rawPassword    原始密码
     * @param encodedPassword 加密后的密码
     * @return 是否匹配
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 检查密码强度
     * 密码必须至少8位，包含数字、小写字母、大写字母和特殊字符中的至少3种
     *
     * @param password 密码
     * @return 是否满足强度要求
     */
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        int strength = 0;
        
        // 检查是否包含数字
        if (Pattern.compile("[0-9]").matcher(password).find()) {
            strength++;
        }
        
        // 检查是否包含小写字母
        if (Pattern.compile("[a-z]").matcher(password).find()) {
            strength++;
        }
        
        // 检查是否包含大写字母
//        if (Pattern.compile("[A-Z]").matcher(password).find()) {
//            strength++;
//        }
        
        // 检查是否包含特殊字符
        if (Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]").matcher(password).find()) {
            strength++;
        }
        
        return strength >= 3;
    }
}