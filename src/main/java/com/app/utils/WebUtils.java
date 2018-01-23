package com.app.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

public class WebUtils {
	private static Logger logger = Logger.getLogger(WebUtils.class);

	public static boolean isAjax(HttpServletRequest request) {
		String requestType = request.getHeader("X-Requested-With");
		if ("XMLHttpRequest".equalsIgnoreCase(requestType)) {
			return true;
		}
		return false;
	}

	public static void sendJson(HttpServletResponse response, Map<String, Object> map) {
		JSONObject json = (JSONObject) JSONObject.toJSON(map);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append(json.toString());
			logger.debug("返回是\n");
			logger.debug(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
