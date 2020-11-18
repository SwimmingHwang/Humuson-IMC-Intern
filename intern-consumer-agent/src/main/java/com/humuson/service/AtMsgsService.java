package com.humuson.service;
import com.humuson.domain.msgs.AtMsgs;
import com.humuson.domain.msgs.AtMsgsRepository;
import com.humuson.dto.AtMsgsListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AtMsgsService {

    private final AtMsgsRepository atMsgsRepository;

    public void save(AtMsgs atMsgs) {
        atMsgsRepository.save(atMsgs);
    }

    public void saveAll(List<AtMsgs> atMsgsList) {
        atMsgsRepository.saveAll(atMsgsList);
    }
}
