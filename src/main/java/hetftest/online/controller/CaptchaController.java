package hetftest.online.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @PostMapping("/get")
    public ResponseEntity<byte[]> generateCaptcha(HttpServletRequest request, HttpServletResponse response) {
        // 生成验证码
        String captchaText = defaultKaptcha.createText();
        String captchaId = UUID.randomUUID().toString();
        System.out.println(captchaText);

        // 将验证码存储在会话中或发送给前端进行验证
        HttpSession session = request.getSession();
        session.setAttribute("captcha", captchaText);
        session.setAttribute("captchaId", captchaId);

        // 将验证码渲染为图片并转换为字节数组
        BufferedImage captchaImage = defaultKaptcha.createImage(captchaText);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(captchaImage, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            // 处理异常
        }
        byte[] imageBytes = outputStream.toByteArray();

        // 将验证码标识ID设置为响应头部的"Captcha-Id"字段
        response.setHeader("Captcha-Id", captchaText);

        // 返回字节数组给前端
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }


    @PostMapping("/get2")
    public ResponseEntity<Map<String, Object>> generateCaptcha2(HttpServletRequest request, HttpServletResponse response) {
        // 生成验证码
        String captchaText = defaultKaptcha.createText();
        String captchaId = UUID.randomUUID().toString();
        System.out.println(captchaText);

        // 将验证码存储在会话中或发送给前端进行验证
        HttpSession session = request.getSession();
        session.setAttribute("captcha", captchaText);
        session.setAttribute("captchaId", captchaId);

        // 将验证码渲染为图片并转换为字节数组
        BufferedImage captchaImage = defaultKaptcha.createImage(captchaText);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(captchaImage, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            // 处理异常
        }
        byte[] imageBytes = outputStream.toByteArray();

        // 将验证码图片进行 Base64 编码
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        // 构建返回的 JSON 数据
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("captchaId", captchaText);
        responseData.put("captchaImage", base64Image);

        // 返回 JSON 数据给前端
        return ResponseEntity.ok(responseData);
    }


    @PostMapping("/verify")
    public String verifyCaptcha(@RequestBody String userInput, HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (captcha != null && captcha.equals(userInput)) {
            // 验证码匹配成功
            return "验证码正确";
        } else {
            // 验证码匹配失败
            return "验证码错误";
        }
    }

}
