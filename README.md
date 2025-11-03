# 简化版Spring Boot项目

这是一个最简化的Spring Boot项目，用于演示REST API的基本用法和数据库操作。

## 环境要求

- JDK 17+
- MySQL 5.7+
- Maven 3.6+

## 数据库配置

1. 创建数据库：zm
2. 创建表：
```sql
CREATE TABLE `sys_user` (
  `ID` bigint NOT NULL COMMENT '用户ID',
  `USERNAME` varchar(50) NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(100) NOT NULL COMMENT '密码（加密存储）',
  `NAME` varchar(50) DEFAULT NULL COMMENT '姓名',
  `STATUS` tinyint NOT NULL DEFAULT '1' COMMENT '状态（0-禁用，1-正常）',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_USERNAME` (`USERNAME`) COMMENT '用户名唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统用户表';
```

3. 确保MySQL服务运行在localhost:3306，用户名为root，密码为123456

## 启动步骤

1. 运行以下命令构建项目：
   ```
   mvn clean package
   ```

2. 运行生成的JAR文件：
   ```
   java -jar base-admin/target/base-admin-1.0.0.jar
   ```

## API接口

- GET /user/ - 获取所有用户
- GET /user/{id} - 根据ID获取用户
- POST /user/ - 创建用户
- PUT /user/ - 更新用户
- DELETE /user/{id} - 删除用户

## 项目结构

- base-project: 根目录，包含父POM
- base-admin: Spring Boot应用程序模块
  - controller: 控制器层
  - service: 服务层
  - mapper: 数据访问层（使用MyBatis XML配置）
  - entity: 实体类
  - dto: 数据传输对象