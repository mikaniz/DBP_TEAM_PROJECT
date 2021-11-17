package controller.diary;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import service.DiaryServiceImpl;
import service.dto.DiaryDTO;
import controller.member.MemberSessionUtils;

public class ListMyDiaryController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = MemberSessionUtils.getLoginMemberId(session);

		DiaryServiceImpl manager = DiaryServiceImpl.getInstance();
		
		if (request.getMethod().equals("GET")) {
			List<DiaryDTO> diaryList = manager.findMyDiaryList(id);
			request.setAttribute("diaryList", diaryList);
			return "/diary/myDiaryPage.jsp";
		}
		
		if (request.getParameter("sortDiary").equals("1")) {
			request.setAttribute("checkedOne", true);
			
			List<DiaryDTO> diaryList = manager.getSortedMyDiary(id, "date");
			request.setAttribute("diaryList", diaryList);
		}
		else if (request.getParameter("sortDiary").equals("2")) {
			request.setAttribute("checkedTwo", true);
			
			List<DiaryDTO> diaryList = manager.getSortedMyDiary(id, "workTime");
			request.setAttribute("diaryList", diaryList);
		}
		
		return "/diary/myDiaryPage.jsp";
	}

}
