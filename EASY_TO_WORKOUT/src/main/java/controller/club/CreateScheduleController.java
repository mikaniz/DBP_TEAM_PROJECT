package controller.club;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import controller.member.MemberSessionUtils;
import service.ClubScheduleManager;
import service.RoutineManager;
import service.dto.ClubSchedule;
import service.dto.Routine;

public class CreateScheduleController implements Controller {
	private static final Logger log = LoggerFactory.getLogger(CreateScheduleController.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (!MemberSessionUtils.hasLogined(session)) {
			return "redirect:/login";
		}
		
		MemberSessionUtils.setLoginUserInfo(session, request);
		
		if (request.getMethod().equals("GET")) {	// club_detail.jsp -> CreateScheduleController -> schedule_createForm.jsp
			String clubId = request.getParameter("clubId");
			request.setAttribute("clubId", clubId);
			log.debug("Create schedule : {}", clubId);
			
			return "/club/schedule_createForm.jsp";
		}
		
		if (request.getParameter("thisIsForUsage") != null) {	// schedule_usageForm.jsp -> CreateScheduleController
			if (request.getParameter("checkRoutineId") == null)	// 루틴이 선택되지 않았다면
				request.setAttribute("routineNotSelected", "routineNotSelected");
			else {
				request.setAttribute("clubId", request.getParameter("clubId"));	
				request.setAttribute("creationDate", request.getParameter("creationDate"));
				request.setAttribute("contactAddress", request.getParameter("contactAddress"));
				request.setAttribute("notice", request.getParameter("notice"));
				
				String[] routineIdList = request.getParameterValues("checkRoutineId");
				RoutineManager routineManager = RoutineManager.getInstance();
				List<Routine> routineList = new ArrayList<Routine>();
				
				for (String routineId : routineIdList)  {
					log.debug("routine id lists : {}", routineId);
					Routine routine = routineManager.getRoutineById(routineId);
					routineList.add(routine);
				}
				request.setAttribute("routineList", routineList);
			}
			
			return "/club/schedule_createForm.jsp";
		}
		
		// schedule_createForm.jsp -> CreateScheduleController (스케줄 등록 완료)
		ClubSchedule schedule = new ClubSchedule();
		int clubId = Integer.parseInt(request.getParameter("clubId"));
		log.debug("Create schedule : {}", request.getParameter("creationDate"));
		
		schedule.setClubId(clubId);	//log.debug("Create schedule : {}", clubId);
		schedule.setContactAddress(request.getParameter("contactAddress"));
		schedule.setCreationDate(request.getParameter("creationDate")); 
		schedule.setNotice(request.getParameter("notice"));
	
		ClubScheduleManager scheduleManager = ClubScheduleManager.getInstance();
		scheduleManager.insertClubSchedule(schedule);

// 		Usage insert
//		String[] routineIdList = request.getParameterValues("routineIdList");
//		for (String routineId : routineIdList) log.debug("aaaaa : {}", routineId);
		
		return "redirect:/club/list";
	}

}
