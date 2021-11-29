package controller.routine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.MemberSessionUtils;
import service.ExerciseManager;
import service.dto.Exercise;

public class ChoiceExerciseController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (!MemberSessionUtils.hasLogined(session)) {
			return "redirect:/login";
		}
		
		ExerciseManager manager = ExerciseManager.getInstance();
		
		int exerciseId = Integer.parseInt(request.getParameter("exerciseId"));
		Exercise exercise = manager.getExercise(exerciseId);
		request.setAttribute("exercise", exercise);
		
		return "/routine/exercise_detail.jsp";
	}

}
