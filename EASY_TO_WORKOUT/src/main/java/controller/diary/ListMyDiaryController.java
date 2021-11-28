package controller.diary;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import service.DiaryManager;
import service.dto.Diary;
import controller.member.MemberSessionUtils;

public class ListMyDiaryController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = MemberSessionUtils.getLoginMemberId(session);

		DiaryManager manager = DiaryManager.getInstance();
		
		if (request.getMethod().equals("GET")) {
			List<Diary> diaryList = manager.getSortedMyDiary(id, "date");
			request.setAttribute("diaryList", diaryList);
			return "/diary/myDiaryPage.jsp";
		}
		
		if (request.getParameter("sortDiary").equals("1")) {
			request.setAttribute("checkedOne", true);
			
			List<Diary> diaryList = manager.getSortedMyDiary(id, "date");
			request.setAttribute("diaryList", diaryList);
		}
		else if (request.getParameter("sortDiary").equals("2")) {
			request.setAttribute("checkedTwo", true);
			
			List<Diary> diaryList = manager.getSortedMyDiary(id, "workTime");
			request.setAttribute("diaryList", diaryList);
		}
		
		return "/diary/myDiaryPage.jsp";
	}

}
