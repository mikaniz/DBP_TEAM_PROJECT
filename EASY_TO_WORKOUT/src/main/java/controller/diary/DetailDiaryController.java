package controller.diary;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import service.DiaryManager;
import service.dto.Diary;
import controller.member.MemberSessionUtils;

public class DetailDiaryController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String memberId = MemberSessionUtils.getLoginMemberId(session);
		
		DiaryManager manager = DiaryManager.getInstance();

		int diaryId = Integer.parseInt(request.getParameter("diaryId"));
		
		if (request.getParameter("thisIsForDel") != null) {
			manager.delete(diaryId);
			
			return "redirect:/diary/all/list";
		}

		Diary diary = manager.getDiaryById(diaryId);
		
		request.setAttribute("diary", diary);
		if (memberId.equals(diary.getAuthor())) {
			request.setAttribute("isAuthor", true);
		} else {
			request.setAttribute("isAuthor", false);
		}
		
		return "/diary/diary_detail.jsp";
	}

}
