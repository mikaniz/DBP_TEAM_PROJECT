package service;

import java.util.List;

import persistence.dao.DiaryDAOImpl;

import java.sql.SQLException;

import service.dto.DiaryDTO;
import service.exception.DiaryNotFoundException;

public class DiaryServiceImpl {

	private static DiaryServiceImpl diaryService = new DiaryServiceImpl();
	private DiaryDAOImpl diaryDAO;
	
	private DiaryServiceImpl() {
		try {
			diaryDAO = new DiaryDAOImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static DiaryServiceImpl getInstance() {
		return diaryService;
	}

	public int create(DiaryDTO diary) throws SQLException {
		return diaryDAO.insertDiary(diary);
	}
	
	public List<DiaryDTO> findMyDiaryList(String id) throws SQLException {
		return diaryDAO.getMyDiaryList(id);
	}
	
	public List<DiaryDTO> findAllDiaryList() throws SQLException {
		return diaryDAO.getAllDiaryList();
	}
	
	public List<DiaryDTO> getSortedAllDiary(String sortType) throws SQLException {
		return diaryDAO.getSortedAllDiary(sortType);
	}
	
	public List<DiaryDTO> getSortedMyDiary(String id, String sortType) throws SQLException {
		return diaryDAO.getSortedMyDiary(id, sortType);
	}
	
	public List<DiaryDTO> getMyDiaryByTitle(String id, String title) throws DiaryNotFoundException, SQLException {
		if (title.equals("")) {
			throw new DiaryNotFoundException("제목을 다시 입력하세요.");
		} else if (!existingMyDiary(id, title)) {
			throw new DiaryNotFoundException("해당 다이어리가 존재하지 않습니다.");
		}
		return diaryDAO.getMyDiaryByTitle(id, title);
	}
	
	public List<DiaryDTO> getAllDiaryByTitle(String title) throws DiaryNotFoundException, SQLException {
		if (title.equals("")) {
			throw new DiaryNotFoundException("제목을 다시 입력하세요.");
		} else if (!existingAllDiary(title)) {
			throw new DiaryNotFoundException("해당 다이어리가 존재하지 않습니다.");
		}
		return diaryDAO.getAllDiaryByTitle(title);
	}
	
	public boolean existingMyDiary(String id, String title) throws SQLException {
		return diaryDAO.existingMyDiary(id, title);
	}

	public boolean existingAllDiary(String title) throws SQLException {
		return diaryDAO.existingAllDiary(title);
	}
	
}
