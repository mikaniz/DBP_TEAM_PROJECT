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
    
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    
    	mappings.put("/login", new ForwardController("/member/memberLogin.jsp"));
    	
    	mappings.put("/main", new MainController());
    	
    	mappings.put("/", new ForwardController("index.jsp"));
    	mappings.put("/member/login", new LoginController()); 
    	mappings.put("/member/logout", new LogoutController());
    	mappings.put("/member/join/form", new ForwardController("/member/member_joinForm.jsp"));
    	mappings.put("/member/join", new JoinController());
    	mappings.put("/member/update", new UpdateMemberController());
    	
    	
    	mappings.put("/diary/all/list", new ListAllDiaryController());
    	mappings.put("/diary/my/list", new ListMyDiaryController());
    	mappings.put("/diary/detail", new DetailDiaryController());
    	mappings.put("/diary/update", new UpdateDiaryController());
    	mappings.put("/diary/write", new ForwardController("/diary/diary_writeForm.jsp"));
    	mappings.put("/diary/create", new WriteDiaryController());
    	mappings.put("/diary/all/find", new FindAllDiaryController());
    	mappings.put("/diary/my/find", new FindMyDiaryController());
    	
    	
    	mappings.put("/club/open/form", new ForwardController("/club/club_openForm.jsp"));
    	mappings.put("/club/open", new OpenClubController());
    	mappings.put("/club/list", new ListClubController());
    	mappings.put("/club/detail", new DetailClubController());
    	mappings.put("/club/schedule/create/form", new ForwardController("/club/schedule_createForm.jsp"));
    	mappings.put("/club/schedule/create", new CreateScheduleController());
    	mappings.put("/club/schedule/view", new ViewScheduleController());
    	mappings.put("/club/schedule/usage", new UsageRoutineController());
    	mappings.put("/club/join", new JoinClubController());
    	mappings.put("/club/find", new FindClubController());
    	
    	
    	mappings.put("/routine/list", new ListRoutineController());
    	mappings.put("/routine/detail", new DetailRoutineController());
//    	mappings.put("/routine/create/form", new ForwardController("/routine/routine_createForm.jsp"));
//    	mappings.put("/routine/create", new CreateRoutineController());
    	mappings.put("/routine/create", new CreateRoutineController());
//    	mappings.put("/routine/update/form", new ForwardController("/routine/routine_updateForm.jsp"));
//    	mappings.put("/routine/update", new UpdateRoutineController());
    	mappings.put("/routine/update", new UpdateRoutineController());
    	mappings.put("/routine/find", new FindRoutineController());
        
    	
    	mappings.put("/exercise/list", new ListExerciseController());
    	mappings.put("/exercise/detail", new DetailExerciseController());
    	mappings.put("/exercise/find", new FindExerciseController());
    	mappings.put("/exercise/choice", new ChoiceExerciseController());
    	mappings.put("/exercise/choice/form", new ForwardController("/routine/exercise_choiceForm.jsp"));
    	
        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {
        return mappings.get(uri);
    }
}