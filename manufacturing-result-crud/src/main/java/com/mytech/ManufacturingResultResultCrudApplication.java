package com.mytech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author `<a href="mailto:qiang.wang@1020@gmail.com">qiang</a>`
 * @date 2021-09-18
 * @description :
 */
@SpringBootApplication
@EntityScan("com.mytech.domain")
public class ManufacturingResultResultCrudApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManufacturingResultResultCrudApplication.class,args);
    }
}
