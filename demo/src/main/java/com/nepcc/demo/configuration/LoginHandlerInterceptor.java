package com.nepcc.demo.configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆检查，
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //return  true;   //调试用
        Object user = request.getSession().getAttribute("loginCount");
        if(user == null){
            if (request.getHeader("x-requested-with") != null
                    && request.getHeader("x-requested-with").equals("XMLHttpRequest")) { // 表明是一个ajax请求
                //未登录，返回给Ajax处理
                response.setHeader("SessionStatus", "timeout");
            }
            else {
                //未登陆，返回登陆页面
                request.setAttribute("msg","请先登陆！");
                request.getRequestDispatcher("/login.html").forward(request,response);
            }
            return false;
        }else{
            //已登陆，放行请求
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
