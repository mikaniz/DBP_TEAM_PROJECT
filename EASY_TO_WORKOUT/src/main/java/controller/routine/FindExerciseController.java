package controller.routine;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.MemberSessionUtils;
import service.ExerciseManager;
import service.dto.Exercise;
import service.exception.ExistingExerciseException;

public class FindExerciseController implements Controller{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (!MemberSessionUtils.hasLogined(session)) {
			return "redirect:/login";
		}
		
		ExerciseManager manager = ExerciseManager.getInstance();
		
		String exerciseName = request.getParameter("exerciseName");
		
		try {
			List<Exercise> exerciseList = manager.getExerciseByName(exerciseName);
			request.setAttribute("exerciseList", exerciseList);
			
			return "/routine/routine_createForm.jsp";
		}
		catch(ExistingExerciseException e) {
			request.setAttribute("findExerciseFailed", true);
			request.setAttribute("exception", e);
			
			return "/routine/routine_createForm.jsp";
		}
	
	}

}