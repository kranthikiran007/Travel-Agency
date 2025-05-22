package com.kk.util;
 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 
public class EncodePassword {
 
	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		System.out.println(bCryptPasswordEncoder.encode("kk@123"));
		System.out.println(bCryptPasswordEncoder.encode("gg@123"));
 
	}
 
}