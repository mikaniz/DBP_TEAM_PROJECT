package controller.club;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import service.ClubServiceImpl;
import service.dto.Club;
import service.exception.ExistingClubException;

public class OpenClubController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(OpenClubController.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub				
		Club club = new Club();
		club.setClubMaster(request.getParameter("clubMasterId"));
		club.setClubName(request.getParameter("clubName"));
		club.setOpenCycle(request.getParameter("clubOpenCycle"));
		club.setSignUp(request.getParameter("clubSignUp"));
		club.setClubIntro(request.getParameter("clubIntro"));
		
		log.debug("Create club : {}", club);
		
		try {
			ClubServiceImpl service = ClubServiceImpl.getInstance();
			service.insertClub(club);
			
			return "redirect:/club/list";
		} catch(ExistingClubException e) {
			// 예외 발생 시 모임 개설 form으로 forwarding
			request.setAttribute("openFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("club", club);
			
			return "/club/club_openForm.jsp";
		}
		
	}

}
