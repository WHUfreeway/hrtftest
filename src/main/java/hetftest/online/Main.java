package hetftest.online;

import hetftest.online.utils.JwtUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("hetftest.online.mapper")
@MapperScan("")
public class Main {
    public static void main(String[] args) {
        String token = JwtUtils.generateToken("freeway");
        System.out.println(token);
        System.out.println("Hello world!");
        SpringApplication.run(Main.class, args);
    }
}