package controller.routine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.MemberSessionUtils;
import service.RoutineManager;
import service.ChoiceManager;
import service.dto.Routine;
import service.dto.Exercise;
import java.util.List;

public class DetailRoutineController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (!MemberSessionUtils.hasLogined(session)) {
			return "redirect:/login";
		}
		
		MemberSessionUtils.setLoginUserInfo(session, request);
		request.setAttribute("btnType", "routineCreate");
		
		String memberId = MemberSessionUtils.getLoginMemberId(session);
		
		RoutineManager routineManager = RoutineManager.getInstance();
		ChoiceManager choiceManager = ChoiceManager.getInstance();
		
		int routineId = Integer.parseInt(request.getParameter("routineId"));
		
		if (request.getParameter("thisIsForDel") != null) {
			choiceManager.deleteChoice(routineId);
			routineManager.deleteRoutine(routineId);
			
			return "redirect:/routine/list";
		}
		
		if (request.getParameter("thisIsForUsage") != null) {
			request.setAttribute("thisIsForUsage", "thisIsForUsage");
		}
		
		Routine routine = routineManager.getRoutineById(routineId);
		request.setAttribute("routine", routine);
		
		List<Exercise> exerciseList = routineManager.getExercises(routineId);
		request.setAttribute("exerciseList", exerciseList);
		
		if (memberId.equals(routine.getRoutineCreater())) {
			request.setAttribute("isCreater", true);
		}
		else {
			request.setAttribute("isCreater", false);
		}
		
		return "/routine/routine_detail.jsp";
	}

}
