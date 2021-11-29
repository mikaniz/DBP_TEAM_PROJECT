package controller.club;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.member.MemberSessionUtils;
import service.ClubManager;
import service.dto.Club;
import service.exception.CannotOpenClubException;

public class OpenClubController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(OpenClubController.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub		
		HttpSession session = request.getSession();
		if (!MemberSessionUtils.hasLogined(session)) {
			return "redirect:/login";
		}
		
		String id = MemberSessionUtils.getLoginMemberId(session);
		
		Club club = new Club();
		club.setClubMaster(id);
		club.setClubName(request.getParameter("clubName"));
		club.setOpenCycle(request.getParameter("clubOpenCycle"));
		club.setSignUp(request.getParameter("clubSignUp"));
		club.setClubIntro(request.getParameter("clubIntro"));
		
		log.debug("Create club : {}", club);
		
		try {
			ClubManager manager = ClubManager.getInstance();
			manager.insertClub(id, club);
			
			return "redirect:/club/list";
		} catch(CannotOpenClubException e) {
			// 예외 발생 시 모임 개설 form으로 forwarding
			request.setAttribute("openClubFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("club", club);
			
			return "/club/club_openForm.jsp";
		}
		
	}

}
