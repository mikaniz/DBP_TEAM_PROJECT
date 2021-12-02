package controller.club;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.MemberSessionUtils;
import service.ClubScheduleManager;
import service.RoutineManager;
import service.UsageManager;
import service.dto.ClubSchedule;
import service.dto.Routine;
import service.dto.Usage;

public class ViewScheduleController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (!MemberSessionUtils.hasLogined(session)) {
			return "redirect:/login";
		}
		
		MemberSessionUtils.setLoginUserInfo(session, request);
		
		int clubId = Integer.parseInt(request.getParameter("clubId"));
		int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
		
		ClubScheduleManager scheduleManager = ClubScheduleManager.getInstance();
		ClubSchedule schedule = scheduleManager.getClubScheduleById(scheduleId, clubId);
		request.setAttribute("clubName", request.getParameter("clubName"));
		request.setAttribute("clubId", String.valueOf(schedule.getClubId()));
		request.setAttribute("creationDate", schedule.getCreationDate());
		request.setAttribute("contactAddress", schedule.getContactAddress());
		request.setAttribute("notice", schedule.getNotice());
		
		UsageManager usageManager = UsageManager.getInstance();
		List<Usage> usageList = usageManager.getUsageByClubIdAndScheduleId(clubId, scheduleId);
		
		RoutineManager routineManager = RoutineManager.getInstance();
		List<Routine> routineList = new ArrayList<Routine>();
		
		for (Usage usage : usageList) {
			Routine routine = routineManager.getRoutineById(String.valueOf(usage.getRoutineId()));
			routineList.add(routine);
		}
		request.setAttribute("routineList", routineList);
		
		return "/club/schedule_view.jsp";
	}

}
