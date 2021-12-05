package controller.routine;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.MemberSessionUtils;
import service.ChoiceManager;
import service.MemberManager;
import service.RoutineManager;
import service.dto.Choice;
import service.dto.Exercise;
import service.dto.Member;
import service.dto.Routine;

public class UpdateRoutineController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if (!MemberSessionUtils.hasLogined(session)) {
			return "redirect:/login";
		}
		
		MemberSessionUtils.setLoginUserInfo(session, request);
		String memberId = MemberSessionUtils.getLoginMemberId(session);
		
		MemberManager memberManager = MemberManager.getInstance();
		Member member = memberManager.findMember(memberId);
		request.setAttribute("member", member);
		
		request.setAttribute("btnType", "routineCreate");
		
		RoutineManager manager = RoutineManager.getInstance();
		
		if (request.getMethod().equals("GET")) {
			if (request.getParameter("thisIsForStorage") != null) {
				request.setAttribute("storage", "storage");
			}
			request.setAttribute("routineName", request.getParameter("routineName"));
			request.setAttribute("routinePart", request.getParameter("routinePart"));
			request.setAttribute("routineTime", request.getParameter("routineTime"));
			request.setAttribute("routineLevel", request.getParameter("routineLevel"));
			request.setAttribute("routineType", request.getParameter("routineType"));
			
			int routineId = Integer.parseInt(request.getParameter("routineId"));
			Routine routine = manager.getRoutineById(routineId);
			
			request.setAttribute("routine", routine);
			
			List<Exercise> exerciseList = manager.getExercises(routineId);
			request.setAttribute("exerciseList", exerciseList);
			
			return "/routine/routine_updateForm.jsp";
		}
		
		Routine routine = new Routine();
		routine.setRoutineId(Integer.parseInt(request.getParameter("routineId")));
		routine.setrName(request.getParameter("routineName"));
		routine.setRoutineCreater(member.getId());
		
		String[] parts = request.getParameterValues("routinePart");
		String part = "";
		int num = 0;
		
		for (int i = 0; i < parts.length; i++) {
			if (parts[i].equals("상체")) {
				if (num == 0) {
					part += "상체 ";
				}
				else {
					part += ", 상체 ";
				}
				num += 1;
			}
			else if (parts[i].equals("하체")) {
				if (num == 0) {
					part += "하체 ";
				}
				else {
					part += ", 하체 ";
				}
				num += 1;
			}
			else if (parts[i].equals("복부")) {
				if (num == 0) {
					part += "복부 ";
				}
				else {
					part += ", 복부 ";
				}
				num += 1;
			}
			else if (parts[i].equals("어깨")) {
				if (num == 0) {
					part += "어깨 ";
				}
				else {
					part += ", 어깨 ";
				}
				num += 1;
			}
			else {
				if (num == 0) {
					part += "전신 ";
				}
				else {
					part += ", 전신 ";
				}
				num += 1;
			}
		}
		routine.setPart(part);
		
		routine.setrTime(Integer.parseInt(request.getParameter("routineTime"))); 
		routine.setDifficulty(Integer.parseInt(request.getParameter("routineLevel"))); 
		routine.setrType(request.getParameter("routineType"));
		
		manager.updateRoutine(routine);
		
		String[] exerciseIdList = request.getParameterValues("exerciseIdList");
		int exerciseLength = exerciseIdList.length;
		
		String[] sequenceList = request.getParameterValues("sequence");
		
		String[] repetitionList = request.getParameterValues("repetition");
		
		ChoiceManager choiceManager = ChoiceManager.getInstance();
		
		for (int i = 0; i < exerciseLength; i++)  {
			Choice choice = new Choice();
			choice.setRoutineId(Integer.parseInt(request.getParameter("routineId")));
			choice.setExerciseId(Integer.parseInt(exerciseIdList[i]));
			choice.setSequence(Integer.parseInt(sequenceList[i]));
			choice.setRepetition(Integer.parseInt(repetitionList[i]));
			
			Choice findChoice = choiceManager.getChoiceByRoutineIdAndExerciseId(
										Integer.parseInt(request.getParameter("routineId")), choice.getExerciseId());
			
			choiceManager.updateChoice(findChoice);
		}
		
		return "redirect:/routine/list";
	}

}
