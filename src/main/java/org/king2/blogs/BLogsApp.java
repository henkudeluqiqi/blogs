package org.king2.blogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 启动类
 *
 * @author 鹿七七sama
 */
@SpringBootApplication
public class BLogsApp {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(BLogsApp.class);
    }
}
