/*프론트 컨트롤러에서 필터링되어 넘어와서 로그인을 확인해주는 컨트롤러*/
package com.js.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.js.Action;
import com.js.Result;

public class LoginController implements Action {

	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String userEmail = null, userPassword = null;
		Result result = new Result();
		boolean autoLogin = false;
		
		// 쿠키를 검사하여 사용자의 이메일, 비밀번호, 자동 로그인 여부를 확인
		if(req.getHeader("Cookie") != null){
			Cookie[] cookies = req.getCookies();
			
			for(Cookie cookie: cookies){
				if(cookie.getName().equals("userEmail")) {
					userEmail = cookie.getValue();
				}
				if(cookie.getName().equals("userPassword")) {
					userPassword = cookie.getValue();
				}
				if(cookie.getName().equals("autoLogin")) {
					autoLogin = Boolean.valueOf(cookie.getValue());
				}
			}
		}
		
		// 쿠키에 저장된 사용자 정보가 있을 경우, 해당 정보를 로그인 처리에 사용
		if(userEmail != null) {
			req.setAttribute("userEmail", userEmail);
			req.setAttribute("userPassword", userPassword);
			result.setPath("loginOk.user");
		}else {
			// 쿠키에 저장된 사용자 정보가 없을 경우, 로그인 페이지로 이동
			if(autoLogin) {
				req.setAttribute("autoLogin", autoLogin);
			}
			result.setPath("templates/makepage-hsw/login.jsp");
		}
		
		return result;
	}

}
