package service;

import java.util.List;
import persistence.DAOFactory;
import persistence.dao.ExerciseDAO;
import service.dto.Exercise;


public class ExerciseManager {

	private ExerciseDAO dao = null;
	
	public ExerciseManager() {
		DAOFactory factory = new DAOFactory();
		dao = factory.getExerciseDAO();
	}
	
	public List<Exercise> ListingExercises() {
		return dao.getExerciseList();
	}
	
	public Exercise getExercise(String name) {
		return dao.getExerciseByName(name);
	}
}
