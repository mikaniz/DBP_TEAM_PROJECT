package controller.diary;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import service.DiaryServiceImpl;
import service.dto.Diary;

public class ListAllDiaryController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DiaryServiceImpl manager = DiaryServiceImpl.getInstance();
		
		if (request.getMethod().equals("GET")) {
			List<Diary> diaryList = manager.findAllDiaryList();
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
