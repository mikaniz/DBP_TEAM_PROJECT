package controller.diary;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import service.DiaryServiceImpl;
import service.dto.DiaryDTO;

public class ListAllDiaryController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DiaryServiceImpl manager = DiaryServiceImpl.getInstance();
		
		if (request.getMethod().equals("GET")) {
			List<DiaryDTO> diaryList = manager.findAllDiaryList();
			request.setAttribute("diaryList", diaryList);
			return "/diary/allDiaryPage.jsp";
		}
		
		if (request.getParameter("sortDiary").equals("1")) {
			request.setAttribute("checkedOne", true);
			
			List<DiaryDTO> diaryList = manager.getSortedAllDiary("date");
			request.setAttribute("diaryList", diaryList);
		}
		else if (request.getParameter("sortDiary").equals("2")) {
			request.setAttribute("checkedTwo", true);
			
			List<DiaryDTO> diaryList = manager.getSortedAllDiary("workTime");
			request.setAttribute("diaryList", diaryList);
		}
		
		return "/diary/allDiaryPage.jsp";
	}

}
