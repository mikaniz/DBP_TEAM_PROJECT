package persistence;

import persistence.dao.*;

// DAO 를 구현한 Implementation 객체를 생성하는 클래스
public class DAOFactory {
	
//	public ChoiceDAO getChoiceDAO() {
//		return new ChoiceDAOImpl();
//	}
	
	public ClubDAO getClubDAO() {
		return new ClubDAOImpl();
	}
	
	public ClubScheduleDAO getClubScheduleDAO() {
		return new ClubScheduleDAOImpl();
	}
	
	public DiaryDAO getDiaryDAO() {
		return new DiaryDAOImpl();
	}
	
	public ExerciseDAO getExerciseDAO() {
		return new ExerciseDAOImpl();
	}
	
	public MemberDAO getMemberDAO() {
		return new MemberDAOImpl();
	}
	
//	public MembershipDAO getMembershipDAO() {
//		return new MembershipDAOImpl();
//	}
//	
	public RoutineDAO getRoutineDAO() {
		return new RoutineDAOImpl();
	}
//	
//	public UsageDAO getUsageDAO() {
//		return new UsageDAOImpl();
//	}
}
