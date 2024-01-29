package com.jsk.interceptors;

import com.jsk.pojo.Result;
import com.jsk.utils.JwtUtil;
import com.jsk.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //獲取令牌
        String token = request.getHeader("Authorization");

        //令牌驗證
        try {
            //解析token
            Map<String, Object> claims = JwtUtil.parseToken(token);
            //建立局部線程獲取使用者的token
            ThreadLocalUtil.set(claims);
            //驗證通過放行
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            //驗證未通過不放行
            return false;
        }


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空局部現成的資源
        ThreadLocalUtil.remove();
    }
}

