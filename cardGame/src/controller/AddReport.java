package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ReportService;
import vo.*;


@WebServlet("/AddReport")
public class AddReport extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		// puzzle.html에서 넘어온 값을 받고 확인하기위해 객체 생성을 한다
		Report report = new Report();
		report.setCount(Integer.parseInt(request.getParameter("count")));
		report.setTimer(Integer.parseInt(request.getParameter("timer")));
		System.out.println("AddReport 서블렛");
		System.out.println("count = "+report.getCount()+" timer = "+report.getTimer());
		
		HttpSession session = request.getSession();
		String sessionInfo = (String)session.getAttribute("sessionInfo");
		report.setMember(new Member());
		report.getMember().setMemberId(sessionInfo);
		System.out.println("memberId"+report.getMember().getMemberId());
		// 서비스로 넘긴다
		ReportService reportService = new ReportService();
		reportService.insertReport(report);
		
	}

}
