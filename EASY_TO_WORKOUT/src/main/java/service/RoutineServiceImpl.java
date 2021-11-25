package service;

import java.sql.SQLException;
import service.exception.*;
import java.util.List;
import persistence.DAOFactory;
import persistence.dao.RoutineDAO;
import service.dto.Routine;

public class RoutineServiceImpl {

	private static RoutineServiceImpl rouSI = new RoutineServiceImpl();
	private RoutineDAO dao;
	private DAOFactory factory;
	
	public RoutineServiceImpl() {
		try {
			factory = new DAOFactory();
			dao = factory.getRoutineDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static RoutineServiceImpl getInstance() {
		return rouSI;
	}
	
	public List<Routine> ListingRoutines() throws SQLException {
		return dao.getRoutineList();
	}
	
	public List<Routine> ListingRoutinesByPublic() throws SQLException {
		return dao.getRoutineListByPublic();
	}
	
	public List<Routine> ListingRoutinesByPersonal() throws SQLException {
		return dao.getRoutineListByPersonal();
	}
	
	public List<Routine> getRoutineByName(String rName) throws ExistingRoutineException, SQLException {
		// TODO Auto-generated method stub
		if (rName.equals("")) {
			throw new ExistingRoutineException("루틴명을 다시 입력하세요.");
		}
		else if (!existingRoutine(rName)) {
			throw new ExistingRoutineException("해당 루틴이 존재하지 않습니다.");
		}
		return dao.getRoutineByName(rName);
	}
	
	public boolean existingRoutine(String rName) throws SQLException {
		return dao.existingRoutine(rName);
	}
	
	public int insertRoutine(Routine routine) throws SQLException, ExistingRoutineException {
		if (dao.existingRoutine(routine.getrName()) == true) {
			throw new ExistingRoutineException(routine.getrName() + "는 존재하는 루틴입니다.");
		}
		return dao.insertRoutine(routine);
	}
	
	public int updateRoutine(Routine routine) throws SQLException, RoutineNotFoundException, ExistingRoutineException {
		String oldRoutineName = getRoutine(routine.getRoutineId()).getrName();
		if (routine.getrName().equals(oldRoutineName) == false) {
			throw new RoutineNotFoundException(routine.getrName() + "는 존재하지 않는 루틴입니다.");
		}
		return dao.updateRoutine(routine);
	}
	
	public int deleteRoutine(int routineId) {
		return dao.deleteRoutine(routineId);
	}
	
	public Routine getRoutine(int routineId) throws SQLException, ExistingRoutineException {
		return dao.getRoutineById(routineId);
	}
}
