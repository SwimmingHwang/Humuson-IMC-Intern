package com.humuson.controller;

import com.humuson.dto.AtMsgsResponseDto;
import com.humuson.dto.FtMsgsResponseDto;
import com.humuson.dto.MsgsResponseDto;
import com.humuson.dto.MtMsgsResponseDto;
import com.humuson.service.AtMsgsService;
import com.humuson.service.FtMsgsService;
import com.humuson.service.MsgsService;
import com.humuson.service.MtMsgsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final MsgsService msgsService;
    private final AtMsgsService atMsgsService;
    private final FtMsgsService ftMsgsService;
    private final MtMsgsService mtMsgsService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/send")
    public String send(Model model){// 서버 템플릿 엔진에서 사용할 수 있는 객체 저장
        model.addAttribute("msgs", msgsService.findAll());// findalldesc 결과를 posts라는 이름으로 send.must에 전달
        return "page/send";
    }

    @GetMapping("/login")
    public String login() {// 서버 템플릿 엔진에서 사용할 수 있는 객체 저장
        return "page/login";
    }

    /*
    * 발송 페이지
    * */
    @GetMapping("/send/at-send")
    public String atSend(Model model){
        model.addAttribute("title","알림톡 발송 예약 내역");
        model.addAttribute("msgSbj","at");
        model.addAttribute("msgs", atMsgsService.findAll());
        return "page/atSend";
    }
    @GetMapping("/send/ft-send")
    public String ftSend(Model model){
        model.addAttribute("title","친구톡 발송 예약 내역");
        model.addAttribute("msgSbj","ft");
        model.addAttribute("msgs",ftMsgsService.findAll());
        return "page/ftSend";
    }
    @GetMapping("/send/mt-send")
    public String mtSend(Model model){
        model.addAttribute("title","문자 메시지 발송 예약 내역");
        model.addAttribute("msgSbj","mt");
        model.addAttribute("msgs",mtMsgsService.findAll());
        return "page/mtSend";
    }

    // 결과 조회 ------------------------------------------------------------------------------------
    @GetMapping("/send/at-record")
    public String atRecord(Model model){
        model.addAttribute("title","알림톡 발송 예약 내역");
        model.addAttribute("msgSbj","at");
        model.addAttribute("msgs", atMsgsService.findAll());
        return "page/table";
    }
    @GetMapping("/send/ft-record")
    public String ftRecord(Model model){
        model.addAttribute("title","친구톡 발송 예약 내역");
        model.addAttribute("msgSbj","ft");
        model.addAttribute("msgs",ftMsgsService.findAll());
        return "page/table";
    }
    @GetMapping("/send/mt-record")
    public String mtRecord(Model model){
        model.addAttribute("title","문자 메시지 발송 예약 내역");
        model.addAttribute("msgSbj","mt");
        model.addAttribute("msgs",mtMsgsService.findAll());
        return "page/table";
    }


    /*
    * 메시지 Create, Update url
    * */

    // Save
    @GetMapping("/send/msgs/save/{msg}")
    public String msgsSave(@PathVariable String msg, Model model) {
        model.addAttribute("msg",msg);
        return "page/msgs-save";
    }

    // Update
    @GetMapping("/send/msgs/update/at/{id}") // 수정할 화면 연결
    public String atMsgsUpdate(@PathVariable Integer id, Model model) {
        AtMsgsResponseDto dto = atMsgsService.findById(id);
        model.addAttribute("msgSbj","at");
        model.addAttribute("msg", dto);

        return "page/msgs-update";
    }
    @GetMapping("/send/msgs/update/ft/{id}")
    public String ftMsgsUpdate(@PathVariable Integer id, Model model) {
        FtMsgsResponseDto dto = ftMsgsService.findById(id);
        model.addAttribute("msgSbj","ft");
        model.addAttribute("msg", dto);

        return "page/msgs-update";
    }
    @GetMapping("/send/msgs/update/mt/{id}")
    public String mtMsgsUpdate(@PathVariable Integer id, Model model) {
        MtMsgsResponseDto dto = mtMsgsService.findById(id);
        model.addAttribute("msgSbj","mt");
        model.addAttribute("msg", dto);

        return "page/msgs-update";
    }
}
