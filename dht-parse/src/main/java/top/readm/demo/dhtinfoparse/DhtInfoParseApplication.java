package top.readm.demo.dhtinfoparse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.readm.demo.dhtinfoparse.listen.ApplicationReadyEventListen;

@SpringBootApplication
public class DhtInfoParseApplication {

    public static void main(String[] args) {
        SpringApplication springApplication=new SpringApplication(DhtInfoParseApplication.class);
        springApplication.addListeners(new ApplicationReadyEventListen());
        springApplication.run(args);
    }

}
