package service;

import java.sql.*;
import java.util.*;

import javax.servlet.http.HttpSession;

import dao.ReportDao;
import vo.*;

public class ReportService {
	// 이달의 top10 서비스
		public List<Report> monthTopService() {
			// 리턴받을 List 생성
			List<Report> list = new ArrayList<Report>();
			// 인수값으로 넘어 온 값 확인
			System.out.println("monthTopService 서비스");
			// collection을 받을 변수 선언
			Connection conn = null;
			try {
				DBService dbService = new DBService();
				conn = dbService.getConnection();
				conn.setAutoCommit(false);
				ReportDao reportDao = new ReportDao();
				list = reportDao.selectMonthTopRecord(conn);
				conn.commit();
			}catch(Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return list;
		}
	// 오늘의 top10 서비스
	public List<Report> todayTopService() {
		// 리턴받을 List 생성
		List<Report> list = new ArrayList<Report>();
		// 인수값으로 넘어 온 값 확인
		System.out.println("TodayTopService 서비스");
		// collection을 받을 변수 선언
		Connection conn = null;
		try {
			DBService dbService = new DBService();
			conn = dbService.getConnection();
			conn.setAutoCommit(false);
			ReportDao reportDao = new ReportDao();
			list = reportDao.selectTodayTopRecord(conn);
			conn.commit();
		}catch(Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	// top10 서비스
		public List<Report> topService() {
			// 리턴받을 List 생성
			List<Report> list = new ArrayList<Report>();
			// 인수값으로 넘어 온 값 확인
			System.out.println("topService 서비스");
			// collection을 받을 변수 선언
			Connection conn = null;
			try {
				DBService dbService = new DBService();
				conn = dbService.getConnection();
				conn.setAutoCommit(false);
				ReportDao reportDao = new ReportDao();
				list = reportDao.selectTopRecord(conn);
				conn.commit();
			}catch(Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return list;
		}
	// 자신의 점수 리스트 확인 서비스
	public List<Report> myReportList(Report report) {
		// 리턴받을 List 생성
		List<Report> list = new ArrayList<Report>();
		// 인수값으로 넘어 온 값 확인
		System.out.println("reportService 서비스");
		System.out.println("아이디는 "+report.getMember().getMemberId());
		// collection을 받을 변수 선언
		Connection conn = null;
		try {
			DBService dbService = new DBService();
			conn = dbService.getConnection();
			conn.setAutoCommit(false);
			ReportDao reportDao = new ReportDao();
			list = reportDao.selectMyReportList(conn, report);
			conn.commit();
		}catch(Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	// 게임 종료 시 점수 등록 서비스
	public void insertReport(Report report) {
		System.out.println("ReportService");
		System.out.println("count"+report.getCount()+"timer"+report.getTimer()+"memberId"+report.getMember().getMemberId());		
		Connection conn = null;
		try {
			DBService dbService = new DBService();
			conn = dbService.getConnection();
			conn.setAutoCommit(false);
			ReportDao reportDao = new ReportDao();
			reportDao.insertScore(conn, report);
			conn.commit();
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
