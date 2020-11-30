package com.humuson.controller;

import com.humuson.dto.at.AtMsgsResponseDto;
import com.humuson.dto.customer.CustomerGroupResponseDto;
import com.humuson.dto.customer.CustomerResponseDto;
import com.humuson.dto.ft.FtMsgsResponseDto;
import com.humuson.dto.mt.MtMsgsResponseDto;
import com.humuson.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    private final CustomerGroupService customerGroupService;

    // TODO : index로 가는일 없게 하기 혹은 다른 페이지 보여주기
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "page/home";
    }

    @Operation(hidden = true)
    @GetMapping("/send")
    public String send(Model model){// 서버 템플릿 엔진에서 사용할 수 있는 객체 저장
        return "page/send/send";
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
        return "page/send/atsend";

    }
    @GetMapping("/send/ft-send")
    public String ftSend(Model model){
        model.addAttribute("title","친구톡 발송 예약 내역");
        model.addAttribute("msgSbj","ft");
        model.addAttribute("msgs",ftMsgsService.findAll());
        return "page/send/ftsend";
    }
    @GetMapping("/send/mt-send")
    public String mtSend(Model model){
        model.addAttribute("title","문자 메시지 발송 예약 내역");
        model.addAttribute("msgSbj","mt");
        model.addAttribute("msgs",mtMsgsService.findAll());
        return "page/send/mtsend";
    }

    // 결과 조회 ------------------------------------------------------------------------------------
    @GetMapping("/send/at-record")
    public String atRecord(Model model){
        model.addAttribute("title","알림톡 발송 예약 내역");
        model.addAttribute("msgSbj","at");
        model.addAttribute("msgs", atMsgsService.findAll());
        return "page/attable";
    }
    @GetMapping("/send/ft-record")
    public String ftRecord(Model model){
        model.addAttribute("title","친구톡 발송 예약 내역");
        model.addAttribute("msgSbj","ft");
        model.addAttribute("msgs",ftMsgsService.findAll());
        return "page/attable";
    }
    @GetMapping("/send/mt-record")
    public String mtRecord(Model model){
        model.addAttribute("title","문자 메시지 발송 예약 내역");
        model.addAttribute("msgSbj","mt");
        model.addAttribute("msgs",mtMsgsService.findAll());
        return "page/mttable";
    }


    /*
    * 메시지 Create, Update url
    * */

    // 알림톡 create
    @GetMapping("/send/single-msg/save/at")
    public String singleMsgSave( Model model) {
        model.addAttribute("msg","at");
        //test 용
        model.addAttribute("msgs", atMsgsService.findAll());
        model.addAttribute("templateCodes",templateInfoService.findAll());
        return "page/sendDetails/singleMsgSend";
    }
    @GetMapping("/send/multi-msgs/save/at")
    public String multiMsgsSave(Model model) {
        model.addAttribute("msg","at");
        //test 용
        model.addAttribute("msgs", atMsgsService.findAll());
        model.addAttribute("templateCodes",templateInfoService.findAll());
        return "page/sendDetails/multiMsgsSend";
    }

    @GetMapping("/send/multi-msgs/save/at/list")    // 주소록
    public String multiMsgsSaveList(Model model) {
        model.addAttribute("msg","at"+"list");
        model.addAttribute("msgs", atMsgsService.findAll());
        model.addAttribute("templateCodes",templateInfoService.findAll());
        model.addAttribute("customers",customerService.findAll());
        return "page/sendDetails/multiMsgsSend";
    }
    @Operation(hidden = true)
    @GetMapping("/send/msgs/save/{msg}")
    public String msgsSave(@PathVariable String msg, Model model) {
        model.addAttribute("msg",msg);
        //test 용
        model.addAttribute("msgs", atMsgsService.findAll());
        model.addAttribute("templateCodes",templateInfoService.findAll());
        return "page/sendDetails/singleMsgSend";
    }


    // 문자톡 create
    @Operation(hidden = true)
    @GetMapping("/send/single-msg/save/mt")
    public String singleMtMsgSave(Model model) {
        model.addAttribute("msg","mt");
        //test 용
        model.addAttribute("msgs", mtMsgsService.findAll());
        return "page/sendDetails/singleMtMsgSend";
    }
    @Operation(hidden = true)
    @GetMapping("/send/multi-msgs/save/mt")
    public String multiMtMsgsSave(Model model) {
        model.addAttribute("msg","mt");
        //test 용
//        model.addAttribute("msgs", mtMsgsService.findAll());
        return "page/sendDetails/multiMtMsgsSend";
    }
    @Operation(hidden = true)
    @GetMapping("/send/multi-msgs/save/mt/list")// 주소록
    public String multiMtMsgsSaveList(Model model) {
        model.addAttribute("msg","mt"+"list");
        model.addAttribute("msgs", mtMsgsService.findAll());
        model.addAttribute("customers",customerService.findAll());
        return "page/sendDetails/multiMtMsgsSend";
    }




    // UPDATE
    @GetMapping("/send/msgs/update/at/{id}")
    public String atMsgsUpdate(@PathVariable Integer id, Model model) {
        AtMsgsResponseDto dto = atMsgsService.findById(id);
        model.addAttribute("msgSbj","at");
        model.addAttribute("msg", dto);
        model.addAttribute("templateCodes",templateInfoService.findAll());
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
        model.addAttribute("msgs", mtMsgsService.findAll());

        return "page/mtmsgs-update";
    }

    /*
     *
     * 기업 회원의 고객 관리
     * */
    @GetMapping("/customer")
    public String profileCreate(Model model) {
        model.addAttribute("title", "고객 리스트 조회");
        model.addAttribute("customers", customerService.findAll());
        return "customer/customerTable";
    }
    @GetMapping("/customer/create")
    public String customerSave() {
        return "customer/customerSave";
    }

    @GetMapping("/customer/update/{id}") // 수정할 화면 연결
    public String customerUpdate(@PathVariable long id, Model model) {
        CustomerResponseDto dto = customerService.findById(id);
        model.addAttribute("customer", dto);
        return "customer/customerUpdate";
    }

    /*
    * Profile
    * */



    /*
    * 고객 그룹 관리
    * */
    @GetMapping("/customer/group")
    public String customerGroup(Model model) {
        model.addAttribute("groups", customerGroupService.findAll());
        return "customer/customerGroupTable";
    }
    @GetMapping("/customer/group/create")
    public String customerGroupSave() {
        return "customer/customerGroupSave";
    }
    @GetMapping("/customer/group/update/{id}") // 수정할 화면 연결
    public String customerGroupUpdate(@PathVariable long id, Model model) {
        CustomerGroupResponseDto dto = customerGroupService.findById(id);
        model.addAttribute("group", dto);
        return "customer/customerGroupUpdate";
    }

}
