package com.must.project.system.controller;

import com.google.code.kaptcha.Producer;
import com.must.common.constant.Constants;
import com.must.common.utils.uuid.IdUtils;
import com.must.framework.redis.RedisCache;
import com.must.framework.security.service.SysLoginService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@ActiveProfiles("dev")
public class BaseTest {
    @Autowired
    private WebApplicationContext context;
    MockMvc mockMvc;

    @Autowired
    private SysLoginService loginService;

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisCache redisCache;

    // 验证码类型
    @Value("${app.captchaType}")
    private String captchaType;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    public String getToken() {
        // 保存验证码信息
        String uuid = IdUtils.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        String code = null;

        // 生成验证码
        if ("math".equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            code = capText.substring(capText.lastIndexOf("@") + 1);
        } else if ("char".equals(captchaType)) {
            code = captchaProducer.createText();
        }

        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);

        String token = loginService.login("jugg", "admin123", code, uuid);

        return token;
    }

    public HttpHeaders getHeaders(){
        String token = getToken();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);
        httpHeaders.add("Cookie", "sidebarStatus=0; Admin-Token=" + token);
        return httpHeaders;
    }

}
