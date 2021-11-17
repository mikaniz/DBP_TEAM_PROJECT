package service;

import java.util.List;

import java.sql.SQLException;

import persistence.dao.impl.DiaryDAOImpl;
import service.dto.DiaryDTO;

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
	
}
