package com.yjxxt;

import com.alibaba.fastjson.JSON;
import com.yjxxt.base.ResultInfo;
import com.yjxxt.exceptions.NoLoginException;
import com.yjxxt.exceptions.ParamsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //未登录异常
        if(ex instanceof NoLoginException){
            ModelAndView mav = new ModelAndView("redirect:/index");
            return mav;
        }
        ModelAndView mav=new ModelAndView();
        //默认视图
        mav.addObject("code",400);
        mav.addObject("msg","系统异常，稍后访问......");
        //判断ResponseBody
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod=(HandlerMethod) handler;
            ResponseBody responseBody=handlerMethod.getMethod().getDeclaredAnnotation(ResponseBody.class);
            //判断
            if(responseBody==null){
                //返回视图
                if(ex instanceof ParamsException){
                    ParamsException pe=(ParamsException)ex;
                    mav.addObject("code",pe.getCode());
                    mav.addObject("msg",pe.getMsg());
                }
                //mav.setViewName("error");
                return mav;
            }else {
                //返回json数据
                ResultInfo resultInfo=new ResultInfo();
                resultInfo.setMsg("系统异常");
                resultInfo.setCode(400);
                ex.printStackTrace();
                if(ex instanceof  ParamsException){
                    ParamsException pe=(ParamsException)ex;
                    resultInfo.setCode(pe.getCode());
                    resultInfo.setMsg(pe.getMsg());
                }
                //获取resp
                response.setContentType("application/json;charset=utf-8");
                //获取输入流
                PrintWriter out=null;
                try {
                    out=response.getWriter();
                    out.write(JSON.toJSONString(resultInfo));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }finally {
                    if(out!=null){
                        out.flush();
                        out.close();
                    }
                }
            }
        }
        return mav;
    }
}
