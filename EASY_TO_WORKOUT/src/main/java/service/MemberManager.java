package service;

import java.sql.SQLException;

import persistence.dao.MemberDAO;
import service.dto.Member;
import service.exception.ExistingMemberException;
import service.exception.MemberNotFoundException;
import service.exception.PasswordMismatchException;

public class MemberManager {

	private static MemberManager memberService = new MemberManager();
	private MemberDAO memberDAO;
	
	private MemberManager() {
		try {
			memberDAO = new MemberDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static MemberManager getInstance() {
		return memberService;
	}
	
	public int create(Member member) throws SQLException, ExistingMemberException {
		if (memberDAO.existingMember(member.getId())) {
			throw new ExistingMemberException(member.getId() + "는 존재하는 아이디입니다.");
		}
		return memberDAO.insertMember(member);
	}
	
	public int update(Member member, Member update, String pw) 
			throws SQLException, PasswordMismatchException {
		if (!member.matchPassword(pw)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return memberDAO.updateMember(update);
	}
	
	public Member findMember(String id)
		throws SQLException, MemberNotFoundException {
		Member member = memberDAO.getMemberById(id);
		
		if (member == null) {
			throw new MemberNotFoundException(id + "는 존재하지 않는 아이디입니다.");
		}
		return member;
	}
	
	public boolean login(String id, String pw)
		throws SQLException, MemberNotFoundException, PasswordMismatchException {
		Member member = findMember(id);
		
		if (!member.matchPassword(pw)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}
	
}
