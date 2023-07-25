//package com.test.config.tomcat;
//
//import com.xt.jwt.annotation.JwtCheck;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class MyInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 判断处理器对象是否是 HandlerMethod 类型
//        if (handler instanceof HandlerMethod) {
//            // 获取处理当前请求的 HandlerMethod
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//
//            JwtCheck methodAnnotation = handlerMethod.getMethodAnnotation(JwtCheck.class);
//
//            // 获取 Controller 类和方法的信息
//            Class<?> controllerClass = handlerMethod.getBeanType();
//            String controllerMethodName = handlerMethod.getMethod().getName();
//
//            // 在这里可以根据获取到的 Controller 类和方法信息执行相应的逻辑
//            System.out.println("Controller Class: " + controllerClass.getName());
//            System.out.println("Controller Method: " + controllerMethodName);
//        }
//
//        return true;
//    }
//}
//
