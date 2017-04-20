package com.zhaopin.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhaopin.core.common.CheckSumBuilder;
import com.zhaopin.core.common.ResultModel;
import com.zhaopin.core.utils.CommonUtil;
import com.zhaopin.model.Imcc;
import com.zhaopin.model.Person;
import com.zhaopin.service.ImccService;
import com.zhaopin.service.PersonService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * 测试类
 * Created by SYJ on 2017/4/13.
 */
@Component
@Path("/test")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class PersonController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
	//秘钥
	private final String appSecret = "7bb79g40f44j";

	@Autowired
	PersonService personService;

	@Autowired
	private ImccService imccService;

	@GET
	@Path("/test")
	public ResultModel test() {
		LOGGER.info("===test===");
		return ResultModel.ok();
	}

	@GET
	@Path("/person/select")
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultModel selectPerson(@Context HttpServletRequest request) {
		String name = request.getParameter("personName");
		String personName = CommonUtil.encodeISO88591ToUtf8(name);
		LOGGER.info("personName:" + personName);
		//封装参数
		Map<String, Object> map = new HashMap<>();
		map.put("personName", personName);
		//根据条件组合查询
		List<Person> pList = personService.selectPersonByCondition(map);

		return ResultModel.ok(pList);
	}

	@GET
	@Path("/imcc/select")
	@Consumes(MediaType.APPLICATION_JSON)
	public ResultModel selectImccById() {
		System.out.println("====XbsController#select====");
		Imcc imcc = imccService.selectImccById(1);
		return ResultModel.ok(imcc);
	}

	@POST
	@Path("/imcc/insert")
	public ResultModel insert(@Context HttpServletRequest request) {
		byte[] body = readBody(request);
		if (body == null) {
			return ResultModel.build(null, "request wrong, empty body!");
		}
		// 将请求体转成String格式，并打印
		String requestBody = null;
		try {
			requestBody = new String(body, "utf-8");
		} catch (UnsupportedEncodingException e) {
			return ResultModel.build(null, "Unsupported encoding exception!");
		}
		LOGGER.info("request body = {}", requestBody);
		Imcc imcc = JSON.parseObject(requestBody, Imcc.class);
		imccService.insert(imcc);
		return ResultModel.ok();
	}

	@POST
	@Path("/route/mockClient")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject mockClient(@Context HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject();
		if (request == null) {
			LOGGER.warn("request is empty!");
			result.put("code", 414);
			return result;
		}

		try {
			// 获取请求体
			byte[] body = readBody(request);
			if (body == null) {
				LOGGER.warn("request wrong, empty body!");
				result.put("code", 414);
				return result;
			}

			// 获取部分request header，并打印
			String ContentType = request.getContentType();
			String AppKey = request.getHeader("AppKey");
			String CurTime = request.getHeader("CurTime");
			String MD5 = request.getHeader("MD5");
			String CheckSum = request.getHeader("CheckSum");
			LOGGER.info("request headers: ContentType = {}, AppKey = {}, CurTime = {}, " +
					"MD5 = {}, CheckSum = {}", ContentType, AppKey, CurTime, MD5, CheckSum);

			// 将请求体转成String格式，并打印
			String requestBody = new String(body, "utf-8");
			LOGGER.info("request body = {}", requestBody);

			// 获取计算过的md5及checkSum
			String verifyMD5 = CheckSumBuilder.getMD5(requestBody);
			String verifyChecksum = CheckSumBuilder.getCheckSum(appSecret, verifyMD5, CurTime);
			LOGGER.info("verifyMD5 = {}, verifyChecksum = {}", verifyMD5, verifyChecksum);

			// TODO: 比较md5、checkSum是否一致，以及后续业务处理

			Imcc imcc = JSON.parseObject(requestBody, Imcc.class);
			imccService.insert(imcc);
			LOGGER.info("insert message Imcc = {}", imcc);
			result.put("code", 200);
			return result;
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			result.put("code", 414);
			return result;
		}
	}

	private byte[] readBody(HttpServletRequest request) {
		if (request.getContentLength() > 0) {
			byte[] body = new byte[request.getContentLength()];
			try {
				IOUtils.readFully(request.getInputStream(), body);
			} catch (IOException e) {
				return null;
			}
			return body;
		} else
			return null;
	}
}
