package controller.routine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import service.dto.Routine;
import service.RoutineManager;

public class CreateRoutineController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Routine routine = new Routine();
		routine.setrName(request.getParameter("routineName"));
		routine.setRoutineCreater(request.getParameter("routineCreater"));
		routine.setPart(request.getParameter("routinePart"));
		routine.setrTime(Integer.parseInt(request.getParameter("routineTime"))); 
		routine.setDifficulty(Integer.parseInt(request.getParameter("routineLevel"))); 
		routine.setrType(request.getParameter("routineType"));
		
		try {
			RoutineManager manager = RoutineManager.getInstance();
			manager.insertRoutine(routine);
			
			return "redirect:/routine/list";
		}catch (Exception e) {
			request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("routine", routine);
			return "/routine/routine_createForm.jsp";
		}
	}

}
