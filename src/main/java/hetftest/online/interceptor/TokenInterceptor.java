package hetftest.online.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hetftest.online.mapper.UserMapper;
import hetftest.online.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("starttoverify");
        System.out.println(request);
        // 在这里进行Token的验证，验证通过返回true，验证失败返回false
        String token = request.getHeader("Authorization");
        System.out.println(token);
        if (token != null && isValidToken(token)) {
            // Token验证通过
            return true;
        } else {
            // Token验证失败，返回401 Unauthorized状态码
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
    }

    private boolean isValidToken(String token) {
        // 在这里实现Token验证的逻辑
        // 验证成功返回true，验证失败返回false
        // 可以根据需要自定义Token的验证方式，比如解析Token、检查Token的有效期等
        try {
            System.out.println("try begin");
            // 解析 Token 获取其中的信息
            Claims claims = Jwts.parser()
                    .setSigningKey("whoru") // 设置用于签名的密钥
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println("get claim");

            // 根据需要进行进一步的验证操作
            // 比如检查 Token 的有效期等
            Date expirationDate = claims.getExpiration();
            System.out.println("get Expiration");
            Date now = new Date();
            if (expirationDate.before(now)) {
                // Token 已过期，验证失败
                System.out.println("token gst dated");
                return false;
            }
            String userName = claims.getSubject();
            // 其他自定义验证逻辑...
            User dbUser = userMapper.selectById(userName);
            if (dbUser.getToken().equals(token)) {
                System.out.println("token check success");
                return true;
            } else {
                return false;
            }
        } catch (JwtException e) {
            // Token 解析或验证出现异常，验证失败
            e.printStackTrace(); // 输出异常信息
            return false;
        }
    }

}
