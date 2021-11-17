package service;

import java.sql.SQLException;
import service.exception.*;
import java.util.List;
import persistence.DAOFactory;
import persistence.dao.RoutineDAO;
import service.dto.RoutineDTO;

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
	
	public List<RoutineDTO> ListingRoutines() throws SQLException {
		return dao.getRoutineList();
	}
	
	public List<RoutineDTO> ListingRoutinesByPublic() throws SQLException {
		return dao.getRoutineListByPublic();
	}
	
	public List<RoutineDTO> ListingRoutinesByPersonal() throws SQLException {
		return dao.getRoutineListByPersonal();
	}
	
	public int insertRoutine(RoutineDTO routine) throws SQLException, ExistingRoutineException {
		if (dao.existingRoutine(routine.getrName()) == true) {
			throw new ExistingRoutineException(routine.getrName() + "는 존재하는 루틴입니다.");
		}
		return dao.insertRoutine(routine);
	}
	
	public int updateRoutine(RoutineDTO routine) throws SQLException, RoutineNotFoundException {
		String oldRoutineName = getRoutine(routine.getrName()).getrName();
		if (routine.getrName().equals(oldRoutineName) == false) {
			throw new RoutineNotFoundException(routine.getrName() + "는 존재하지 않는 루틴입니다.");
		}
		return dao.updateRoutine(routine);
	}
	
	public int deleteRoutine(int routineId) throws SQLException {
		return dao.deleteRoutine(routineId);
	}
	
	public RoutineDTO getRoutine(String rName) throws SQLException {
		return dao.getRoutineByName(rName);
	}
}
