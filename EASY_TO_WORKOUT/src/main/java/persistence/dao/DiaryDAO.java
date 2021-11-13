package persistence.dao;

import java.util.List;
import service.dto.DiaryDTO;

public interface DiaryDAO {
	
	public List<DiaryDTO> getAllDiaryList();
	public List<DiaryDTO> getMyDiaryList(String memberId);
	public DiaryDTO getDiaryById(String id);
	public int insertDairy(DiaryDTO diary);
	public int updateDiary(DiaryDTO diary);
	public int deleteDiary(String id);
	
}
