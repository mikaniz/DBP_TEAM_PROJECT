package service;

import java.sql.SQLException;
import java.util.List;

import persistence.DAOFactory;
import persistence.dao.MembershipDAO;
import service.dto.Membership;
import service.exception.AlreadyInClubException;

public class MembershipManager {
	
	private static MembershipManager membershipManager = new MembershipManager();
	private MembershipDAO membershipDao;
	
	private MembershipManager() {
		DAOFactory factory = new DAOFactory();
		membershipDao = factory.getMembershipDAO();
	}
	
	public static MembershipManager getInstance() {return membershipManager;}
	
	public List<Membership> getClubListByMemberId(String memberId) {
		return membershipDao.getClubListByMemberId(memberId);
	}
	
	public int insertMembership(Membership membership) throws SQLException, AlreadyInClubException {
		// 이미 가입되어 있으면 exception 발생
		if (isInClub(membership.getClubId(), membership.getMemberId())) {
			throw new AlreadyInClubException("이미 해당 모임에 가입되어 있습니다.");
		}
		
		return membershipDao.insertMembership(membership);
	}
	
	public int deleteMembership(int clubId, String memberId) {
		return membershipDao.deleteMembership(clubId, memberId);
	}
	
	public boolean isInClub(int clubId, String memberId) {
		return membershipDao.isInClub(clubId, memberId);
	}
}
