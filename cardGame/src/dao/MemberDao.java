package dao;

import java.sql.*;

import com.sun.corba.se.impl.orb.PrefixParserAction;

import vo.Member;
 
public class MemberDao {
	// 회원탈퇴 하기
	public int deleteMember(Connection conn, Member member) throws Exception {
		int successNum = 0;
		System.out.println("joinMember dao 입니다.");
		System.out.println("ID = "+member.getMemberId()+" Pw"+member.getMemberPw());
		PreparedStatement stmt = null;
		// id는 readonly로넘어오고 아이디와 비밀번호가 똑같은것만 삭제하기 위해서
		String sql = "DELETE FROM member WHERE member_id = ? AND member_pw = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			successNum = stmt.executeUpdate();
		} finally {
			stmt.close();
		}
			return successNum;
	}
	// 회원가입 하기
	public void insertMembership(Connection conn, Member member) throws Exception {
		// 인수가 넘어왔는지 확인
		System.out.println("JoinMembership의 dao입니다.");
		System.out.println("conn = "+conn+"ID = "+member.getMemberId()+" Pw"+member.getMemberPw());
		// DB에 사용 될 변수 선언 및 초기화
		PreparedStatement stmt = null;
		// 쿼리문 스트링 타입으로 선언
		String sql = "INSERT INTO member (member_id, member_pw, member_level) VALUES (?,?,'Y')";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			stmt.executeQuery();
		} finally {
			stmt.close();
		}
	}
	// 비밀번호 변경 하기
	public int changePassword(Connection conn, Member member, String newPw) throws Exception {
		int successNum = 0;
		System.out.println("passwordChangeDao");
		System.out.println("conn의 값 "+conn+"id = "+member.getMemberId()+" Pw = "+member.getMemberPw()+"newPw = "+newPw);
		// DB에 사용한 변수 선언 및 초기화
		PreparedStatement stmt = null;
		// 쿼리문 스트링 타입으로 저장
		String sql = "UPDATE member SET member_pw=? WHERE member_id=? and member_pw=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, newPw);
			stmt.setString(2, member.getMemberId());
			stmt.setString(3, member.getMemberPw());
			successNum = stmt.executeUpdate();
		}finally {
			stmt.close();
		}
		return successNum;
	}
	/*
	 * select member_id from member where member_id=? and member_pw=? and member_level='Y'
	 */
	// 로그인 하기
	public Member login(Connection conn, Member member) throws Exception {
		System.out.println("conn = "+conn+"member = "+member);
		Member returnMember = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// 아이디와 비밀번호가 똑같은게 있어야 딱 하나가 셀렉트 되니까 쿼리문 조건 이렇게 씀
		String sql = "select member_id from member where member_id=? and member_pw=? and member_level='Y'";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberId(rs.getString("member_id")); 
				System.out.println(returnMember.getMemberId());
			}
		}finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return returnMember;
	}
}
