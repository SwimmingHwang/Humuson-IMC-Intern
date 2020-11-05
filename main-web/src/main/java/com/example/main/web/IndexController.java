package com.example.main.web;

import com.example.main.service.MsgsService;
import com.example.main.service.PostsService;
import com.example.main.web.dto.MsgsResponseDto;
import com.example.main.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final MsgsService msgsService;


//    @GetMapping("/")
//    public String index(Model model) {// 서버 템플릿 엔진에서 사용할 수 있는 객체 저장
//        model.addAttribute("posts", postsService.findAllDesc());// findalldesc 결과를 posts라는 이름으로 index.must에 전달
//        return "index";
//    }
    @GetMapping("/")
    public String index(Model model) {// 서버 템플릿 엔진에서 사용할 수 있는 객체 저장
        model.addAttribute("msgs", msgsService.findAllDesc());// findalldesc 결과를 posts라는 이름으로 index.must에 전달
        return "send";
    }

//    public String index(Model model, @LoginUser SessionUser user) {
//        model.addAttribute("posts", postsService.findAllDesc());
//        if (user != null) {
//            model.addAttribute("userName", user.getName());
//        }
//        return "index";
//    }
//
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}") // 수정할 화면 연결
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

    @GetMapping("/msgs/save")
    public String msgsSave() {
        return "msgs-save";
    }

    @GetMapping("/msgs/update/{id}") // 수정할 화면 연결
    public String msgsUpdate(@PathVariable Integer id, Model model) {
        MsgsResponseDto dto = msgsService.findById(id);
        model.addAttribute("msg", dto);

        return "msgs-update";
    }
}
