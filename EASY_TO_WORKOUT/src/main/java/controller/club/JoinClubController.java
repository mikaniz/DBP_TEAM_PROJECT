package controller.club;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.member.MemberSessionUtils;
import service.ClubManager;
import service.MembershipManager;
import service.dto.Club;
import service.dto.Membership;
import service.exception.AlreadyInClubException;

public class JoinClubController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(JoinClubController.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (!MemberSessionUtils.hasLogined(session)) {
			return "redirect:/login";
		}

		String memberId = MemberSessionUtils.getLoginMemberId(session);
		int clubId = Integer.parseInt(request.getParameter("clubId"));
		
		log.debug("join club : {}", request.getParameter("clubId"));
		log.debug("join club : {}", memberId);
//		log.debug("join club : {}", request.getParameter("thisIsForJoin"));
		
		// clubDAO에서 getClubById => setAttribute("club", club)
			ClubManager clubManager = ClubManager.getInstance();
			Club club = clubManager.getClubById(clubId);
			request.setAttribute("club", club);
			
			
		// 모임 가입
		if (request.getParameter("thisIsForJoin").equals("thisIsForJoin")) {
			try {
				MembershipManager membershipManager = MembershipManager.getInstance();
	
				Membership membership = new Membership();
				membership.setClubId(clubId);
				membership.setMemberId(memberId);
				membership.setSubDate(new Timestamp(System.currentTimeMillis()));
				
				membershipManager.insertMembership(membership);
				
				request.setAttribute("successJoin", "successJoin");	// 모임 가입 성공하면 alert창 띄우기 위함
			} catch(AlreadyInClubException e) {
				request.setAttribute("joinClubFailed", true);
				request.setAttribute("exception", e);
				
				return "/club/club_detail.jsp";
			}
			
		}
		
		return "/club/club_detail.jsp";
	}

}
