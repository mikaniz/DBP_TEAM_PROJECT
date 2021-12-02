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
import service.UsageManager;
import service.dto.ClubSchedule;
import service.dto.Routine;
import service.dto.Usage;

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
		
/* schedule_createForm.jsp -> CreateScheduleController (스케줄 등록 완료)	*/
//		schedule insert		
		int clubId = Integer.parseInt(request.getParameter("clubId"));
		String contactAddress = request.getParameter("contactAddress");
		String creationDate = request.getParameter("creationDate");
		String notice = request.getParameter("notice");
		
		ClubSchedule schedule = new ClubSchedule();
		schedule.setClubId(clubId);		schedule.setContactAddress(contactAddress);
		schedule.setCreationDate(creationDate); 	schedule.setNotice(notice);
	
		ClubScheduleManager scheduleManager = ClubScheduleManager.getInstance();
		scheduleManager.insertClubSchedule(schedule);

// 		Usage insert
		UsageManager usageManager = UsageManager.getInstance();
		Usage usage = null;
		
		int scheduleId = scheduleManager.getCurrentScheduleId(schedule);
		String[] routineIdList = request.getParameterValues("routineIdList");
		for (String routineId : routineIdList) {
			usage = new Usage();
			usage.setClubId(clubId);	
			usage.setRoutineId(Integer.parseInt(routineId));
			usage.setScheduleId(scheduleId);

			usageManager.insertUsage(usage);
		}
		
		return "redirect:/club/list";
	}

}
