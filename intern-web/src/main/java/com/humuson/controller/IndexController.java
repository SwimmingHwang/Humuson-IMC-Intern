package com.humuson.controller;

import com.humuson.domain.entity.AtCampaign;
import com.humuson.domain.entity.Customer;
import com.humuson.domain.entity.Group;
import com.humuson.domain.entity.Profile;
import com.humuson.dto.at.AtCampaignSaveRequestDto;
import com.humuson.dto.at.AtMsgsResponseDto;
import com.humuson.dto.ft.FtMsgsResponseDto;
import com.humuson.dto.mt.MtMsgsResponseDto;
import com.humuson.service.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final MsgsService msgsService;
    private final AtMsgsService atMsgsService;
    private final FtMsgsService ftMsgsService;
    private final MtMsgsService mtMsgsService;
    private final UserService userService;

    private final TemplateInfoService templateInfoService;
    private final CustomerService customerService;
    private final GroupService groupService;
    private final ProfileService profileService;
    private final AtCampaignService atCampaignService;

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
    * 발송 기본 페이지
    * */
    @GetMapping("/send/at-send")
    public String atSend(){
        return "page/send/atsend";

    }
    // ymbin
    @GetMapping("/send/bulk-mt-msgs-send")
    public String mtMsgsSend(Model model, Authentication authentication){
        String sendNumber = userService.findPhoneNumber(authentication.getName());
        model.addAttribute("sendNumber", sendNumber);
        return "page/sendDetails/bulkMtMsgsSend";
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
        model.addAttribute("title","문자 메시지 발송 내역");
        model.addAttribute("msgSbj","mt");
        model.addAttribute("msgs",mtMsgsService.findAll());
        return "page/send/mtsend";
    }

    /*
    * 발송 세부사항 입력 페이지
    * */
    @GetMapping("/send/at-msgs-send")
//    public String atMsgsSend(Model model, Authentication authentication){
        public String atMsgsSend(Model model ,Authentication authentication){
        // TODO : profile에서 모든 senderkey-senderName으로!!!!!! 가져오기 : select 로 구현할 것

//        authentication.getName();
        long userIdx = userService.findUserIdx(authentication.getName());
//        long userIdx = userService.findUserIdx("t1@test.com");
        Profile profile = profileService.findByUserId(userIdx);
        String senderName = profile.getSenderName();
        String senderKey = profile.getSenderKey();

        AtCampaignSaveRequestDto atCampaignSaveRequestDto = new AtCampaignSaveRequestDto();
        atCampaignSaveRequestDto.setSenderName(senderName);
        atCampaignSaveRequestDto.setSenderKey(senderKey);

        model.addAttribute("atCampaign",atCampaignSaveRequestDto);
        model.addAttribute("templateCodes",templateInfoService.findAll());

        return "page/sendDetails/atMsgsSend";
    }
//    // ymbin
//    @GetMapping("/send/mt-msgs-send")
////    public String mtMsgsSend(Model model, Authentication authentication){
//    public String mtMsgsSend(Model model){
//        String sendNumber = userService.findPhoneNumber("t1@test.com");
//        model.addAttribute("sendNumber", sendNumber);
//        return "page/sendDetails/mtMsgsSend";
//    }



    // 결과 조회 ------------------------------------------------------------------------------------
    @GetMapping("/send/at-record")
    public String atRecord(Model model){
        model.addAttribute("title","알림톡 전체 조회");
        model.addAttribute("msgSbj","at");
        model.addAttribute("msgs", atMsgsService.findAllReservedDateDesc());
        return "page/attable";
    }
    @GetMapping("/send/ft-record")
    public String ftRecord(Model model){
        model.addAttribute("title","친구톡 발송 내역");
        model.addAttribute("msgSbj","ft");
        model.addAttribute("msgs",ftMsgsService.findAll());
        return "page/attable";
    }
    @GetMapping("/send/mt-record")
    public String mtRecord(Model model){
        model.addAttribute("title","문자 전체 조회");
        model.addAttribute("msgSbj","mt");
        model.addAttribute("msgs",mtMsgsService.findAllReservedDateDesc());
        return "page/mttable";
    }

    // 결과 조회 ------------------------------------------------------------------------------------
    @GetMapping("/send/at-camp-record")
    public String atCampRecord(Model model){
        model.addAttribute("title","알림톡 발송 내역");
        model.addAttribute("msgSbj","at");
        model.addAttribute("msgs", atCampaignService.findAllReservedDateDesc());
        return "page/atcamptable";
    }
    @GetMapping("/send/mt-camp-record")
    public String mtCampRecord(Model model){
        model.addAttribute("title","문자 메시지 발송 내역");
//        model.addAttribute("msgSbj","mt");
//        model.addAttribute("msgs",mtMsgsService.findAll());
        return "page/mtcamptable";
    }



    /*
    * 메시지 Create, Update url
    * */

    // 알림톡 create
    @GetMapping("/send/single-msg/save/at")
    public String singleMsgSave(Model model, Authentication authentication) {
        model.addAttribute("msg","at");
        //test 용
        model.addAttribute("msgs", atMsgsService.findAll());

//        long userIdx = userService.findUserIdx("t1@test.com");
        long userIdx = userService.findUserIdx(authentication.getName());
        Profile profile = profileService.findByUserId(userIdx);
        String senderName = profile.getSenderName();
        String senderKey = profile.getSenderKey();

        AtCampaignSaveRequestDto atCampaignSaveRequestDto = new AtCampaignSaveRequestDto();
        atCampaignSaveRequestDto.setSenderName(senderName);
        atCampaignSaveRequestDto.setSenderKey(senderKey);

        model.addAttribute("atCampaign",atCampaignSaveRequestDto);
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
     * 고객 관리
     * */
    @GetMapping("/customer")
    public String customerCreate(Model model) {
        model.addAttribute("title", "고객 리스트 조회");
        model.addAttribute("customers", customerService.findAll());
        return "customer/customerTable";
    }
    @GetMapping("/customer/create")
    public String customerSave(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customer/customerSave";
    }

    @GetMapping("/customer/update/{id}") // 수정할 화면 연결
    public String customerUpdate(@PathVariable long id, Model model) {
        Customer dto = customerService.findById(id);
        model.addAttribute("customer", dto);
        return "customer/customerUpdate";
    }

    /*
    * 고객 그룹 관리
    * */
    @GetMapping("/customer/group")
    public String group(Model model) {
        model.addAttribute("groups", groupService.findAll());
        return "customer/groupTable";
    }
    @GetMapping("/customer/group/create")
    public String groupSave(Model model) {
        Group group = new Group();
        model.addAttribute("group", group);
        model.addAttribute("customerList", customerService.findAll());
        return "customer/groupSave";
    }
    @GetMapping("/customer/group/update/{id}") // 수정할 화면 연결
    public String groupUpdate(@PathVariable long id, Model model) {
        Group group = groupService.findById(id);
        List<Customer> customers = customerService.findAll();
        model.addAttribute("group", group);
        model.addAttribute("customerList", customers);
        model.addAttribute("selectedCustomers",groupService.findAllCustomersIds(id) );
        return "customer/groupUpdate";
    }
}
