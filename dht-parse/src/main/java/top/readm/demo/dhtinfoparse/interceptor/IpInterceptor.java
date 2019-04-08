package top.readm.demo.dhtinfoparse.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IpInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       String[] ips =request.getRemoteHost().split("\\.");
       if(ips.length!=4)
            return false;

       if(!ips[0].equals("127")||!ips[1].equals("0")||!ips[2].equals("0")||!ips[3].equals("1")){
           return false;
       }
        System.out.println("是本地用户可以控制爬虫");
       return true;
    }

}
