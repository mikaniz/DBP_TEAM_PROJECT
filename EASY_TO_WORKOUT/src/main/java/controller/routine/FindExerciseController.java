package controller.routine;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.MemberSessionUtils;
import service.ExerciseManager;
import service.RoutineManager;
import service.dto.Exercise;
import service.dto.Routine;
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
		
		request.setAttribute("routineName", request.getParameter("routineName"));
		request.setAttribute("routinePart", request.getParameter("routinePart"));
		request.setAttribute("routineTime", request.getParameter("routineTime"));
		request.setAttribute("routineLevel", request.getParameter("routineLevel"));
		request.setAttribute("routineType", request.getParameter("routineType"));
		
		if (request.getParameter("thisIsForChoice") != null) {
			request.setAttribute("thisIsForChoice", "thisIsForChoice");
		}
		else {
			RoutineManager routineManager = RoutineManager.getInstance();
			int routineId = Integer.parseInt(request.getParameter("routineId"));
			Routine routine = routineManager.getRoutineById(routineId);
			request.setAttribute("routine", routine);
		}
		
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