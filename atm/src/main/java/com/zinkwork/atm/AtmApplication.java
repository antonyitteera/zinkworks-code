package com.zinkwork.atm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.zinkwork.atm.entity.AtmEntity;
import com.zinkwork.atm.entity.UserEntity;
import com.zinkwork.atm.repository.UserRepository;

@EnableResourceServer
@SpringBootApplication
public class AtmApplication implements CommandLineRunner {
	
	@Autowired
	UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(AtmApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		UserEntity userEntity= new UserEntity();
	
		
	}

}
