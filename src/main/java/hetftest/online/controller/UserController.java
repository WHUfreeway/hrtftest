package hetftest.online.controller;

import com.google.gson.Gson;
import hetftest.online.mapper.UserMapper;
import hetftest.online.pojo.User;
import hetftest.online.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    Gson gson = new Gson();

    @RequestMapping("/test")
    public String test(){
        User user = new User("freeway","123456","",0,"");
        userMapper.selectById(user.getUserName());
        //return gson.toJson(dbUser);
        return "";
    }

    @PostMapping("/login")
    public String Login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String captcha = request.getParameter("captcha");
        User dbUser = userMapper.selectById(username);
        if(dbUser==null){
            return "user not exist!";
        }
        else if(!dbUser.getPassWord().equals(password)){
            return "wrong pwd";
        }
        else {
            String token = JwtUtils.generateToken(username);
            dbUser.setToken(token);
            System.out.println(dbUser);
            userMapper.updateById(dbUser);
            return token;
        }
    }

    @PostMapping("/regist")
    public String register(@RequestBody User user) {
        User dbUser = userMapper.selectById(user.getUserName());
        if (dbUser != null) {
            return "regist failed, contains same user";
        } else {
            userMapper.insert(user);
            return "regist success";
        }
    }

    @PostMapping("/modify")
    public String modify(@RequestBody User user){
        User dbUser = userMapper.selectById(user.getUserName());
        if(dbUser == null){
            return "modify failed, no such user";
        }else {
            userMapper.updateById(user);
            return "change success";
        }
    }

    @PostMapping("/updateBesthrtfs")
    public String updateBesthrtfs(@RequestBody Map<String, String> requestData) {
        String userName = requestData.get("userName");
        String bestHrtfsString = requestData.get("bestHrtfsString");

        User dbUser = userMapper.selectById(userName);
        if (dbUser == null) {
            return "modify failed, no such user";
        } else {
            dbUser.setBestHrtfs(bestHrtfsString);
            userMapper.updateById(dbUser);
            return "modify success";
        }
    }

    @PostMapping("/updateBesthrtf")
    public String updateBesthrtf(@RequestBody Map<String, String> requestData) {
        String userName = requestData.get("userName");
        String bestHrtfString = requestData.get("bestHrtf");

        User dbUser = userMapper.selectById(userName);
        if (dbUser == null) {
            return "modify failed, no such user";
        } else {
            dbUser.setBestHrtf(Integer.parseInt(bestHrtfString));
            userMapper.updateById(dbUser);
            return "modify success";
        }
    }

    @PostMapping("/getbestHrtfs")
    public String getbestHrtfsByUsername(@RequestBody String username) {
        System.out.println(username);
        User dbUser = userMapper.selectById(username);
        if (dbUser == null) {
            return "User not found";
        } else {
            return dbUser.getBestHrtfs();
        }
    }

    @PostMapping("/getbestHrtf")
    public int getbestHrtfByUsername(@RequestBody String username) {
        User dbUser = userMapper.selectById(username);
        if (dbUser == null) {
            return -1;
        } else {
            return dbUser.getBestHrtf();
        }
    }

}
