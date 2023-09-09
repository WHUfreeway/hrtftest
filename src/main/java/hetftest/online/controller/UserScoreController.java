package hetftest.online.controller;

import hetftest.online.mapper.UserScoreMapper;
import hetftest.online.pojo.UserScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/score")
@RestController
public class UserScoreController {
    @Autowired
    UserScoreMapper userScoreMapper;

    @PostMapping("/write")
    public String writeScore(@RequestBody UserScore userScore){
        userScoreMapper.insert(userScore);
        return "write success";
    }
}
