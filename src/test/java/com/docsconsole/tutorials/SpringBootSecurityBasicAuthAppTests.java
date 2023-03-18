package com.docsconsole.tutorials;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Base64;

@SpringBootTest
class SpringBootSecurityBasicAuthAppTests {

	@Test
	void contextLoads() {
	}

	public static void main(String[] args) {
		new BCryptPasswordEncoder(8).encode("user" + ":" + "password");
		String encoding = Base64.getEncoder().encodeToString(("user" + ":" + "password").getBytes());
		String authHeader = "Basic " + encoding;
		System.out.println(new BCryptPasswordEncoder(8).encode("user" + ":" + "password"));
	}
}
