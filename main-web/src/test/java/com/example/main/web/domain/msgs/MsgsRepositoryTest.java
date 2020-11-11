package com.example.main.web.domain.msgs;

import com.example.main.domain.msgs.AtMsgs;
import com.example.main.domain.msgs.AtMsgsRepository;
import com.example.main.domain.msgs.Msgs;
import com.example.main.domain.msgs.MsgsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MsgsRepositoryTest {

    @Autowired
    MsgsRepository msgsRepository;
    @Autowired
    AtMsgsRepository atMsgsRepository;

    @After
    public void cleanup() {
        msgsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String msg = "발송 메세지";
        String phoneNumber = "전화번호";

        msgsRepository.save(Msgs.builder()
                .msg(msg)
                .build());

        //when
        List<Msgs> msgsList = msgsRepository.findAll();

        //then
        Msgs msgs = msgsList.get(0);
        assertThat(msgs.getMsg()).isEqualTo(msg);
        assertThat(msgs.getPhoneNumber()).isEqualTo(phoneNumber);
    }

    @Test
    public void insert_test(){
        String msg = "발송 메세지";
        String phoneNumber = "전화번호";

        msgsRepository.save(Msgs.builder()
                .msg(msg)
                .phoneNumber(phoneNumber)
                .build());

        //when
        List<Msgs> msgsList = msgsRepository.findAll();

        //then
        Msgs msgs = msgsList.get(0);
        assertThat(msgs.getMsg()).isEqualTo(msg);
        assertThat(msgs.getPhoneNumber()).isEqualTo(phoneNumber);

    }
    @Test
    public void insertAtTest(){
        String msg = "발송 메세지";
        String phoneNumber = "전화번호";
        String status = "9";
        String priority = "9";
        String reservedDate = "20201111";
        String senderKey = "@tjkgkjg#kjklf";
        String templateCode = "SPRING TEST CODE";

        atMsgsRepository.save(AtMsgs.builder()
                .msg(msg)
                .phoneNumber(phoneNumber)
                .status(status)
                .priority(priority)
                .reservedDate(reservedDate)
                .senderKey(senderKey)
                .templateCode(templateCode)
                .build());

        //when
        List<AtMsgs> msgsList = atMsgsRepository.findAll();

        //then
        AtMsgs msgs = msgsList.get(0);
        assertThat(msgs.getMsg()).isEqualTo(msg);
        assertThat(msgs.getPhoneNumber()).isEqualTo(phoneNumber);

    }



}