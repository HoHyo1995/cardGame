package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.ReportService;
import vo.Report;


@WebServlet("/Top")
public class Top extends HttpServlet {
	Report report;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Top 서블릿");
		// json으로 응답한다고 선언
		response.setContentType("application/json;charset=utf-8");		
		// 리턴받을 값을 선언 하고 서비스로 넘어간다
		ReportService reportService = new ReportService();
		List <Report> list = reportService.topService();
		System.out.println(list);
		// json타입 선언 후 응답한다 
		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.getWriter().write(json);
	}

}
