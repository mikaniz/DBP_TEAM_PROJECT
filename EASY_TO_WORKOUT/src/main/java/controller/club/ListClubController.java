package controller.club;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import controller.Controller;
import service.ClubServiceImpl;
import service.dto.ClubDTO;

public class ListClubController implements Controller {
	
	//private static final Logger log = LoggerFactory.getLogger(ListClubController.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ClubServiceImpl service = ClubServiceImpl.getInstance();
		
		if (request.getMethod().equals("GET")) {
			List<ClubDTO> clubList = service.listingClub();
			request.setAttribute("clubList",  clubList);
			
			return "/club/clubPage.jsp";
		}
		
		//log.debug("sort club : {}", request.getParameter("sortClub"));
		
		/* 모임 목록 정렬 */
		if (request.getParameter("sortClub").equals("1")) { // 이름순
			request.setAttribute("checkedOne", true);
			
			List<ClubDTO> clubList = service.getSortedClub("clubName");
			request.setAttribute("clubList",  clubList);
		}
		else if (request.getParameter("sortClub").equals("2")) { // 회원순
			request.setAttribute("checkedTwo", true);
			
			List<ClubDTO> clubList = service.getSortedClub("countMember");
			request.setAttribute("clubList",  clubList);
		}
		else if (request.getParameter("sortClub").equals("3")) { // 자유가입만
			request.setAttribute("checkedThree", true);
			
			List<ClubDTO> clubList = service.getSortedClub("freeSignUp");
			request.setAttribute("clubList",  clubList);
		}
		
		return "/club/clubPage.jsp";
	}

}
