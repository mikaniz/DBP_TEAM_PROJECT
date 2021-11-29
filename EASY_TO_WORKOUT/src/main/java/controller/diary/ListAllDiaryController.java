package controller.diary;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.MemberSessionUtils;
import service.DiaryManager;
import service.dto.Diary;

public class ListAllDiaryController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if (!MemberSessionUtils.hasLogined(session)) {
			return "redirect:/login";
		}
		
		MemberSessionUtils.setLoginUserInfo(session, request);
		request.setAttribute("btnType", "diaryWrite");
		
		DiaryManager manager = DiaryManager.getInstance();
		
		if (request.getMethod().equals("GET")) {
			List<Diary> diaryList = manager.getSortedAllDiary("date");
			request.setAttribute("diaryList", diaryList);
			return "/diary/allDiaryPage.jsp";
		}
		
		if (request.getParameter("sortDiary").equals("1")) {
			request.setAttribute("checkedOne", true);
			
			List<Diary> diaryList = manager.getSortedAllDiary("date");
			request.setAttribute("diaryList", diaryList);
		}
		else if (request.getParameter("sortDiary").equals("2")) {
			request.setAttribute("checkedTwo", true);
			
			List<Diary> diaryList = manager.getSortedAllDiary("workTime");
			request.setAttribute("diaryList", diaryList);
		}
		
		return "/diary/allDiaryPage.jsp";
	}

}
