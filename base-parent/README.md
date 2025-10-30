# base-parent 父模块说明
本模块为项目的Maven父POM，主要作用：
1. 统一管理所有子模块的依赖版本，避免版本冲突
2. 声明子模块关系（base-common、base-system、base-file、base-admin）
3. 配置通用Maven插件（如编译插件）

所有子模块必须继承本模块，无需在子模块中重复声明依赖版本。