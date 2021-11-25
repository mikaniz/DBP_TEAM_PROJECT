package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.member.*;
import controller.diary.*;
import controller.club.*;
import controller.routine.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
    	mappings.put("/main", new ForwardController("/mainPage.jsp"));
    	mappings.put("/club", new ForwardController("/club/clubPage.jsp"));
    	mappings.put("/routine", new ForwardController("/routine/routinePage.jsp"));
    	mappings.put("/diary/all", new ForwardController("/diary/allDiaryPage.jsp"));
    	mappings.put("/diary/my", new ForwardController("/diary/myDiaryPage.jsp"));
    	mappings.put("/login", new ForwardController("/member/memberLogin.jsp"));
    	// 사용자 관리
    	mappings.put("/", new ForwardController("index.jsp"));
    	mappings.put("/member/login", new LoginController()); 
    	mappings.put("/member/join/form", new ForwardController("/member/member_joinForm.jsp"));
    	mappings.put("/member/join", new JoinController());
//    	mappings.put("/member/update/form", new ForwardController("/member/member_updateForm.jsp"));
//    	mappings.put("/member/update", new ForwardController("/mainPage.jsp"));
    	mappings.put("/member/update", new UpdateMemberController());
    	
    	// 다이어리 관리
    	mappings.put("/diary/all/list", new ListAllDiaryController());
    	mappings.put("/diary/my/list", new ListMyDiaryController());
    	mappings.put("/diary/detail", new DetailDiaryController());
//    	mappings.put("/diary/update/form", new ForwardController("/diary/update_form.jsp"));
//    	mappings.put("/diary/update", new UpdateDiaryController());
    	mappings.put("/diary/update", new UpdateDiaryController());
    	mappings.put("/diary/write", new ForwardController("/diary/diary_writeForm.jsp"));
    	mappings.put("/diary/create", new WriteDiaryController());
    	mappings.put("/diary/all/find", new FindAllDiaryController());
    	mappings.put("/diary/my/find", new FindMyDiaryController());
    	
    	// 모임 관리
    	mappings.put("/club/open/form", new ForwardController("/club/club_openForm.jsp"));
    	mappings.put("/club/open", new OpenClubController());
    	mappings.put("/club/list", new ListClubController());
    	mappings.put("/club/detail", new DetailClubController());
    	mappings.put("/club/schedule/create/form", new ForwardController("/club/schedule_createForm.jsp"));
    	mappings.put("/club/schedule/create", new CreateScheduleController());
    	mappings.put("/club/schedule/view", new ViewScheduleController());
    	mappings.put("/club/join", new JoinClubController());
    	mappings.put("/club/find", new FindClubController());
    	
    	// 루틴 관리
    	mappings.put("/routine/list", new ListRoutineController());
    	mappings.put("/routine/detail", new DetailRoutineController());
    	mappings.put("/routine/create/form", new ForwardController("/routine/routine_createForm.jsp"));
    	mappings.put("/routine/create", new CreateRoutineController());
//    	mappings.put("/routine/update/form", new ForwardController("/routine/routine_updateForm.jsp"));
//    	mappings.put("/routine/update", new UpdateRoutineController());
    	mappings.put("/routine/update", new UpdateRoutineController());
    	mappings.put("/routine/find", new FindRoutineController());
        
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}