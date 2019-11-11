package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


import service.MemberService;
import vo.Member;


@WebServlet("/JoinMembership")
public class JoinMembership extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// json으로 응답한다고 선언
		response.setContentType("application/json;charset=utf-8");
		System.out.println("joinMemberShip서블렛입니다.");
		// 맴버 객체 생성 해서 넘어온 값을 받고 확인한다.
		Member member = new Member();
		member.setMemberId(request.getParameter("memberId"));
		member.setMemberPw(request.getParameter("memberPw"));
		System.out.println("ID = "+member.getMemberId()+" Pw"+member.getMemberPw());
		// 서비스에 보내주기
		MemberService memberService = new MemberService();
		memberService.insertJoin(member);
		System.out.println("응답--->");
		// json타입으로 응답
		Gson gson = new Gson();
		String json = gson.toJson(member);
		response.getWriter().write(json);
	}

}
