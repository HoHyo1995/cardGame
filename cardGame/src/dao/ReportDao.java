package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Member;
import vo.Report;

public class ReportDao {
	// 이달의 Top 10 리스트
		public List<Report> selectMonthTopRecord(Connection conn) throws Exception {
			// 리턴 받을 타입 선언
			List<Report> list = new ArrayList<Report>();
			// 넘어 온 인수값들 확인
			System.out.println("selectMonthTopRecord dao");	
			// DB에 필요 한 변수 선언 및 초기화
			PreparedStatement stmt = null;
			ResultSet rs = null;
			// 쿼리문 스트링 타입으로 저장
			String sql = "SELECT member_id, timer, COUNT, report_date FROM report WHERE month(report_date)= month(NOW()) ORDER BY timer ASC,COUNT ASC LIMIT 10";
			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while(rs.next()) {
					Report reportDao = new Report();
					reportDao.setMember(new Member());
					reportDao.getMember().setMemberId(rs.getString("member_id"));
					reportDao.setTimer(rs.getInt("timer"));
					reportDao.setCount(rs.getInt("count"));
					reportDao.setReportDate(rs.getString("report_date"));
					list.add(reportDao);
					
				}
			}finally {
				stmt.close();
			}
			return list;
		}
	// 오늘의 Top 10 리스트
	public List<Report> selectTodayTopRecord(Connection conn) throws Exception {
		// 리턴 받을 타입 선언
		List<Report> list = new ArrayList<Report>();
		// 넘어 온 인수값들 확인
		System.out.println("selectTodayTopRecord dao");	
		// DB에 필요 한 변수 선언 및 초기화
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// 쿼리문 스트링 타입으로 저장
		String sql = "SELECT member_id, timer, COUNT, report_date FROM report WHERE day(report_date)= DAY(NOW()) ORDER BY timer ASC,COUNT ASC LIMIT 10";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Report reportDao = new Report();
				reportDao.setMember(new Member());
				reportDao.getMember().setMemberId(rs.getString("member_id"));
				reportDao.setTimer(rs.getInt("timer"));
				reportDao.setCount(rs.getInt("count"));
				reportDao.setReportDate(rs.getString("report_date"));
				list.add(reportDao);
				
			}
		}finally {
			stmt.close();
		}
		return list;
	}
	// 전체 Top 10 리스트
		public List<Report> selectTopRecord(Connection conn) throws Exception {
			// 리턴 받을 타입 선언
			List<Report> list = new ArrayList<Report>();
			// 넘어 온 인수값들 확인
			System.out.println("topRecord dao");	
			// DB에 필요 한 변수 선언 및 초기화
			PreparedStatement stmt = null;
			ResultSet rs = null;
			// 쿼리문 스트링 타입으로 저장
			String sql = "SELECT member_id, timer, COUNT, report_date FROM report ORDER BY timer ASC,COUNT ASC LIMIT 10";
			try {
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				while(rs.next()) {
					Report reportDao = new Report();
					reportDao.setMember(new Member());
					reportDao.getMember().setMemberId(rs.getString("member_id"));
					reportDao.setTimer(rs.getInt("timer"));
					reportDao.setCount(rs.getInt("count"));
					reportDao.setReportDate(rs.getString("report_date"));
					list.add(reportDao);
					
				}
			}finally {
				stmt.close();
			}
			return list;
		}
	// 자신의 기록들을 순위로 보스는 메소드
	public List<Report> selectMyReportList(Connection conn, Report report) throws Exception {
		// 리턴 받을 타입 선언
		List<Report> list = new ArrayList<Report>();
		// 넘어 온 인수값들 확인
		System.out.println("selectMyReportList dao");
		System.out.println("커넥션은 "+conn+"아이디는 "+report.getMember().getMemberId());
		// DB에 필요 한 변수 선언 및 초기화
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// 쿼리문 스트링 타입으로 저장
		String sql = "SELECT member_id, timer, COUNT, report_date FROM report WHERE member_id=? ORDER BY timer ASC,COUNT asc";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, report.getMember().getMemberId());
			rs = stmt.executeQuery();
			while(rs.next()) {
				Report reportDao = new Report();
				reportDao.setMember(new Member());
				reportDao.getMember().setMemberId(rs.getString("member_id"));
				reportDao.setTimer(rs.getInt("timer"));
				reportDao.setCount(rs.getInt("count"));
				reportDao.setReportDate(rs.getString("report_date"));
				list.add(reportDao);
			}
		}finally {
			stmt.close();
		}
		return list;
	}
	// 게임 종료 시 점수 등록 서비스
	//INSERT INTO report (member_id, report_date, COUNT, timer) VALUES(?,NOW(),?,?)
	public void insertScore(Connection conn, Report report) {
		System.out.println("dao");
		System.out.println("member_id"+report.getMember().getMemberId()+"COUNT"+report.getCount()+"timer"+report.getTimer());
		PreparedStatement stmt = null;
		String sql = "INSERT INTO report (member_id, report_date, COUNT, timer) VALUES(?,NOW(),?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, report.getMember().getMemberId());
			stmt.setInt(2, report.getCount());
			stmt.setInt(3, report.getTimer());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}