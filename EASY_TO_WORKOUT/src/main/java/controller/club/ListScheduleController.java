package controller.club;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.MemberSessionUtils;
import service.ClubScheduleManager;
import service.dto.ClubSchedule;

public class ListScheduleController implements Controller  {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (!MemberSessionUtils.hasLogined(session)) {
			return "redirect:/login";
		}
		
		MemberSessionUtils.setLoginUserInfo(session, request);
		
		ClubScheduleManager scheduleManager = ClubScheduleManager.getInstance();
		
		int clubId = Integer.parseInt(request.getParameter("clubId"));
		List<ClubSchedule> scheduleList = scheduleManager.getClubScheduleListById(clubId);
		request.setAttribute("scheduleList", scheduleList);
		request.setAttribute("clubId", clubId);
		request.setAttribute("clubName", request.getParameter("clubName"));
		
		
		return "/club/schedule_list.jsp";
	}

}
