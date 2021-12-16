# log4j2BugTest
2021年12月9日，log4j2的一个核弹级漏洞响彻技术圈（https://www.oschina.net/news/172999（ps：文中提及补丁已被相关安全团队验证仍存漏洞））。本次将使用java模拟log4j2日志打印的lookup调用rmi的漏洞以及验证一种解决此漏洞的方式（特定环境下执行）
