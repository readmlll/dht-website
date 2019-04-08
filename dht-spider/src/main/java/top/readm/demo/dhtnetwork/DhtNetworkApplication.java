package top.readm.demo.dhtnetwork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.readm.demo.dhtnetwork.dht.DhtApp;
import top.readm.demo.dhtnetwork.dht.redis.RedisHashInfoUtils;
import top.readm.demo.dhtnetwork.listen.ApplicationReadyEventListen;

import java.io.IOException;

@SpringBootApplication
public class DhtNetworkApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication application = new SpringApplication(DhtNetworkApplication.class);
        application.addListeners(new ApplicationReadyEventListen());
        application.run();
    }

}
