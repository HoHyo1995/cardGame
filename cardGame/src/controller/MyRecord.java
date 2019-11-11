package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.ReportService;
import vo.Member;
import vo.Report;


@WebServlet("/MyRecord")
public class MyRecord extends HttpServlet {
	Report report;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// JSON타입으로 응답한다고 선언한다
		response.setContentType("application/json;charset=utf-8");
		// html에서 넘어온 인수 값을 확인하고 받는다.
		System.out.println("myRecord서블릿");
		report = new Report();
		report.setMember(new Member());
		report.getMember().setMemberId(request.getParameter("loginState"));
		System.out.println("아이디는 "+report.getMember().getMemberId());
		// 리턴 받을 값 선언
		List<Report> list = new ArrayList<Report>();
		// 서비스로 넘겨준다
		ReportService reportService = new ReportService();
		list = reportService.myReportList(report);
		System.out.println(list);
		// Gson객체 선언 후 응답한다
		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.getWriter().write(json);
	}

}
