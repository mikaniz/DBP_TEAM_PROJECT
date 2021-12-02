package controller.diary;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Timestamp;

import controller.Controller;
import service.DiaryManager;
import service.dto.Diary;
import controller.member.MemberSessionUtils;

public class WriteDiaryController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if (!MemberSessionUtils.hasLogined(session)) {
			return "redirect:/login";
		}
		
		MemberSessionUtils.setLoginUserInfo(session, request);
		
    	String title = request.getParameter("diaryTitle");
		int workTime = Integer.parseInt(request.getParameter("workTime"));
		String contents = request.getParameter("diaryContents");
		
		Diary diary = new Diary();
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
            String author = MemberSessionUtils.getLoginMemberId(session);
            diary.setAuthor(author);
            
			DiaryManager manager = DiaryManager.getInstance();
			manager.create(diary);
            
            return "redirect:/diary/my/list";			
		} catch (Exception e) {
            return "redirect:/diary/write";
		}
	}

}
