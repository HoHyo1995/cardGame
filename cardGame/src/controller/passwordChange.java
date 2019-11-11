package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.*;
import vo.Member;


@WebServlet("/passwordChange")
public class passwordChange extends HttpServlet {
	Member member;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// json타입으로 응답한다고 선언한다
		response.setContentType("application/json;charset=utf-8");
		// 리턴받을값 선언
		int successNum = 0;
		// 객체 생성 후에 html에서 넘겨준 값 받고 확인한다.
		member = new Member();
		member.setMemberId(request.getParameter("memberId"));
		member.setMemberPw(request.getParameter("memberPw"));
		String newPw = request.getParameter("newMemberPw");
		System.out.println("passwordChange서블릿");
		System.out.println("id = "+member.getMemberId()+" Pw = "+member.getMemberPw()+"newPw = "+newPw);
		// 서비스로 넘겨주기
		MemberService memberService = new MemberService();
		successNum = memberService.changeService(member, newPw);
		System.out.println("successNum = "+successNum);
		// gson선언 및 응답
		Gson gson = new Gson();
		String json = gson.toJson(successNum);
		response.getWriter().write(json);
	}

}
