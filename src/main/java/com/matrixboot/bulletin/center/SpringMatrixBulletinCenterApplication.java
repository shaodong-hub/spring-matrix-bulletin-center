package com.matrixboot.bulletin.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author shishaodong
 */
@EnableAsync
@EnableCaching
@EnableScheduling
@EnableFeignClients
@EnableMongoAuditing
@SpringBootApplication
public class SpringMatrixBulletinCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMatrixBulletinCenterApplication.class, args);
    }

}
