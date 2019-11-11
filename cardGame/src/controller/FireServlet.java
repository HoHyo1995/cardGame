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


@WebServlet("/FireServlet")
public class FireServlet extends HttpServlet {
	Member member;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 확인
		System.out.println("fire서블릿");
		// 리턴값 필요 확인 하기위해서
		int successNum = 0;
		// json으로 응답한다고 선언
		response.setContentType("application/json;charset=utf-8");
		// 맴버객체를 만들어서 넘어온 값들을 받고 확인한다.
		member = new Member();
		member.setMemberId(request.getParameter("memberId"));
		member.setMemberPw(request.getParameter("memberPw"));
		System.out.println("id = "+member.getMemberId()+" Pw = "+member.getMemberPw());
		// 서비스로 넘겨준다.
		MemberService memberService = new MemberService();
		successNum = memberService.fireDeleteService(member);
		System.out.println("successNum = "+successNum);
		
		// Gson
		Gson gson = new Gson();
		String json = gson.toJson(successNum);
		response.getWriter().write(json);
	}

}
