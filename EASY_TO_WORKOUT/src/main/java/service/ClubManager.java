package service;

import java.sql.SQLException;
import java.util.List;

import persistence.DAOFactory;
import persistence.dao.ClubDAO;
//import persistence.dao.MemberDAO;
import service.dto.Club;
import service.exception.ExistingClubException;

public class ClubManager {
	
	private static ClubManager clubService = new ClubManager(); 
	private ClubDAO clubDao;
//	private MemberDAO memberDao;
	
	private ClubManager() {							// DAOFactory 클래스의 객체 생성
		DAOFactory factory = new DAOFactory();
		clubDao = factory.getClubDAO();
//		memberDao = factory.getMemberDAO();
	}
	
	public static ClubManager getInstance() {	return clubService;		}

	public List<Club> listingClub() throws SQLException {				// ClubDAO를 통해 모임 정보 목록 획득
		// TODO Auto-generated method stub
		return clubDao.getClubList();
	}

	public List<Club> getClubByName(String clubName) throws ExistingClubException, SQLException { // ClubDAO를 통해 이름에 해당하는 모임 정보 목록 획득
		// TODO Auto-generated method stub
		if (clubName.equals("")) {
			throw new ExistingClubException("모임 이름을 다시 입력하세요.");
		}
		else if (!existingClub(clubName)) {
			throw new ExistingClubException("해당 모임이 존재하지 않습니다.");
		}
		return clubDao.getClubByName(clubName);
	}

	public Club getClubById(int clubId) {			// ClubDAO를 통해 아이디에 해당하는 모임 정보 획득
		// TODO Auto-generated method stub
		return clubDao.getClubById(clubId);
	}

	public List<Club> getSortedClub(String sortWith) { // ClubDAO를 통해 모임 정렬 (회원순, 자유가입)
		return clubDao.getSortedClub(sortWith);
	}
	
	public boolean existingClub(String clubName) throws SQLException {
		return clubDao.existingClub(clubName);
	}
	
	public int insertClub(Club club) throws ExistingClubException, SQLException {				// ClubDAO를 통해 모임 정보 추가
		// TODO Auto-generated method stub
		
/*		if (!memberDao.isMaster(club.getMasterId())) {	// clubMaster가 마스터 등급이 아닐 경우 예외 발생
			throw new ExistingClubException("마스터 등급이 아닙니다.");
		}
		else if (!memberDao.existingUser(masterId)) {	// clubMaster가 존재하지 않는 아이디일 경우 예외 발생
			throw new ExistingClubException("존재하지 않는 회원 아이디입니다.");
		}*/
		
		return clubDao.insertClub(club);
	}

	public int deleteClub(int clubId) {					// ClubDAO를 통해 모임 정보 삭제
		// TODO Auto-generated method stub
		return clubDao.deleteClub(clubId);
	}
	
	
}
