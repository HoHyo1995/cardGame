package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import service.MemberService;
import vo.Member;


@WebServlet("/Login")
public class Login extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// json 타입으로 응답한다고 선언
		response.setContentType("application/json;charset=utf8");
		// 맴버 변수 생성해서 받고 확인한다
		Member member = new Member();
			member.setMemberId(request.getParameter("memberId"));
			member.setMemberPw(request.getParameter("memberPw"));
		System.out.println("login서블릿");
		System.out.println("memberId = "+member.getMemberId()+"memberPw = "+member.getMemberPw());
		// 서비스로 넘긴다(MemberService)
		MemberService memberService = new MemberService();
		member.setMemberId(memberService.login(member));
		
		
		HttpSession session = request.getSession();
		session.setAttribute("sessionInfo", member.getMemberId());
		String sessionInfo = (String)session.getAttribute("sessionInfo");
		Gson gson = new Gson();
		String json = gson.toJson(sessionInfo);
		response.getWriter().write(json);
	}

}
