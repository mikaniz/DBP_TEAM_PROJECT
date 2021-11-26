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

public class DetailClubController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(DetailClubController.class);
	
	@Override 
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String id = MemberSessionUtils.getLoginMemberId(session);
		
		ClubManager manager = ClubManager.getInstance();
		
		log.debug("detail club : {}", request.getParameter("clubId"));
		
		int clubId = Integer.parseInt(request.getParameter("clubId"));
		Club club = manager.getClubById(clubId);
		request.setAttribute("club", club);
		
		if (request.getParameter("thisIsForDel") != null) {		// 모임 삭제
			log.debug("delete club : {}", clubId);
			manager.deleteClub(clubId);
			
			return "redirect:/club/list";
		}
		
		if (id.equals(club.getClubMaster())) {	// 모임 개설자 판별
			request.setAttribute("isMaster", "1");
		}
		else 
			request.setAttribute("isMaster", "0");
		
		return "/club/club_detail.jsp";
	}

}
