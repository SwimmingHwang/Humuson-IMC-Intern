package com.example.main.controller;

import com.example.main.dto.AtMsgsResponseDto;
import com.example.main.dto.FtMsgsResponseDto;
import com.example.main.dto.MtMsgsResponseDto;
import com.example.main.service.*;
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

    private final TemplateInfoService templateInfoService;
    private final CustomerService customerService;

    // TODO : index로 가는일 없게 하기 혹은 다른 페이지 보여주기
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/send")
    public String send(Model model){// 서버 템플릿 엔진에서 사용할 수 있는 객체 저장
        return "page/send";
    }

//    @GetMapping("/login")
//    public String login() {// 서버 템플릿 엔진에서 사용할 수 있는 객체 저장
//        return "page/login";
//    }

    /*
    * 발송 페이지
    * */
    @GetMapping("/send/at-send")
    public String atSend(){
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
//    @GetMapping("/send/msgs/save/{msg}")
//    public String msgsSave(@PathVariable String msg, Model model) {
//        model.addAttribute("msg",msg);
//        //test 용
//        model.addAttribute("msgs", atMsgsService.findAll());
//        return "page/msgs-save";
//    }
    @GetMapping("/send/single-msg/save/{msg}")
    public String singleMsgSave(@PathVariable String msg, Model model) {
        model.addAttribute("msg",msg);
        //test 용
        model.addAttribute("msgs", atMsgsService.findAll());
        model.addAttribute("templateCodes",templateInfoService.findAll());
        return "page/singleMsgSend";
    }
    @GetMapping("/send/multi-msgs/save/{msg}")
    public String multiMsgsSave(@PathVariable String msg, Model model) {
        model.addAttribute("msg",msg);
        //test 용
        model.addAttribute("msgs", atMsgsService.findAll());
        model.addAttribute("templateCodes",templateInfoService.findAll());
        return "page/multiMsgsSend";
    }
    // 주소록
    @GetMapping("/send/multi-msgs/save/{msg}/list")
    public String multiMsgsSaveList(@PathVariable String msg, Model model) {
        model.addAttribute("msg",msg+"list");
        model.addAttribute("msgs", atMsgsService.findAll());
        model.addAttribute("templateCodes",templateInfoService.findAll());
        model.addAttribute("customers",customerService.findAll());
        return "page/multiMsgsSend";
    }

    @GetMapping("/send/msgs/save/{msg}")
    public String msgsSave(@PathVariable String msg, Model model) {
        model.addAttribute("msg",msg);
        //test 용
        model.addAttribute("msgs", atMsgsService.findAll());
        model.addAttribute("templateCodes",templateInfoService.findAll());
        return "page/singleMsgSend";
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


    /*
    * Profile
    * */
    @GetMapping("/profile/create")
    public String profileCreate(){
        return "profile/create";
    }


    /*
    *
    * 기업 회원의 고객 관리
    * */
    @GetMapping("/customer")
    public String proofieCreate(Model model) {
        model.addAttribute("title", "고객 리스트 조회");
        model.addAttribute("customers", customerService.findAll());
        return "customer/customerTable";
    }
    @GetMapping("/customer/create")
    public String customerSave() {
        return "customer/customerSave";
    }

}
