package persistence.dao;

import java.util.List;
import service.dto.RoutineDTO;

public interface RoutineDAO {

	public List<RoutineDTO> getRoutineList();	//루틴리스트 반환
	public List<RoutineDTO> getRoutineListByPublic();	//퍼블릭루틴리스트 반환
	public List<RoutineDTO> getRoutineListByPersonal();	//퍼스널루틴리스트 반환
	public int insertRoutine(RoutineDTO routine);	//루틴정보를 추가
	public int updateRoutine(RoutineDTO routine);	//루틴정보를 수정
	public int deleteRoutine(int routineId);	//루틴정보를 삭제
	public RoutineDTO getRoutineByName(String rName);	//루틴정보를 루틴명으로 찾음
	public boolean existingRoutine(String rName);
}
