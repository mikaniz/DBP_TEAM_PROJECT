package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import service.MemberManager;
import service.dto.Member;
import service.exception.PasswordMismatchException;

public class UpdateMemberController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if (!MemberSessionUtils.hasLogined(session)) {
			return "redirect:/login";
		}
		
		MemberSessionUtils.setLoginUserInfo(session, request);
		
		if (request.getMethod().equals("GET")) {
			return "/member/member_updateForm.jsp";
		}
		
		try {
			MemberManager manager = MemberManager.getInstance();
			Member member = manager.findMember(request.getParameter("id"));
			Member update = manager.findMember(request.getParameter("id"));
	
			update.setPw(request.getParameter("updatePw"));
			update.setPhone(request.getParameter("phone"));
			update.setEmail(request.getParameter("email"));
			
			manager.update(member, update, request.getParameter("pw"));
			
			return "redirect:/main";
		} catch (PasswordMismatchException e) {
			request.setAttribute("updateFailed", true);
			request.setAttribute("exception", e);
			return "/member/member_updateForm.jsp";
		}
	}

}
