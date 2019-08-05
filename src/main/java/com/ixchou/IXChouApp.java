package com.ixchou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/04 18:28<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 主程序启动类<br/>
 * <b>Description</b>:
 */
@SpringBootApplication
@MapperScan("com.ixchou.mappings")
@EnableTransactionManagement
public class IXChouApp {

    public static void main(String[] args) {
        SpringApplication.run(IXChouApp.class, args);
    }
}
