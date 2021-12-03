package controller;

import java.util.ArrayList;
import java.util.List;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.member.MemberSessionUtils;
import service.ClubManager;
import service.ClubScheduleManager;
import service.dto.Club;
import service.dto.ClubSchedule;
import service.DiaryManager;
import service.dto.Diary;

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
		
		DiaryManager diaryManager = DiaryManager.getInstance();
		List<Diary> diaryList = diaryManager.getSortedMyDiary(memberId, "date");
		List<List<Object>> recordList = new ArrayList<List<Object>>();
		
		if (diaryList != null) {
			int count = 0;
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			for (Diary diary : diaryList) {
				List<Object> record = new ArrayList<Object>();
				record.add(format.format(diary.getDate()));
				record.add(diary.getWorkTime());
				recordList.add(record);
				if (++count >= 7) {
					break;
				}
			}
			request.setAttribute("recordList", recordList);
		}
		
		
		return "/mainPage.jsp";
	}
}
