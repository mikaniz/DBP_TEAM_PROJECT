package controller.diary;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import service.DiaryManager;
import service.dto.Diary;
import service.exception.DiaryNotFoundException;

public class FindAllDiaryController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DiaryManager service = DiaryManager.getInstance();
		
		String diaryTitle = request.getParameter("searchAllDiary");
		try {
			List<Diary> diaryList = service.getAllDiaryByTitle(diaryTitle);
			request.setAttribute("diaryList", diaryList);
			
			return "/diary/allDiaryPage.jsp";
		} catch (DiaryNotFoundException e) {
			request.setAttribute("findDiaryFailed", true);
			request.setAttribute("exception", e);
			
			return "/diary/allDiaryPage.jsp";
		}
	}
	
}
