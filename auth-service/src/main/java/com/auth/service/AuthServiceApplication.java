package com.auth.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class AuthServiceApplication 
{
	public static void main( String[] args )
    {
    	SpringApplication.run(AuthServiceApplication.class, args);
    	System.out.println(new BCryptPasswordEncoder().encode("ashik"));

    }
}
