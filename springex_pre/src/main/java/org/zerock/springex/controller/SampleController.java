package org.zerock.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.TodoDto;

import java.time.LocalDate;

@Controller
@Log4j2
public class SampleController {
    @GetMapping("/hello")
    public void hello() {
        log.info("hello");

        // 리턴이 없으면 자동으로 /WEB-INF/views/hello.jsp로 이동하게 된다.
    }

    @GetMapping("/ex1")
    public void ex1(String name, int age) {
        log.info("ex1");
        log.info("name" + name);
        log.info("age" + age);
    }

    @GetMapping("/ex2")
    public void ex2(@RequestParam(name="name", defaultValue = "AAA") String name,
                    @RequestParam(name="age", defaultValue = "20") int age) {
        log.info("ex2");
        log.info("name" + name);
        log.info("age" + age);

        /*
            이처리가 끝나고 /WEB-INF/views/ex2.jsp로 이동할때
            String name과 int age는 request의 공간에 자동으로 저장 공유 된다.
         */
    }

    /*
        웹에서 날짜 정보는 String으로 전달된다.
        이 때 포맷 변환이 필요하다.

        그래서 메서드 parameter를 String dueDate로 한 다음에
        java 변환 코드로 처리하는 방법이 있지만,
        스프링은 이것을 보다 편하게 하도록 도와준다.
     */
    @GetMapping("/ex3")
    public void ex3(LocalDate dueDate) {
        log.info("ex3");
        log.info("dueDtae: " + dueDate);
    }

    @GetMapping("/ex4")
    public void ex4(Model model){
        log.info("--------------------------");

        // model에 추가한 값을 꺼내서 request에 저장한다.
        model.addAttribute("message", "Hello World");
    }

    /*
        웹에서 수신된 parameter들은 todoDto의 동일한 필드에 저장되고
        todoDto라는 이름으로 request에 전달된다.
        name이라는 이름으로 Albert가 request에 전달된다.

        request에 전달된다는 의미는 결국 jsp에서 꺼내어 쓸 수 있다는 의미이다.
     */
    @GetMapping("/ex4_1")
    public void ex4_1(TodoDto todoDto, Model model){
        log.info(todoDto);

        // model에 추가한 값을 꺼내서 request에 저장한다.
        model.addAttribute("name", "Albert");
    }

    @GetMapping("/ex4_2")
    public void ex4_2(@ModelAttribute("dto") TodoDto todoDto, Model model){
        log.info(todoDto);

        // model에 추가한 값을 꺼내서 request에 저장한다.
        model.addAttribute("name", "Albert");
    }

    @GetMapping("/ex5")
    public String ex5(RedirectAttributes redirectAttributes){
        // 리다이렉션 시 필요한 정보를 전달하는 것
        redirectAttributes.addAttribute("name", "ABC");
        // 1회성 데이터
        redirectAttributes.addFlashAttribute("result", "success");

        // 리턴 값이 존재하면  jsp로 전달되는 것이 아니고
        // 브라우저한테 아래 주소로 재접속을 하도록 하는 것
        return "redirect:/ex6";
    }

    @GetMapping("/ex6")
    public void ex6(){
        
    }

    @GetMapping("/ex7")
    public void ex7(String p1, int p2){
        log.info("p1........." + p1);
        log.info("p2........." + p2);
    }
}
