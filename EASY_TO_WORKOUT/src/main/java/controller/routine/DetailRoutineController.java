package controller.routine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.MemberSessionUtils;
import service.RoutineManager;
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
		
		RoutineManager manager = RoutineManager.getInstance();
		
		int routineId = Integer.parseInt(request.getParameter("routineId"));
		
		if (request.getParameter("thisIsForDel") != null) {
			manager.deleteRoutine(routineId);
			
			return "redirect:/routine/list";
		}
		
		Routine routine = manager.getRoutine(routineId);
		request.setAttribute("routine", routine);
		
		List<Exercise> exerciseList = manager.getExercises(routineId);
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
