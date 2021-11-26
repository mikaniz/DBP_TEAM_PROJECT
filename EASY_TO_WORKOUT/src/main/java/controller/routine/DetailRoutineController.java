package controller.routine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import service.RoutineManager;
import service.dto.Routine;
import service.dto.Exercise;
import java.util.List;

public class DetailRoutineController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		RoutineManager manager = RoutineManager.getInstance();
		
		int routineId = Integer.parseInt(request.getParameter("routineId"));
		Routine routine = manager.getRoutine(routineId);
		request.setAttribute("routine", routine);
		
		List<Exercise> exerciseList = manager.getExercises(routineId);
		request.setAttribute("exerciseList", exerciseList);
		
		return "/routine/routine_detail.jsp";
	}

}
