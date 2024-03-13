package com.xeg.xms.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import com.google.code.kaptcha.Producer;
import com.xeg.xms.common.lang.Const;
import com.xeg.xms.common.lang.Result;
import com.xeg.xms.entity.User;
import com.xeg.xms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;

@RestController
public class AuthController extends BaseController{

    // 引入谷歌验证码
    @Autowired
    Producer producer;

    // 生成图片验证码
    @GetMapping("/captcha")
    public Result captcha() throws IOException {//抛出异常IO
        // 生成一个UUID作为key
        String key = UUID.randomUUID().toString();
        // 生成一个五位数的验证码
        String code = producer.createText();

        // 为了测试
        // key = "aaaaa";
        // code = "11111";

        //
        BufferedImage image = producer.createImage(code);//使用code生成图片
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();//输出
        ImageIO.write(image, "jpg", outputStream);//把图片写进流

        BASE64Encoder encoder = new BASE64Encoder();//把图片转成base64
        String str = "data:image/jpeg;base64,";//base64前缀

        String base64Img = str + encoder.encode(outputStream.toByteArray());

        redisUtil.hset(Const.CAPTCHA_KEY, key, code, 120);//存入radis

        // 返回图片
        return Result.succ(
                MapUtil.builder()
                        .put("token", key)
                        .put("captchaImg", base64Img)
                        .build()

        );
    }

    /**
     * 获取用户信息接口
     * @param principal
     * @return
     */
    // @GetMapping("/sys/userInfo")
    // public Result userInfo(Principal principal) {
    //
    //     SysUser sysUser = sysUserService.getByUsername(principal.getName());
    //
    //     return Result.succ(MapUtil.builder()
    //             .put("id", sysUser.getId())
    //             .put("username", sysUser.getUsername())
    //             .put("avatar", sysUser.getAvatar())
    //             .put("created", sysUser.getCreated())
    //             .map()
    //     );
    // }


}
