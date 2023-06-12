/*프론트 컨트롤러에서 필터링되어 넘어와서 이메일 확인해주는 컨트롤러*/
package com.js.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.js.Action;
import com.js.Result;
import com.js.user.dao.UserDAO;

public class CheckEmailOkController implements Action {

	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html;charset=utf-8");
		// 이메일 파라미터 가져오기
		String userEmail = req.getParameter("userEmail");
		// UserDAO 객체 생성
		UserDAO userDAO = new UserDAO();
		// 이메일 중복 확인
		boolean check = userDAO.selectEmail(userEmail) == null;
		// 결과를 담을 JSON 객체 생성
		JSONObject result = new JSONObject();
		
		try {
			result.put("check", check);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		PrintWriter out = resp.getWriter();
		out.print(result.toString());
		out.close();
		
		return null;
	}

}
