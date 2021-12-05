package service;

import java.sql.SQLException;
import service.exception.*;
import java.util.List;
import persistence.DAOFactory;
import persistence.dao.RoutineDAO;
import service.dto.Routine;
import service.dto.Exercise;

public class RoutineManager {

	private static RoutineManager rouSI = new RoutineManager();
	private RoutineDAO dao;
	private DAOFactory factory;
	
	public RoutineManager() {
		try {
			factory = new DAOFactory();
			dao = factory.getRoutineDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static RoutineManager getInstance() {
		return rouSI;
	}
	
	public List<Routine> ListingRoutines() throws SQLException {
		return dao.getRoutineList();
	}
	
	public List<Routine> ListingRoutinesByPublic() throws SQLException {
		return dao.getRoutineListByPublic();
	}
	
	public List<Routine> ListingRoutinesByPersonal(String id) throws SQLException {
		return dao.getRoutineListByPersonal(id);
	}
	
	public Routine getRoutineById(int routineId) throws SQLException  {
		return dao.getRoutineById(routineId);
	}
	
	public Routine getRoutineByName(String name) throws SQLException  {
		return dao.getRoutineByName(name);
	}
	
	public List<Routine> getRoutineByPart(String part) throws ExistingPartException, SQLException {
		// TODO Auto-generated method stub
		if (part.equals("")) {
			throw new ExistingPartException("운동부위를 다시 입력하세요.");
		}
		else if (!existingPart(part)) {
			throw new ExistingPartException("해당 운동부위가 존재하지 않습니다.");
		}
		return dao.getRoutineByPart(part);
	}
	
	public int insertRoutine(Routine routine) throws SQLException, ExistingRoutineException {
		if (dao.existingRoutine(routine.getrName()) == true) {
			throw new ExistingRoutineException(routine.getrName() + "는 이미 존재하는 루틴입니다.");
		}
		return dao.insertRoutine(routine);
	}
	
	public int updateRoutine(Routine routine) throws SQLException {
		return dao.updateRoutine(routine);
	}
	
	public int deleteRoutine(int routineId) throws SQLException  {
		return dao.deleteRoutine(routineId);
	}
	
	public boolean existingRoutine(String rName) throws SQLException {
		return dao.existingRoutine(rName);
	}
	
	public boolean existingPart(String part) throws SQLException {
		return dao.existingPart(part);
	}
	
	public List<Exercise> getExercises(int routineId) throws SQLException {
		return dao.getExercises(routineId);
	}
}
