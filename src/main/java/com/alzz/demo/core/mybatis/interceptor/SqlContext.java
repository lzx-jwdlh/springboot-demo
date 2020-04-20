package com.alzz.demo.core.mybatis.interceptor;

import java.util.ArrayList;
import java.util.List;

public class SqlContext {

	private static ThreadLocal<List<String>> contextHolder = new ThreadLocal<>();
	private static ThreadLocal<List<String>> sqlHolder = new ThreadLocal<>();
	private static ThreadLocal<List<List<Object>>> paramHolder = new ThreadLocal<>();

	public static List<String> getContextHolder() {
		return contextHolder.get();
	}

	public static List<String> getSqlHolder() {
		return sqlHolder.get();
	}

	public static List<List<Object>> getParamHolder() {
		return paramHolder.get();
	}

	public static void setContextHolder(String sql) {
		List<String> list = null;
		if(contextHolder.get() != null) {
			list = contextHolder.get();
		}else {
			list = new ArrayList<String>();
		}
		list.add(sql);
		contextHolder.set(list);
	}

	public static void setSqlHolder(String sql) {
		List<String> list = null;
		if(sqlHolder.get() != null) {
			list = sqlHolder.get();
		}else {
			list = new ArrayList<String>();
		}
		list.add(sql);
		sqlHolder.set(list);
	}

	public static void setParamHolder(List<Object> param) {
		List<List<Object>> list = null;
		if(paramHolder.get() != null) {
			list = paramHolder.get();
		}else {
			list = new ArrayList<List<Object>>();
		}
		list.add(param);
		paramHolder.set(list);
	}

	public static void clearContextHolder() {
        contextHolder.remove();
        sqlHolder.remove();
        paramHolder.remove();
    }

}