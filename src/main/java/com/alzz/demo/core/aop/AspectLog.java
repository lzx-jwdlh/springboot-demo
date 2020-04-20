package com.alzz.demo.core.aop;

import com.alibaba.fastjson.JSONObject;
import com.alzz.demo.constant.Constants;
import com.alzz.demo.core.annotation.AnnotationLog;
import com.alzz.demo.core.mybatis.interceptor.SqlContext;
import com.alzz.demo.core.systemlog.SystemLogQueue;
import com.alzz.demo.domain.SystemLog;
import com.alzz.demo.repository.SystemLogMapper;
import com.alzz.demo.utils.LogUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @ClassName AspectLog
 * @Description TODO
 * @Author lzx
 * @Date 2020/1/14 16:14
 */
@Aspect
@Component
public class AspectLog {
    private static final Logger logger = LoggerFactory.getLogger(AspectLog.class);

    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Autowired
    SystemLogMapper systemLogMapper;
    @Autowired
    SystemLogQueue systemLogQueue;
    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.alzz.demo.core.annotation.AnnotationLog)")
    public void methodCachePointcut() {
    }

    @Around("methodCachePointcut()")
    public Object doAfterThrowing(ProceedingJoinPoint pjp) throws Throwable {

        Object result = null;
        SystemLog systemLog = null;
        JSONObject params = new JSONObject();

        //获取请求的类名
        String className = pjp.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        //当前请求的request
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //当前响应的response
//        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        //获取当前执行的方法
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        String logType = targetMethod.getAnnotation(AnnotationLog.class).remark();
//        Method realMethod = pjp.getTarget().getClass().getDeclaredMethod(signature.getName(), targetMethod.getParameterTypes());
//        System.out.println(realMethod.getAnnotation(AnnotationLog.class).remark()+"/"+realMethod.toString());
        List sqlList = null;
        try {
            //清空
            SqlContext.clearContextHolder();
            //查询
            result = pjp.proceed();
            //获取
            sqlList = SqlContext.getContextHolder();
            if(args.length>0){
                for (int i = 0; i < args.length; i++) {
                    if(args[i] instanceof JSONObject){
                        JSONObject obj = (JSONObject) args[i];
                        obj.forEach((k,v) ->{
                            params.put(k, v);
                        });
                    }
                }
            }
            systemLog = LogUtils.setSystemLog(sqlList,request,params,logType, Constants.SUCCESS,methodName,null,className);
        }catch (Exception e){
            e.printStackTrace();
            systemLog = LogUtils.setSystemLog(sqlList,request,params,logType, Constants.FAILURE,methodName,e.toString(),className);
        }finally {
            //返回信息


            System.out.println(systemLog);
//            systemLogMapper.insertSelective(systemLog);
            systemLogQueue.add(systemLog);
        }
        return result;
    }

}
