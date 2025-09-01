package com.example.mall.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.mall.dto.MemberDTO;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class APILoginSuccessHandler implements AuthenticationSuccessHandler{
	
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)throws IOException,ServletException {

	log.info("-".repeat(20));
	log.info(authentication);
	log.info("-".repeat(20));
	
	MemberDTO memberDTO = (MemberDTO) authentication.getPrincipal();
	
	Map<String, Object> claims = memberDTO.getClaims();
	
	claims.put("accessToken", "");
	claims.put("refreshToken", "");
	
	Gson gson = new Gson();
	
	String jsonStr = gson.toJson(claims);
	
	PrintWriter printWriter = response.getWriter();
	printWriter.println(jsonStr);
	printWriter.close();
	}
}
