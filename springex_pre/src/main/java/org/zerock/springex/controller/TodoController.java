package org.zerock.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zerock.springex.dto.TodoDto;

@Controller
@RequestMapping("/todo")
@Log4j2
public class TodoController {
    @RequestMapping("/list")
    public void list() {
        log.info("list");
    }

    @GetMapping(value = "/register")
    public void register(){
        log.info("todo register.........");
    }

    /*
        웹에서 보내오는 parameter들이
        TodoDto내부의 필드들의 이름과 매칭되면 todoDto
     */
    @PostMapping("/register")
    public void registerPost(TodoDto todoDto){
        log.info("todo register post.........");
        log.info(todoDto);
    }
}
