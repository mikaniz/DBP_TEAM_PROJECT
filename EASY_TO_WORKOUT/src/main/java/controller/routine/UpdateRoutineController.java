package controller.routine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Controller;
import controller.member.MemberSessionUtils;
import service.RoutineManager;
import service.dto.Routine;

public class UpdateRoutineController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		if (!MemberSessionUtils.hasLogined(session)) {
			return "redirect:/login";
		}
		
		RoutineManager manager = RoutineManager.getInstance();
		
		if (request.getMethod().equals("GET")) {
			int routineId = Integer.parseInt(request.getParameter("routineId"));
			Routine routine = manager.getRoutine(routineId);
			
			request.setAttribute("routine", routine);
			
			return "/routine/routine_updateForm.jsp";
		}
		
		Routine updateRoutine = new Routine();
		updateRoutine.setRoutineId(Integer.parseInt(request.getParameter("routineId")));
		updateRoutine.setrName(request.getParameter("routineName"));
		
		String part = "";
		int num = 0;
		
		if (request.getParameter("routinePart").equals("상체")) {
			if (num == 0) {
				part += "상체 ";
			}
			else {
				part += ", 상체 ";
			}
			num += 1;
		}
		else if (request.getParameter("routinePart").equals("하체")) {
			if (num == 0) {
				part += "하체 ";
			}
			else {
				part += ", 하체 ";
			}
			num += 1;
		}
		else if (request.getParameter("routinePart").equals("복부")) {
			if (num == 0) {
				part += "복부 ";
			}
			else {
				part += ", 복부 ";
			}
			num += 1;
		}
		else if (request.getParameter("routinePart").equals("어깨")) {
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
		updateRoutine.setPart(part);
		
		updateRoutine.setrTime(Integer.parseInt(request.getParameter("routineTime")));
		
		if (request.getParameter("routineLevel") == "1") {
			updateRoutine.setDifficulty(1);
		}
		else if (request.getParameter("routineLevel") == "2") {
			updateRoutine.setDifficulty(2);
		}
		else if (request.getParameter("routineLevel") == "3") {
			updateRoutine.setDifficulty(3);
		}
		else if (request.getParameter("routineLevel") == "4") {
			updateRoutine.setDifficulty(4);
		}
		else {
			updateRoutine.setDifficulty(5);
		}
		
		if (request.getParameter("routineType") == "0") {
			updateRoutine.setrType("0");
		}
		else {
			updateRoutine.setrType("1");
		}
		
		manager.updateRoutine(updateRoutine);
		
		return "redirect:/routine/list";
	}

}
