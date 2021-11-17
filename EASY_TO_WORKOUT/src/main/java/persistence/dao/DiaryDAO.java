package persistence.dao;

import java.util.List;
import service.dto.DiaryDTO;

public interface DiaryDAO {
	
	public List<DiaryDTO> getAllDiaryList();
	public List<DiaryDTO> getMyDiaryList(String memberId);
	public List<DiaryDTO> getSortedAllDiary(String sortType);
	public List<DiaryDTO> getSortedMyDiary(String memberId, String sortType);
	public DiaryDTO getDiaryById(String id);
	public int insertDiary(DiaryDTO diary);
	public int updateDiary(DiaryDTO diary);
	public int deleteDiary(String id);
	
}
