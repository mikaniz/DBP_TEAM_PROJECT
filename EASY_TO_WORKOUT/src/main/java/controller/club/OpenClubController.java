package controller.club;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.member.MemberSessionUtils;
import service.ClubManager;
import service.MembershipManager;
import service.dto.Club;
import service.dto.Membership;
import service.exception.CannotOpenClubException;

public class OpenClubController implements Controller {
//	private static final Logger log = LoggerFactory.getL(OpenClubController.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub		
		HttpSession session = request.getSession();
		if (!MemberSessionUtils.hasLogined(session)) {
			return "redirect:/login";
		}
		
		MemberSessionUtils.setLoginUserInfo(session, request);
		String memberId = MemberSessionUtils.getLoginMemberId(session);
		
		Club club = new Club();
		club.setClubMaster(memberId);
		club.setClubName(request.getParameter("clubName"));
		club.setOpenCycle(request.getParameter("clubOpenCycle"));
		club.setSignUp(request.getParameter("clubSignUp"));
		club.setClubIntro(request.getParameter("clubIntro"));
		
		MembershipManager membershipManager = MembershipManager.getInstance();
		ClubManager manager = ClubManager.getInstance();
		
		try {
			manager.insertClub(memberId, club);	// 모임 개설
			
			Membership membership = new Membership();
			int clubId = manager.getCurrentClubId(club);
			membership.setClubId(clubId);
			membership.setMemberId(memberId);
			membership.setSubDate(new Timestamp(System.currentTimeMillis()));
			
			membershipManager.insertMembership(membership);	// 개설자 membership에 추가
			
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
