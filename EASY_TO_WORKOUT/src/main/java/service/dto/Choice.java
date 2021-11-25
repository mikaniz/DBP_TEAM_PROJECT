package service.dto;

public class Choice {
	private int exerciseId = 0;
	private int routineId = 0;
	private int sequence = 0;
	private int repetition = 0;
	
	public int getExerciseId() {
		return exerciseId;
	}
	
	public void setExerciseId(int exerciseId) {
		this.exerciseId = exerciseId;
	}
	
	public int getRoutineId() {
		return routineId;
	}
	
	public void setRoutineId(int routineId) {
		this.routineId = routineId;
	}
	
	public int getSequence() {
		return sequence;
	}
	
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	public int getRepetition() {
		return repetition;
	}
	
	public void setRepetition(int repetition) {
		this.repetition = repetition;
	}
}
