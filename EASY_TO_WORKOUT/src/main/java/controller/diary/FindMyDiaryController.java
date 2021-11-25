package controller.diary;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import service.DiaryServiceImpl;
import service.dto.Diary;
import service.exception.DiaryNotFoundException;
import controller.member.MemberSessionUtils;

public class FindMyDiaryController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = MemberSessionUtils.getLoginMemberId(session);
		
		DiaryServiceImpl service = DiaryServiceImpl.getInstance();
		
		String diaryTitle = request.getParameter("searchMyDiary");
		try {
			List<Diary> diaryList = service.getMyDiaryByTitle(id, diaryTitle);
			request.setAttribute("diaryList", diaryList);
			
			return "/diary/myDiaryPage.jsp";
		} catch (DiaryNotFoundException e) {
			request.setAttribute("findDiaryFailed", true);
			request.setAttribute("exception", e);
			
			return "/diary/myDiaryPage.jsp";
		}
	}
	
}
