package controller.diary;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import service.DiaryServiceImpl;
import service.dto.DiaryDTO;
import service.exception.DiaryNotFoundException;

public class FindAllDiaryController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DiaryServiceImpl service = DiaryServiceImpl.getInstance();
		
		String diaryTitle = request.getParameter("searchAllDiary");
		try {
			List<DiaryDTO> diaryList = service.getAllDiaryByTitle(diaryTitle);
			request.setAttribute("diaryList", diaryList);
			
			return "/diary/allDiaryPage.jsp";
		} catch (DiaryNotFoundException e) {
			request.setAttribute("findDiaryFailed", true);
			request.setAttribute("exception", e);
			
			return "/diary/allDiaryPage.jsp";
		}
	}
	
}
