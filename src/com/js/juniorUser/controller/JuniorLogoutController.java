package com.js.juniorUser.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.js.Action;
import com.js.Result;

public class JuniorLogoutController implements Action {

	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Result result = new Result();
		
		// 세션 무효화하여 로그아웃 처리
		req.getSession().invalidate();
		
		// 쿠키 값 제거
		if (req.getHeader("Cookie") != null) {
			Cookie[] cookies = req.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userEmail")) {
					cookie.setMaxAge(0); // 쿠키 만료 시간 설정 (0초로 설정하여 제거)
					resp.addCookie(cookie);
				}
				if (cookie.getName().equals("userPassword")) {
					cookie.setMaxAge(0); // 쿠키 만료 시간 설정 (0초로 설정하여 제거)
					resp.addCookie(cookie);
				}
			}
		}
		
		result.setRedirect(true);
		result.setPath(req.getContextPath() + "/login.juniorUser");
		System.out.println("로그아웃 완료");
		return result;
	}

}
