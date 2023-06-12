/* 프론트 컨트롤러에서 필터링되어 넘어와서 회원가입을 확인해주는 컨트롤러 */

package com.js.juniorUser.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.js.Action;
import com.js.Result;
import com.js.juniorUser.dao.JuniorUserDAO;
import com.js.juniorUser.domain.JuniorUserDTO;
import com.js.juniorUser.domain.JuniorUserDTO2;
import com.js.user.domain.UserVO;

public class JuniorJoinOkController implements Action {
	
	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// JuniorUserDAO 객체 생성
		JuniorUserDAO juniorUserDAO = new JuniorUserDAO();
		
		// JuniorUserDTO 및 JuniorUserDTO2 객체 생성
		JuniorUserDTO juniorUserDTO = new JuniorUserDTO();
		JuniorUserDTO2 juniorUserDTO2 = new JuniorUserDTO2();
		
		// 결과 객체 생성
		Result result = new Result();
		
		// 사용자가 입력한 회원가입 정보를 JuniorUserDTO2에 저장(기본 일반 유저 정보)
		juniorUserDTO2.setUserEmail(req.getParameter("userEmail"));
		juniorUserDTO2.setUserPassword(req.getParameter("userPassword"));
		juniorUserDTO2.setUserName(req.getParameter("userName"));
		juniorUserDTO2.setUserAddress(req.getParameter("userAddress"));
		juniorUserDTO2.setUserPhonenumber(req.getParameter("userPhonenumber"));
		
		// 사용자가 입력한 이력서 정보를 JuniorUserDTO에 저장(주니어 유저 정보)
		juniorUserDTO.setUserResume(req.getParameter("userResume"));
		
		// 사용자가 입력한 경력 정보를 JuniorUserDTO에 저장(주니어 유저 정보)
		String userCareerYearsParam = req.getParameter("userCareerYears");
		if (userCareerYearsParam != null && !userCareerYearsParam.isEmpty()) {
		    juniorUserDTO.setUserCareerYears(Integer.parseInt(userCareerYearsParam));
		}
		
		// 회원가입 정보를 데이터베이스에 저장(이후 두 정보를 합쳐 주니어 유저 정보로 사용)
		juniorUserDAO.insert(juniorUserDTO, juniorUserDTO2);
		
		// 결과 설정
		result.setRedirect(true);
		result.setPath(req.getContextPath() + "/gosuLogin.juniorUser");
		
		System.out.println("주니어 들어옴");
		
		return result;
	}
}
