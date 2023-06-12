/* 프론트 컨트롤러에서 필터링되어 넘어와서 로그인을 확인해주는 컨트롤러 */

package com.js.juniorUser.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.js.Action;
import com.js.Result;

public class JuniorLoginController implements Action {

	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// 사용자 이메일과 비밀번호 변수 초기화
		String userEmail = null;
		String userPassword = null;
		
		// 결과 객체 생성
		Result result = new Result();
		
		// 요청 헤더에서 쿠키 확인
		if (req.getHeader("Cookie") != null) {
			// 모든 쿠키 가져오기
			Cookie[] cookies = req.getCookies();
			
			// 쿠키 배열 반복 처리
			for (Cookie cookie : cookies) {
				// userEmail 쿠키 값 가져오기
				if (cookie.getName().equals("userEmail")) {
					userEmail = cookie.getValue();
				}
				// userPassword 쿠키 값 가져오기
				if (cookie.getName().equals("userPassword")) {
					userPassword = cookie.getValue();
				}
			}
		}
		
		if (userEmail != null) {
			req.setAttribute("userEmail", userEmail);
			req.setAttribute("userPassword", userPassword);
			
			// 로그인 성공 시 이동할 경로 설정
			result.setPath("JuniorLoginOk.juniorUser");
		} else {
			// 로그인 실패 시 이동할 경로 설정
			result.setPath("templates/makepage-hsw/gosuLogin.jsp");
		}
		
		return result;
	}

}
