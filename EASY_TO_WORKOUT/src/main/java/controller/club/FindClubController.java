package controller.club;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.MemberSessionUtils;
import service.ClubManager;
import service.dto.Club;
import service.exception.ExistingClubException;

public class FindClubController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (!MemberSessionUtils.hasLogined(session)) {
			return "redirect:/login";
		}
		
		ClubManager manager = ClubManager.getInstance();
		
		String clubName = request.getParameter("searchClub");
		try {
			List<Club> clubList = manager.getClubByName(clubName);
			request.setAttribute("clubList", clubList);
			
			return "/club/clubPage.jsp";
		}
		catch(ExistingClubException e) {
			request.setAttribute("findClubFailed", true);
			request.setAttribute("exception", e);
			
			return "/club/clubPage.jsp";
		}
	
	}

}
