package com.file.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableFeignClients
public class FileServiceApplication 
{
	public static void main( String[] args )
    {
    	SpringApplication.run(FileServiceApplication.class, args);
    }
}
