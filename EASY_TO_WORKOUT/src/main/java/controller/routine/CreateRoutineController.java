package controller.routine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.member.MemberSessionUtils;
import controller.Controller;
import service.dto.Choice;
import service.dto.Member;
import service.dto.Routine;
import service.ChoiceManager;
import service.MemberManager;
import service.RoutineManager;

public class CreateRoutineController implements Controller {

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
		
		if (request.getMethod().equals("GET")) {
			request.setAttribute("routineName", request.getParameter("routineName"));
			request.setAttribute("routinePart", request.getParameter("routinePart"));
			request.setAttribute("routineTime", request.getParameter("routineTime"));
			request.setAttribute("routineLevel", request.getParameter("routineLevel"));
			request.setAttribute("routineType", request.getParameter("routineType"));
			
			return "/routine/routine_createForm.jsp";
		}
		
		Routine routine = new Routine();
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
		
		try {
			RoutineManager routineManager = RoutineManager.getInstance();
			routineManager.insertRoutine(routine);
			
			Routine findRoutine = routineManager.getRoutineByName(request.getParameter("routineName"));
			
			String[] exerciseIdList = request.getParameterValues("exerciseIdList");
			int exerciseLength = exerciseIdList.length;
			
			String[] sequenceList = request.getParameterValues("sequence");
			
			String[] repetitionList = request.getParameterValues("repetition");
			
			ChoiceManager choiceManager = ChoiceManager.getInstance();
			
			for (int i = 0; i < exerciseLength; i++)  {
				Choice choice = new Choice();
				choice.setRoutineId(findRoutine.getRoutineId());
				choice.setExerciseId(Integer.parseInt(exerciseIdList[i]));
				choice.setSequence(Integer.parseInt(sequenceList[i]));
				choice.setRepetition(Integer.parseInt(repetitionList[i]));
				
				choiceManager.insertChoice(choice);
			}
			
			return "redirect:/routine/list";
		}catch (Exception e) {
			request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("routine", routine);
			return "/routine/routine_createForm.jsp";
		}
	}

}
