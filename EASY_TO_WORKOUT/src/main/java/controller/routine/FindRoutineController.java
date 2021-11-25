package controller.routine;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import service.RoutineServiceImpl;
import service.dto.RoutineDTO;
import service.exception.ExistingRoutineException;


public class FindRoutineController implements Controller{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		RoutineServiceImpl service = RoutineServiceImpl.getInstance();
		
		String routineName = request.getParameter("searchRoutine");
		
		try {
			List<RoutineDTO> routineList = service.getRoutineByName(routineName);
			request.setAttribute("routineList", routineList);
			
			return "/routine/routinePage.jsp";
		}
		catch(ExistingRoutineException e) {
			request.setAttribute("findRoutineFailed", true);
			request.setAttribute("exception", e);
			
			return "/routine/routinePage.jsp";
		}
	
	}

}
