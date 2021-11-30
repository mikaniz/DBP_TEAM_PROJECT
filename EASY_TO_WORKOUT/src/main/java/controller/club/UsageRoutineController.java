package controller.club;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.MemberSessionUtils;
import service.RoutineManager;
import service.dto.Routine;

public class UsageRoutineController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(UsageRoutineController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (!MemberSessionUtils.hasLogined(session)) {
			return "redirect:/login";
		}
		
		MemberSessionUtils.setLoginUserInfo(session, request);
		
		// 작성 값 유지 위해
		log.debug("usage checked routine : {}, {}, {}", request.getParameter("notice"), request.getParameter("creationDate"), request.getParameter("contactAddress"));
		request.setAttribute("creationDate", request.getParameter("creationDate"));
		request.setAttribute("contactAddress", request.getParameter("contactAddress"));
		request.setAttribute("notice", request.getParameter("notice"));
		
		RoutineManager routineManager = RoutineManager.getInstance();
		
		List<Routine> routineList = routineManager.ListingRoutines();
		request.setAttribute("routineList", routineList);
				
		return "/club/schedule_usageForm.jsp";
	}

}
