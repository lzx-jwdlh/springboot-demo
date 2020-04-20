package com.alzz.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.alzz.demo.domain.SystemLog;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LogUtils {
	
	public static SystemLog jsonObjectToAccLog(JSONObject params) {
		SystemLog accessLog = new SystemLog();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		accessLog.setLogTime(formatter.format(new Date()));
		
		accessLog.setOperCode(params.getString("operCode"));
		accessLog.setUserName(params.getString("userName"));
		return accessLog;
	}
	
	public static SystemLog setSystemLog(List<String> sqlList, HttpServletRequest request,
										 JSONObject params, String logType, String result, String methodName, String error,
										 String className) {
		SystemLog accessLog = LogUtils.jsonObjectToAccLog(params);

		accessLog.setOperCode("");
		//设置请求模块
		accessLog.setLogModel("");
		//设置操作类型
		accessLog.setLogType(logType);
		//ip
		accessLog.setClientIp(IPUtil.getIpAddress(request));
		//结果
		accessLog.setLogResult(result);
		//sql
		accessLog.setLogContext(sqlList!=null?sqlList.toString():"");
		//方法名
		accessLog.setMethodName(methodName);
		//参数
		accessLog.setParams(params.toString());
		//错误
		accessLog.setLogError(error);
		//类名
		accessLog.setClassName(className);
//		设置请求的Url
		accessLog.setLogUrl(request.getRequestURL().toString());
		return accessLog;
	}
//
//	public static SysAccessLog setAccessLog(String accessModel, List<String> sqlList, HttpServletRequest request,
//			JSONObject params, String operator, String result, String methodName, String error,
//			String className, SqlSessionFactory sqlSessionFactory, String historyData) {
//		SysAccessLog accessLog = LogUtils.jsonObjectToAccLog(params);
//
//		//设置请求模块
//		accessLog.setAccessModel(accessModel);
//		//设置客户端IP
//		accessLog.setClientIp(IPUtils.getIPAddress(request));
//		//accessLog.setClientIp(request.getRemoteAddr());
//		//设置请求类名
//		accessLog.setClassName(className);
//		//设置sql语句
//		accessLog.setAccessContext(sqlList.toString());
//		//设置请求参数
//		accessLog.setParams(params.toString());
//		//设置操作类型
//		accessLog.setAccessType(operator);
//		//设置请求结果
//		accessLog.setAccessResult(result);
//		//设置请求方法名
//		accessLog.setMethodName(methodName);
//		//设置请求的Url
//		accessLog.setAccessUrl(request.getRequestURL().toString());
//		//设置error
//		accessLog.setAccessError(error);

//		return accessLog;
//	}
}
