package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controller;
import service.MemberManager;
import service.dto.Member;
import service.exception.ExistingMemberException;

public class JoinController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Member member = new Member();
		member.setId(request.getParameter("id"));
		member.setPw(request.getParameter("pw"));
		member.setName(request.getParameter("name"));
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));
		member.setGrade("green");
		
		try {
			MemberManager manager = MemberManager.getInstance();
			manager.create(member);
			return "/member/memberLogin.jsp";
		} catch (ExistingMemberException e) {
			request.setAttribute("joinFailed", true);
			request.setAttribute("exception", e);
			return "/member/member_joinForm.jsp";
		}
	}

}
