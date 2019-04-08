package top.readm.demo.dhtinfoparse.listen;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import top.readm.demo.dhtinfoparse.dht.parse.DhtParseApp;

import java.net.SocketException;

public class ApplicationReadyEventListen implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        try {
           event.getApplicationContext().getBean(DhtParseApp.class).run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
