package service;

import java.util.List;
import persistence.DAOFactory;
import persistence.dao.ExerciseDAO;
import service.dto.Exercise;


public class ExerciseServiceImpl {

	private ExerciseDAO dao = null;
	
	public ExerciseServiceImpl() {
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
