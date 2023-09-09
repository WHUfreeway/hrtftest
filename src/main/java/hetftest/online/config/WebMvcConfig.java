package hetftest.online.config;
import hetftest.online.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .excludePathPatterns("/user/login") // 排除/login请求
                .excludePathPatterns("/user/regist") // 排除/register请求
                .excludePathPatterns("/captcha/get") // 排除验证码路径下的请求
                .excludePathPatterns("/captcha/get2") // 排除验证码路径下的请求
                .excludePathPatterns("/captcha/verify") // 排除验证码路径下的请求
                .excludePathPatterns("/public/**") // 排除/public路径下的请求
                .addPathPatterns("/**"); // 所有请求都需要验证Token
    }

}