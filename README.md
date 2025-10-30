# Java后端基座项目
基于Spring Boot 3.x的企业级后端基座，包含用户管理、角色管理、附件管理等基础功能，支持快速扩展业务模块。

## 环境要求
- JDK 17+
- MySQL 8.0+
- Redis 6.x+
- Maven 3.6+

## 启动步骤
1. 执行 `base-system/src/main/resources/db/init.sql` 初始化数据库
2. 修改 `base-admin/src/main/resources/application-dev.yml` 中的数据库、Redis配置
3. 运行 `base-admin/src/main/java/com/xxx/admin/AdminApplication.java`
4. 访问接口文档：http://localhost:8080/doc.html
5. 初始账号：admin，密码：123456（密码在SQL脚本中已加密，明文为123456）

## 模块说明
- base-parent：父模块，统一管理依赖版本
- base-common：公共组件，包含全局配置、工具类等
- base-system：系统管理，包含用户、角色管理
- base-file：附件管理，支持本地/OSS存储
- base-admin：启动模块，聚合所有子模块