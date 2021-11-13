package persistence.dao;

import java.util.List;
import service.dto.ExerciseDTO;

public interface ExerciseDAO {

	public List<ExerciseDTO> getExerciseList();	//운동리스트 반환
	public ExerciseDTO getExerciseByName(String name);	//운동정보를 운동명으로 찾음
}
