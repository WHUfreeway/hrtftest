package hetftest.online.controller;

import hetftest.online.mapper.ShootPositionMapper;
import hetftest.online.pojo.ShootPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController: 将控制器的所有返回值转换成JSON格式返回值
 */

@RestController
@RequestMapping("/shootpision")
public class ShootPositionController {
    @Autowired
    ShootPositionMapper shootPositionMapper;
    @PostMapping("/add")
    public String addShootPosition(@RequestBody ShootPosition shootPosition){
        shootPositionMapper.insert(shootPosition);
        return "add success";
    }
}
