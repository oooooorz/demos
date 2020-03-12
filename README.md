# demos
存放一些demo项目

# rptdemo
这是一个报表demo，用于验证方案的可行性

# 功能
对于一些仅用sql就能搞定的报表，不需要再写java代码，只需在RtpMapper.xml文件中编写sql语句并在application.yml中配置即可

# 操作步骤
1. 使用intellij idea导入demos下的rptdemo项目
2. 在mysql数据中执行项目下的test.sql文件，生成项目对应的表
3. 修改application.yml中数据库的连接信息，包括库名，用户名和密码为本机所用数据库信息，密码可用util包下的EncryptUtil加密，在test测试类中执行就行
4. 启动项目，验证结果
