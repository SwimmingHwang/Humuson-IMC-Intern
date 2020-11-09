package com.example.main.controller;

import com.example.main.dto.MsgsResponseDto;
import com.example.main.service.MsgsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final MsgsService msgsService;

    @GetMapping("/")
    public String index(Model model) {// 서버 템플릿 엔진에서 사용할 수 있는 객체 저장
        //model.addAttribute("msgs", msgsService.findAllDesc());// findalldesc 결과를 posts라는 이름으로 send.must에 전달
        return "index";
// send page
//        model.addAttribute("msgs", msgsService.findAll());// findalldesc 결과를 posts라는 이름으로 send.must에 전달
//        return "page/send";
    }


    @GetMapping("/msgs/save")
    public String msgsSave() {
        return "page/msgs-save";
    }

    @GetMapping("/msgs/update/{id}") // 수정할 화면 연결
    public String msgsUpdate(@PathVariable Integer id, Model model) {
        MsgsResponseDto dto = msgsService.findById(id);
        model.addAttribute("msg", dto);

        return "page/msgs-update";
    }
}
