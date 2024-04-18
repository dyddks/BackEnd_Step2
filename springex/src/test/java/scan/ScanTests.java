//package scan;
//
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.zerock.springex.AutoAppConfig;
//import org.zerock.springex.service.TodoService;
//
//
//@Log4j2
//public class ScanTests {
//    @Test
//    void basicScan() {
//        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
//        TodoService todoService = ac.getBean(TodoService.class);
//
//       log.info(todoService);
//    }
//}
