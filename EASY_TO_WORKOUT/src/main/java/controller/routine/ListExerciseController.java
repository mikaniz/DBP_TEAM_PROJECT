package controller.routine;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import service.dto.Exercise;
import service.ExerciseManager;

public class ListExerciseController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ExerciseManager manager = ExerciseManager.getInstance();
		
		if (request.getMethod().equals("GET")) {
			List<Exercise> exerciseList = manager.ListingExercises();
			request.setAttribute("exerciseList", exerciseList);
			
			return "/routine/routine_createForm.jsp";
		}
		
		String type = request.getParameter("sortExercise");
		
		if (type.equals("1")) {
			request.setAttribute("checkedOne", true);
			
			List<Exercise> exerciseList = manager.ListingExercises();
			request.setAttribute("exerciseList", exerciseList);
		}
		else if (type.equals("2")) {
			request.setAttribute("checkedTwo", true);
			
			List<Exercise> exerciseList = manager.ListingExercises();
			request.setAttribute("exerciseList", exerciseList);
		}
		else if (type.equals("3")) {
			request.setAttribute("checkedThree", true);
			
			List<Exercise> exerciseList = manager.ListingExercises();
			request.setAttribute("exerciseList", exerciseList);
		}
		
		return "/routine/routine_createForm.jsp";
	}

}
