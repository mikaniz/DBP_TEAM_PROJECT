package service.dto;

public class RoutineDTO {
	private int routineId = 0;
	private String rName = null;
	private int rTime = 0;
	private int difficulty = 0;
	private String rType = null;
	private String part = null;
	private String routineCreater = null;
	
	public RoutineDTO() {}
	
	public RoutineDTO(int routineId, String rName, int rTime, int difficulty, String rType, String part, String routineCreater) {
		this.routineId = routineId;
		this.rName = rName;
		this.rTime = rTime;
		this.difficulty = difficulty;
		this.rType = rType;
		this.part = part;
		this.routineCreater = routineCreater;
	}
	
	public int getRoutineId() {
		return routineId;
	}
	
	public void setRoutineId(int routineId) {
		this.routineId = routineId;
	}
	
	public String getrName() {
		return rName;
	}
	
	public void setrName(String rName) {
		this.rName = rName;
	}
	
	public int getrTime() {
		return rTime;
	}
	
	public void setrTime(int rTime) {
		this.rTime = rTime;
	}
	
	public int getDifficulty() {
		return difficulty;
	}
	
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	public String getrType() {
		return rType;
	}
	
	public void setrType(String rType) {
		this.rType = rType;
	}
	
	public String getPart() {
		return part;
	}
	
	public void setPart(String part) {
		this.part = part;
	}
	
	public String getRoutineCreater() {
		return routineCreater;
	}
	
	public void setRoutineCreater(String routineCreater) {
		this.routineCreater = routineCreater;
	}
}
