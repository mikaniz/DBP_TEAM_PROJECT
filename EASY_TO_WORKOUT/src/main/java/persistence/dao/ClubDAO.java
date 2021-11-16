package persistence.dao;

import java.sql.SQLException;
import java.util.List;
import service.dto.ClubDTO;
import service.exception.ExistingClubException;

public interface ClubDAO {
	
	public List<ClubDTO> getClubList() throws SQLException; // 모임 목록
	public ClubDTO getClubById(int clubId);
	public List<ClubDTO> getClubByName(String clubName) throws ExistingClubException, SQLException; // 이름으로 모임 검색(정렬 기준 선택)
	public List<ClubDTO> getSortedClub(String sortWith); // 모임 정렬 (회원순, 자유가입)
	public boolean existingClub(String clubName) throws SQLException;
	public int insertClub(ClubDTO club) throws SQLException; 
	public int deleteClub(int clubId);
	
}
