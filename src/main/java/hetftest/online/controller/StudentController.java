package hetftest.online.controller;

import com.google.gson.Gson;
import hetftest.online.mapper.StudentMapper;
import hetftest.online.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentMapper studentMapper;

    Gson gson = new Gson();
    @RequestMapping("/test")
    public String test(){
        List<Student> students = studentMapper.selectList(null);
        return gson.toJson(students);
    }

    @GetMapping("/delete")
    public void deleteStudent(){
        String student = "{\"id\":13,\"number\":\"113\",\"name\":\"小黄\",\"age\":14,\"chi\":78,\"math\":99,\"eng\":93}";
        Student studentVar = gson.fromJson(student,Student.class);
        studentMapper.deleteById(studentVar);
    }

    @GetMapping("/update")
    public void updateStudent(){
        String student = "{\"id\":12,\"number\":\"113\",\"name\":\"小黄\",\"age\":14,\"chi\":78,\"math\":99,\"eng\":93}";
        Student studentVar = gson.fromJson(student,Student.class);
        studentMapper.updateById(studentVar);
    }
}
