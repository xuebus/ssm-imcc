package com.zhaopin.api;

import com.zhaopin.core.common.ResultModel;
import com.zhaopin.core.utils.HttpClientUtil;
import com.zhaopin.service.MessageService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * 云信消息抄送接口
 * 接收云信聊天消息发送给平台
 * Created by SYJ on 2017/4/13.
 */
@Component
@Path("/ihr/im/message")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Value("${url}")
    private String url;// 平台接口url

    @Autowired
    private MessageService messageService;

    /**
     * 发送消息
     * @param request
     * @return
     */
    @POST
    @Path("/send")
    public ResultModel send (@Context HttpServletRequest request) {
        try {
            byte[] body = readBody(request);
            if (body == null) {
                return ResultModel.build(null, "request wrong, empty body!");
            }
            String requestBody = new String(body, "utf-8");
            LOGGER.info("request body = {}", requestBody);
            // 调用平台接口,发送消息
            HttpClientUtil.doPostJson(url, requestBody);
        } catch (Exception e) {
            LOGGER.info("Message sending failed = {}", e.toString());
//            LOGGER.info(ExceptionUtil.getStackTrace(e));
        }
        return ResultModel.ok();
    }

    /**
     * 从request解析消息体
     * @param request
     * @return
     * @throws IOException
     */
    private byte[] readBody(HttpServletRequest request) throws IOException {
        if (request.getContentLength() > 0) {
            byte[] body = new byte[request.getContentLength()];
            IOUtils.readFully(request.getInputStream(), body);
            return body;
        } else
            return null;
    }
}
