package controller.club;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import service.RoutineManager;
import service.dto.Routine;

public class UsageRoutineController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		RoutineManager routineManager = RoutineManager.getInstance();
		
		List<Routine> routineList = routineManager.ListingRoutines();
		request.setAttribute("routineList", routineList);
		
		return "/club/schedule_usageForm.jsp";
	}

}
