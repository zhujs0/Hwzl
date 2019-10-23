package com.springboot.hwzl.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.springboot.hwzl.customannotation.PassToken;
import com.springboot.hwzl.customannotation.UserLoginToken;
import com.springboot.hwzl.entity.User;
import com.springboot.hwzl.global.BusinessException;
import com.springboot.hwzl.global.globalentity.RetCode;
import com.springboot.hwzl.services.userservice.userservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    IUserService userService;





    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("Authorization");
        // 如果不是映射到方法直接通过,访问静态资源
        if(! (handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证,否则全部验证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
            else{
                //需要验证
                throw new BusinessException(RetCode.UNAUTHORIZED,"该方法弃用");
            }
        }
        else{
            // 执行认证
            if (token == null) {
                throw new BusinessException(RetCode.UNAUTHORIZED,"无token，请重新登录");
            }
            // 获取 token 中的 user id
            String userId;
            try {
                userId = JWT.decode(token).getAudience().get(0);
            } catch (JWTDecodeException j) {
                throw new BusinessException(RetCode.UNAUTHORIZED,j.toString());
            }
            User user = userService.findUserById( Integer.parseInt(userId));
            if (user == null) {
                throw new BusinessException(RetCode.UNAUTHORIZED,"用户不存在，请重新登录");
            }
            // 验证 token,用户密码为秘钥
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
            try {
                jwtVerifier.verify(token);
            } catch (JWTVerificationException e) {
                throw new BusinessException(RetCode.UNAUTHORIZED,e.toString());
            }
            return true;
        }
        //region  ===有UserLoginToken注解的验证
    /*    if (method.isAnnotationPresent(UserLoginToken.class)) {
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取 token 中的 user id
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j) {
                    throw new BusinessException(RetCode.UNAUTHORIZED,j.toString());
                }
                User user = userService.findUserById( Integer.parseInt(userId));
                if (user == null) {
                    throw new BusinessException(RetCode.UNAUTHORIZED,"用户不存在，请重新登录");
                }
                // 验证 token,用户密码为秘钥
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.Password)).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new BusinessException(RetCode.UNAUTHORIZED,e.toString());
                }
                return true;
            }
        }*/
        //endregion

        //return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }

}
