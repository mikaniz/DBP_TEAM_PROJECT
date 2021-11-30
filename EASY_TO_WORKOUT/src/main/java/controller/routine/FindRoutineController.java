package controller.routine;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.MemberSessionUtils;
import service.RoutineManager;
import service.dto.Routine;
import service.exception.ExistingPartException;


public class FindRoutineController implements Controller{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (!MemberSessionUtils.hasLogined(session)) {
			return "redirect:/login";
		}
		
		MemberSessionUtils.setLoginUserInfo(session, request);
		
		RoutineManager manager = RoutineManager.getInstance();
		
		String routinePart = request.getParameter("searchRoutine");
		
		if (request.getParameter("thisIsForUsage") != null) {
			request.setAttribute("creationDate", request.getParameter("creationDate"));
			request.setAttribute("contactAddress", request.getParameter("contactAddress"));
			request.setAttribute("notice", request.getParameter("notice"));
			
			List<Routine> routineList = manager.getRoutineByPart(routinePart);
			request.setAttribute("routineList", routineList);
			
			return "/club/schedule_usageForm.jsp";
		}
		
		request.setAttribute("btnType", "routineCreate");
		
		try {
			List<Routine> routineList = manager.getRoutineByPart(routinePart);
			request.setAttribute("routineList", routineList);
			
			return "/routine/routinePage.jsp";
		}
		catch(ExistingPartException e) {
			List<Routine> routineList = manager.ListingRoutines();
			request.setAttribute("routineList", routineList);
			
			request.setAttribute("findRoutineFailed", true);
			request.setAttribute("exception", e);
			
			return "/routine/routinePage.jsp";
		}
	
	}

}
