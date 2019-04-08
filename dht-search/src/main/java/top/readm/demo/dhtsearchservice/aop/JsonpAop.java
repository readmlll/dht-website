package top.readm.demo.dhtsearchservice.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/*@Component
@Aspect
@Data
@ConfigurationProperties(prefix = "jsonp")*/
public class JsonpAop {

    private String name="callback";

    /**
     * 定义切入点，切入点
     */
    @Pointcut("execution( * top.readm.demo.dhtsearchservice.controller.BtInfoSearchController.*(..))")
    public void jsonp(){}

    /**
     * 环绕通知
     *
     */
    @Around(value = "jsonp()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){

        Object res="";
        String jsonp="";
        String json="";
        jsonp+=name+"(";
        try {
            res=proceedingJoinPoint.proceed();
            ObjectMapper objectMapper=new ObjectMapper();
            json=objectMapper.writeValueAsString(res);

            jsonp+=json;

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        jsonp+=")";

        return jsonp;
    }




}
