package org.lau.wechat.api;


import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.lau.wechat.config.WxMpConfiguration;
import org.lau.wechat.config.WxMpProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ZHAO.LIU
 * @csdn: https://blog.csdn.net/weixin_43191061
 * @Date 2023/2/15 17:15
 * @Description TODO
 * @Version 1.0
 **/
@RestController
@Slf4j
public class WechatTemplateService {

    @Autowired
    private WxMpProperties wxMpProperties;
    @Value("${wx.templateId}")
    private String templateId;

    /**
     * 调用该接口发送微信模板错误推送
     *
     * @param serviceErrorDto
     * @return
     */
    @RequestMapping("sendWechatTemplateError")
    public String sendWechatTemplateError(ServiceErrorDto serviceErrorDto) {
        WxMpTemplateMsgService wxMpTemplateMsgService = WxMpConfiguration.getMpServices().get("wx1ba5f67bc16408e3").getTemplateMsgService();
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId("9EnjFo_XvXg2JY8MnforWAJjSAAwBkwBYmI3eAXBBEk");
        wxMpTemplateMessage.setToUser("oFaqO6fpfYxoaty0ea8LO68-WF0M");
        List<WxMpTemplateData> data = new ArrayList<>();
        data.add(new WxMpTemplateData("SERVER_NAME", "liuzhao-测试微信公众号推送信息"));
        data.add(new WxMpTemplateData("IP", "192.168.110.1:8080"));
        data.add(new WxMpTemplateData("ERR_MSG", "空指针异常"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        String format = formatter.format(new Date());
        data.add(new WxMpTemplateData("ERR_TIME", format + " 20:30"));
        wxMpTemplateMessage.setData(data);
        wxMpTemplateMessage.setUrl("https://user.qzone.qq.com/1006599534?source=namecardhoverqzone");
        try {
            wxMpTemplateMsgService.sendTemplateMsg(wxMpTemplateMessage);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return "ok";
    }
}
