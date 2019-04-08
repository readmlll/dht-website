package top.readm.demo.dhtnetwork.listen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import top.readm.demo.dhtnetwork.dht.DhtApp;

import java.net.SocketException;

public class ApplicationReadyEventListen implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        try {
            event.getApplicationContext().getBean(DhtApp.class).run();
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }
}
