package edu.tus.authenticationservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
//@EnableResourceServer
@EnableAuthorizationServer
public class AuthenticationServiceApplication {
	
	/*@RequestMapping("/auth/user")
	public Map<String, Object> user(OAuth2Authentication user)
	{
		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put("user", user.getUserAuthentication().getPrincipal());
		userInfo.put("authorities", AuthorityUtils.authorityListToSet(
				user.getUserAuthentication().getAuthorities()));
		return userInfo;
	}
	*/
	@RequestMapping("/auth/user")
	public Map<String, Object> user(UsernamePasswordAuthenticationToken user)
	{
		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put("user", user.getPrincipal());
		userInfo.put("authorities", AuthorityUtils.authorityListToSet(
				user.getAuthorities()));
		return userInfo;
	}
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServiceApplication.class,args);

	}


}
