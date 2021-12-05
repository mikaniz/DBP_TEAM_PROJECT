package service;

import java.util.List;

import persistence.DAOFactory;
import persistence.dao.ChoiceDAO;
import service.dto.Choice;

public class ChoiceManager {

	private static ChoiceManager choiceManager = new ChoiceManager();
	private ChoiceDAO choiceDao;
	
	private ChoiceManager() {
		DAOFactory factory = new DAOFactory();
		choiceDao = factory.getChoiceDAO();
	}
	
	public static ChoiceManager getInstance() {return choiceManager;}
	
	public List<Choice> getChoiceList() {
		return choiceDao.getChoiceList();
	}
	
	public Choice getChoiceListByRoutineId(int routineId) {
		return choiceDao.getChoiceListByRoutineId(routineId);
	}
	
	public int insertChoice(Choice choice) {
		return choiceDao.insertChoice(choice);
	}
	
	public int updateChoice(Choice choice, int routineId, int exerciseId, int sequence, int repetition) {
		return choiceDao.updateChoice(choice, routineId, exerciseId, sequence, repetition);
	}
	
	public int deleteChoice(int routineId) {
		return choiceDao.deleteChoice(routineId);
	}
}