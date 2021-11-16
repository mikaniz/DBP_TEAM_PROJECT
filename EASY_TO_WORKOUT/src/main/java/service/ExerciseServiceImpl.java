package service;

import java.util.List;
import persistence.DAOFactory;
import persistence.dao.ExerciseDAO;
import service.dto.ExerciseDTO;


public class ExerciseServiceImpl {

	private ExerciseDAO dao = null;
	
	public ExerciseServiceImpl() {
		DAOFactory factory = new DAOFactory();
		dao = factory.getExerciseDAO();
	}
	
	public List<ExerciseDTO> ListingExercises() {
		return dao.getExerciseList();
	}
	
	public ExerciseDTO getExercise(String name) {
		return dao.getExerciseByName(name);
	}
}
