package persistence.dao;

import java.util.List;
import service.dto.ClubDTO;

public interface ClubDAO {
	
	public List<ClubDTO> getClubList();
	public ClubDTO getClubById(int clubId);
	public List<ClubDTO> getClubByName(String clubName);
	public int insertClub(ClubDTO club); 
	public int deleteClub(int clubId);
	
}
