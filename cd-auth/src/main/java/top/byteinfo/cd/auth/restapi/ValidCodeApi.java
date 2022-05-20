package top.byteinfo.cd.auth.restapi;

import com.wf.captcha.GifCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.byteinfo.cd.auth.model.AuthVO;
import top.byteinfo.cd.auth.model.Result;
import top.byteinfo.common.redis.template.RedisRepository;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ValidCodeApi {
    @Autowired
    private RedisRepository redisClient;


    @GetMapping("api-uaa/validata/code/{deviceId}")
    public void createCode(@PathVariable String deviceId, HttpServletResponse response) throws Exception {
        Assert.notNull(deviceId, "机器码不能为空");
        // 设置请求头为输出图片类型
        CaptchaUtil.setHeader(response);

        GifCaptcha gifCaptcha = new GifCaptcha(100, 35, 4);
        // 设置类型：字母数字混合
        gifCaptcha.setCharType(Captcha.TYPE_DEFAULT);
        // 保存验证码
        this.saveImageCode(deviceId, gifCaptcha.text().toLowerCase());
        // 输出图片流
        gifCaptcha.out(response.getOutputStream());
    }
   private void saveImageCode(String deviceId, String code) {
            redisClient.set(deviceId,code);
    }

    @PostMapping("/api-uaa/oauth/token")
    @ResponseBody
    public Result<?> simpleAuth(){
        return Result.builder().datas(
                AuthVO.builder()
                        .access_token("e98b201f-a1e1-4fcb-b6d2-157fa28bbc96").token_type("bearer").refresh_token("b9e35452-9b74-4fbd-9dad-055141e00a98").expires_in(3599).scope("all").build()
        ).resp_code(0).resp_msg("").build();
    }



}
