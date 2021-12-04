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
		
		MemberSessionUtils.setLoginUserInfo(session, request);
		request.setAttribute("btnType", "routineCreate");
		
		ExerciseManager manager = ExerciseManager.getInstance();
		
		String exerciseName = request.getParameter("searchExercise");
		
		try {
			List<Exercise> exerciseList = manager.getExerciseByName(exerciseName);
			request.setAttribute("exerciseList", exerciseList);
			
			return "/routine/exercise_choiceForm.jsp";
		}
		catch(ExistingExerciseException e) {
			List<Exercise> exerciseList = manager.ListingExercises();
			request.setAttribute("exerciseList", exerciseList);
			
			request.setAttribute("findExerciseFailed", true);
			request.setAttribute("exception", e);
			
			return "/routine/exercise_choiceForm.jsp";
		}
	
	}

}