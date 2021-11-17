package controller.diary;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Timestamp;

import controller.Controller;
import service.DiaryServiceImpl;
import service.dto.DiaryDTO;
import controller.member.MemberSessionUtils;

public class WriteDiaryController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String title = request.getParameter("diaryTitle");
		int workTime = Integer.parseInt(request.getParameter("workTime"));
		String contents = request.getParameter("diaryContents");
		
		DiaryDTO diary = new DiaryDTO();
		diary.setTitle(title);
		diary.setWorkTime(workTime);
		diary.setContents(contents);
		if (request.getParameter("isPrivate") == null) {
			diary.setIsPrivate(0);
		}
		else {
			diary.setIsPrivate(1);
		}
		diary.setDate(new Timestamp(System.currentTimeMillis()));
		
		try {
			HttpSession session = request.getSession();
            String author = MemberSessionUtils.getLoginMemberId(session);
            diary.setAuthor(author);
            
			DiaryServiceImpl manager = DiaryServiceImpl.getInstance();
			manager.create(diary);
            
            return "redirect:/diary/all/list";			
		} catch (Exception e) {
            return "redirect:/diary/write";
		}
	}

}
