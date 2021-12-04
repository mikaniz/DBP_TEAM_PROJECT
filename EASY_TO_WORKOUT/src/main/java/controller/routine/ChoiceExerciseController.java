package controller.routine;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.MemberSessionUtils;
import service.ExerciseManager;
import service.MemberManager;
import service.RoutineManager;
import service.dto.Exercise;
import service.dto.Member;
import service.dto.Routine;
public class ChoiceExerciseController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (!MemberSessionUtils.hasLogined(session)) {
			return "redirect:/login";
		}
		
		MemberSessionUtils.setLoginUserInfo(session, request);
		
		String memberId = MemberSessionUtils.getLoginMemberId(session);
		
		MemberManager memberManager = MemberManager.getInstance();
		Member member = memberManager.findMember(memberId);
		request.setAttribute("member", member);
		
		if (request.getParameter("checkExerciseId") == null) {
			request.setAttribute("exerciseNotSelected", "exerciseNotSelected");
		}
//		else {
			request.setAttribute("routineName", request.getParameter("routineName"));
			request.setAttribute("routinePart", request.getParameterValues("routinePart"));
			request.setAttribute("routineTime", request.getParameter("routineTime"));
			request.setAttribute("routineLevel", request.getParameter("routineLevel"));
			request.setAttribute("routineType", request.getParameter("routineType"));
			
			String[] exerciseIdList = request.getParameterValues("checkExerciseId");
			request.setAttribute("exerciseIdList", exerciseIdList);
			
			String[] sequenceList = request.getParameterValues("sequence");
			request.setAttribute("sequenceList", sequenceList);
			
			String[] repetitionList = request.getParameterValues("repetition");
			request.setAttribute("repetitionList", repetitionList);
			
			
			ExerciseManager exerciseManager = ExerciseManager.getInstance();
			List<Exercise> exerciseList = new ArrayList<Exercise>();
			
			for (String exerciseId : exerciseIdList)  {
				Exercise exercise = exerciseManager.getExercise(Integer.parseInt(exerciseId));
				exerciseList.add(exercise);
			}
			request.setAttribute("exerciseList", exerciseList);
			
//			if (request.getParameter("thisIsForChoice") == null) {
//				RoutineManager routineManager = RoutineManager.getInstance();
//				int routineId = Integer.parseInt(request.getParameter("routineId"));
//				Routine routine = routineManager.getRoutineById(routineId);
//				request.setAttribute("routine", routine);
//				
//				return "/routine/routine_updateForm.jsp";
//			}
//		}
		if (request.getParameter("thisIsForChoice") == null) {
			RoutineManager routineManager = RoutineManager.getInstance();
			int routineId = Integer.parseInt(request.getParameter("routineId"));
			Routine routine = routineManager.getRoutineById(routineId);
			request.setAttribute("routine", routine);
			
			return "/routine/routine_updateForm.jsp";
		}
				
		return "/routine/routine_createForm.jsp";
	}

}
