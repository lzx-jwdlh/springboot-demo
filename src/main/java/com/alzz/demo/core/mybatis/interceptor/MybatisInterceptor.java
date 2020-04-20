package com.alzz.demo.core.mybatis.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Intercepts(value = {
		@Signature (type= Executor.class,
				method="update",
				args={MappedStatement.class,Object.class}),
		@Signature(type=Executor.class,
				method="query",
				args={MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class,
						CacheKey.class,BoundSql.class})
		/*,
		@Signature(type=Executor.class,
				method="query",
				args={MappedStatement.class,Object.class,RowBounds.class,ResultHandler.class})*/
}
				)
@Component
public class MybatisInterceptor implements Interceptor {

	private static final Logger log = LoggerFactory.getLogger(MybatisInterceptor.class);

	private Properties properties;
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		Object parameter = null;
		if (invocation.getArgs().length > 1) {
			parameter = invocation.getArgs()[1];
		}
		String sqlId = mappedStatement.getId();
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		Configuration configuration = mappedStatement.getConfiguration();
		Object returnValue = null;

		//获取当前的sql语句
		String sql = getSql(configuration, boundSql, sqlId);
		//将sql语句存到上下文中
		SqlContext.setContextHolder(sql);

		returnValue = invocation.proceed();
		return returnValue;
	}

	public static String getSql(Configuration configuration, BoundSql boundSql, String sqlId) {
		String sql = showSql(configuration, boundSql);
		return sql;
	}

	private static String getParameterValue(Object obj) {
		String value = null;
		if (obj instanceof String) {
			value = "'" + obj.toString() + "'";
		} else if (obj instanceof Date) {
			DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
			value = "'" + formatter.format(new Date()) + "'";
		} else if(obj instanceof Character) {
			value = "'" + obj.toString() + "'";
		} else {
			if (obj != null) {
				value = obj.toString();
			} else {
				value = "";
			}

		}
		return value;
	}
	private static String getParameterValue2(Object obj) {
		String value = null;
		if (obj instanceof String) {
			value = obj.toString();
		} else if (obj instanceof Date) {
			DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
			value = formatter.format(new Date());
		} else if(obj instanceof Character) {
			value =  obj.toString();
		} else {
			if (obj != null) {
				value = obj.toString();
			} else {
				value = "";
			}

		}
		return value;
	}

	public static String showSql(Configuration configuration, BoundSql boundSql) {
		Object parameterObject = boundSql.getParameterObject();
		List<Object> paramList = new ArrayList<>();
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		SqlContext.setSqlHolder(sql);
		if (parameterMappings.size() > 0 && parameterObject != null) {
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) { // 判断是数据库的基本类型，是直接替换
				sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
				paramList.add(getParameterValue2(parameterObject));
			} else {
				MetaObject metaObject = configuration.newMetaObject(parameterObject); // 得到mybatis的所有参数
				int count = 0;
				int fargenNum = 0; // 问号的数量
				for (ParameterMapping parameterMapping : parameterMappings) { // 得到sql语句中的字段名
					String propertyName = parameterMapping.getProperty();
					if (metaObject.hasGetter(propertyName)) { // 判断params里面是否有参数
						Object obj = metaObject.getValue(propertyName);
						String value = getParameterValue(obj);
						if(value.contains("?")) {
							fargenNum = fargenNum + StringUtils.countMatches(value, "?"); // 参数里面问号的数量
							count = count + StringUtils.countMatches(value, "?");
						}
						if(count > 0) {
							int num = 0;
							if(value.contains("?")) {
								num = getCharPosition(sql, "\\?", count);
							}else {
								num = getCharPosition(sql, "\\?", count+1);
							}
							StringBuffer buf = new StringBuffer(sql);
							sql = buf.replace(num, num+1, getParameterValue(obj)).toString();
							paramList.add(getParameterValue2(obj));
						}else {
							sql = sql.replaceFirst("\\?", getParameterValue(obj));
							paramList.add(getParameterValue2(obj));
						}
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						Object obj = boundSql.getAdditionalParameter(propertyName);
						sql = sql.replaceFirst("\\?", getParameterValue(obj));
						paramList.add(getParameterValue2(obj));
					} else { // params没有sql语句需要的参数情况下， 使用"''"插入
						if(fargenNum > 0) {
							int num = getCharPosition(sql, "\\?", fargenNum+1);
							StringBuffer buf = new StringBuffer(sql);
							sql = buf.replace(num, num+1, "''").toString();
							paramList.add("");
						}else {
							sql = sql.replaceFirst("\\?", "''");
							paramList.add("");
						}
					}
				}
			}
		}
		SqlContext.setParamHolder(paramList);
		return sql;
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties0) {
		this.properties = properties0;
	}

	public static int getCharPosition(String str, String c, int num){
		Matcher matcher = Pattern.compile(c).matcher(str);
		int index = 0;
		while(matcher.find()) {
			index ++;
			if(index == num) {
				break;
			}
		}
		return matcher.start();
	}
}