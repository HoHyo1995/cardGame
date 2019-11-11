package service;

import java.sql.Connection;
import java.sql.SQLException;
import dao.MemberDao;
import vo.Member;

public class MemberService {
	// 회원탈퇴 하기
		public int fireDeleteService(Member member) {
			int successNum = 0;
			System.out.println("fire서비스");
			System.out.println("id = "+member.getMemberId()+" Pw = "+member.getMemberPw());
			Connection conn = null;
			try {
				DBService dbService = new DBService();
				conn = dbService.getConnection();
				conn.setAutoCommit(false);
				MemberDao memberDao = new MemberDao();
				successNum = memberDao.deleteMember(conn, member);
				conn.commit();
			} catch(Exception e) {
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}finally {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				return successNum;
		}
	// 회원가입 하기
	public void insertJoin(Member member) {
		// 인수 확인하기
		System.out.println("joinMember 서비스 입니다.");
		System.out.println("ID = "+member.getMemberId()+" Pw"+member.getMemberPw());
		// DB에 사용할 변수 선언 후  초기화
		Connection conn = null;
		try {
			// 커넥션 불러오기
			DBService dbService = new DBService();
			conn = dbService.getConnection();
			// dao에서 커밋을 못하게한다
			conn.setAutoCommit(false);
			// dao에 인수값을 보내서 insert메소드를 실행한다
			MemberDao memberDao = new MemberDao();
			memberDao.insertMembership(conn, member);
			// commit 을 승낙한다
			conn.commit();
			System.out.println("commit확인");
		} catch(Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	// 로그인하기
	public String login(Member member) {
		System.out.println("MemberService 서비스");
		System.out.println("memberId = "+member.getMemberId()+"memberPw = "+member.getMemberPw());
		Member returnMember = null;
		Connection conn = null;
		
		try {
			DBService dbService = new DBService();
			conn = dbService.getConnection();
			// commit권한을 서비스가 가지게 된다(dao가 커밋을 못하게한다)
			conn.setAutoCommit(false);
			MemberDao memberDao = new MemberDao();
			// before
			returnMember = memberDao.login(conn, member);
			// after
			conn.commit();
		} catch(Exception e){
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
		
		
		return returnMember.getMemberId();
	}
	// 비밀번호 변경하기
	public int changeService(Member member, String newPw) {
		// 리턴받을 값 선언
		int successNum = 0;
		System.out.println("passwordChange서비스");
		System.out.println("id = "+member.getMemberId()+" Pw = "+member.getMemberPw()+"newPw = "+newPw);
		Connection conn = null;
		try {
			DBService dbService = new DBService();
			conn = dbService.getConnection();
			// commit권한을 서비스가 가지게 된다(dao가 커밋을 못하게한다)
			conn.setAutoCommit(false);
			MemberDao memberDao = new MemberDao();
			successNum = memberDao.changePassword(conn, member, newPw);
			conn.commit();
		} catch(Exception e) {
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
		return successNum;
	}
}
