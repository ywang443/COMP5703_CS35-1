package com.ezeat.Interceptor;


import com.ezeat.common.util.TokenUtils;
import com.ezeat.constant.statusCode.impl.AppCode;
import com.ezeat.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private Object handler;
    private ModelAndView modelAndView;

    /**
     * 在请求被处理之前调用
     * 该方法在控制器处理请求方法前执行，其返回值表示是否中断后续操作
     * 返回 true 表示继续向下执行，返回 false 表示中断后续操作
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求参数
        // String queryString = request.getQueryString();

        // 只拦截post请求
        String method = request.getMethod();
        if (!method.equals("POST")) {
            return true;
        }
        try {
            String token = request.getParameter("token").toString();
            // 验证token, 如果验证错误的话,会抛出错误(ApiException->而且这个错误会被 ControllerExceptionAdvice捕获处理，并返回合适格式的数据给客户端),
            // 验证成功会继续往下执行
            TokenUtils.verifyToken(token);
        } catch (NullPointerException e) {
            throw new ApiException(AppCode.TOKEN_ERROR, "缺少token");
        }
        return true;
    }

    /**
     * 在请求被处理后，视图渲染之前调用
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        this.request = request;
        this.response = response;
        this.handler = handler;
        this.modelAndView = modelAndView;
    }

    /**
     * 在整个请求结束后调用
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
