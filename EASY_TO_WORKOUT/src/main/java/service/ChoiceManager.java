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
	
	public List<Choice> getChoiceListByRoutineId(int routineId) {
		return choiceDao.getChoiceListByRoutineId(routineId);
	}
	
	public Choice getChoiceByRoutineIdAndExerciseId(int routineId, int exerciseId) {
		return choiceDao.getChoiceByRoutineIdAndExerciseId(routineId, exerciseId);
	}
	
	public int insertChoice(Choice choice) {
		return choiceDao.insertChoice(choice);
	}
	
	public int updateChoice(Choice choice) {
		return choiceDao.updateChoice(choice);
	}
	
	public int deleteChoice(int routineId) {
		return choiceDao.deleteChoice(routineId);
	}
	
	public int[] getSequenceList(int routineId) {
		return choiceDao.getSequenceList(routineId);
	}
	
	public int[] getRepetitionList(int routineId) {
		return choiceDao.getRepetitionList(routineId);
	}
}