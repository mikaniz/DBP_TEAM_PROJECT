package controller.routine;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.MemberSessionUtils;
import service.dto.Exercise;
import service.ExerciseManager;
import service.dto.Member;
import service.MemberManager;

public class ListExerciseController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if (!MemberSessionUtils.hasLogined(session)) {
			return "redirect:/login";
		}
		
		MemberSessionUtils.setLoginUserInfo(session, request);
		request.setAttribute("btnType", "routineCreate");
		
		ExerciseManager manager = ExerciseManager.getInstance();
		
		if (request.getMethod().equals("GET")) {
			String memberId = MemberSessionUtils.getLoginMemberId(session);
			
			MemberManager memberManager = MemberManager.getInstance();
			Member member = memberManager.findMember(memberId);
			request.setAttribute("member", member);
			
			List<Exercise> exerciseList = manager.ListingExercises();
			request.setAttribute("exerciseList", exerciseList);
			
			return "/routine/exercise_choiceForm.jsp";
		}
		
		String type = request.getParameter("sortExercise");
		
		if (type.equals("1")) {
			request.setAttribute("checkedOne", true);
			
			List<Exercise> exerciseList = manager.ListingExercises();
			request.setAttribute("exerciseList", exerciseList);
		}
		else if (type.equals("2")) {
			request.setAttribute("checkedTwo", true);
			
			List<Exercise> exerciseList = manager.ListingExercises();
			request.setAttribute("exerciseList", exerciseList);
		}
		else if (type.equals("3")) {
			request.setAttribute("checkedThree", true);
			
			List<Exercise> exerciseList = manager.ListingExercises();
			request.setAttribute("exerciseList", exerciseList);
		}
		
		return "/routine/exercise_choiceForm.jsp";
	}

}
