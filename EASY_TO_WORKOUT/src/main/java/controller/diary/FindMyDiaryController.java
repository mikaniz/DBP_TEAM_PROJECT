package controller.diary;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import service.DiaryManager;
import service.dto.Diary;
import service.exception.DiaryNotFoundException;
import controller.member.MemberSessionUtils;

public class FindMyDiaryController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if (!MemberSessionUtils.hasLogined(session)) {
			return "redirect:/login";
		}
		
		MemberSessionUtils.setLoginUserInfo(session, request);
		request.setAttribute("btnType", "diaryWrite");
		
		String id = MemberSessionUtils.getLoginMemberId(session);
		
		DiaryManager manager = DiaryManager.getInstance();
		
		String diaryTitle = request.getParameter("searchMyDiary");
		try {
			List<Diary> diaryList = manager.getMyDiaryByTitle(id, diaryTitle);
			request.setAttribute("diaryList", diaryList);
			
			return "/diary/myDiaryPage.jsp";
		} catch (DiaryNotFoundException e) {
			request.setAttribute("findDiaryFailed", true);
			request.setAttribute("exception", e);
			
			return "/diary/myDiaryPage.jsp";
		}
	}
	
}
