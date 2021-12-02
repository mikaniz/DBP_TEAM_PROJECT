package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.member.MemberSessionUtils;
import service.ClubManager;
import service.ClubScheduleManager;
import service.dto.Club;
import service.dto.ClubSchedule;

public class MainController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (!MemberSessionUtils.hasLogined(session)) {
			return "redirect:/login";
		}
		
		MemberSessionUtils.setLoginUserInfo(session, request);
		String memberId = MemberSessionUtils.getLoginMemberId(session);
		
		ClubScheduleManager scheduleManager = ClubScheduleManager.getInstance();
		List<ClubSchedule> scheduleList = scheduleManager.getScheduleByMemberId(memberId);
	
		request.setAttribute("scheduleList", scheduleList);
		
		List<Club> clubList = new ArrayList<Club> ();
		for (ClubSchedule schedule : scheduleList) {
			ClubManager clubManager = ClubManager.getInstance();
			Club club = clubManager.getClubById(schedule.getClubId());
			clubList.add(club);
		}
		request.setAttribute("clubList", clubList);
		
		return "/mainPage.jsp";
	}
}
