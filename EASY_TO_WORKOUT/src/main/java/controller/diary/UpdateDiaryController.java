package controller.diary;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import service.DiaryManager;
import service.dto.Diary;

public class UpdateDiaryController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DiaryManager manager = DiaryManager.getInstance();
		
		if (request.getMethod().equals("GET")) {
			int diaryId = Integer.parseInt(request.getParameter("diaryId"));
			Diary diary = manager.getDiaryById(diaryId);
			
			request.setAttribute("diary", diary);
			
			return "/diary/diary_updateForm.jsp";
		}
		
		Diary updateDiary = new Diary();
		updateDiary.setDiaryId(Integer.parseInt(request.getParameter("diaryId")));
		updateDiary.setTitle(request.getParameter("diaryTitle"));
		updateDiary.setWorkTime(Integer.parseInt(request.getParameter("workTime")));
		updateDiary.setContents(request.getParameter("diaryContents"));
		if (request.getParameter("isPrivate") == null) {
			updateDiary.setIsPrivate(0);
		}
		else {
			updateDiary.setIsPrivate(1);
		}
		
		manager.update(updateDiary);
		
		return "redirect:/diary/all/list";
	}

}
