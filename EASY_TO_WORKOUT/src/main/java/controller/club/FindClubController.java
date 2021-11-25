package controller.club;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import service.ClubManager;
import service.dto.Club;
import service.exception.ExistingClubException;

public class FindClubController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ClubManager service = ClubManager.getInstance();
		
		String clubName = request.getParameter("searchClub");
		try {
			List<Club> clubList = service.getClubByName(clubName);
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
