package controller.routine;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import service.dto.RoutineDTO;
import service.RoutineServiceImpl;

public class ListRoutineController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RoutineServiceImpl manager = RoutineServiceImpl.getInstance();
		
		if (request.getMethod().equals("GET")) {
			List<RoutineDTO> routineList = manager.ListingRoutines();
			request.setAttribute("routineList", routineList);
			
			return "/routine/routinePage.jsp";
		}
		
		String type = request.getParameter("sortRoutine");
		
		if (type.equals("1")) {
			request.setAttribute("checkedOne", true);
			
			List<RoutineDTO> routineList = manager.ListingRoutines();
			request.setAttribute("routineList", routineList);
		}
		else if (type.equals("2")) {
			request.setAttribute("checkedTwo", true);
			
			List<RoutineDTO> routineList = manager.ListingRoutinesByPublic();
			request.setAttribute("routineList", routineList);
		}
		else if (type.equals("3")) {
			request.setAttribute("checkedThree", true);
			
			List<RoutineDTO> routineList = manager.ListingRoutinesByPersonal();
			request.setAttribute("routineList", routineList);
		}
		
		return "/routine/routinePage.jsp";
	}

}
