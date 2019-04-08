package top.readm.demo.dhtnetwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.readm.demo.dhtnetwork.dht.DhtApp;

import java.net.SocketException;

@Controller
public class DhtController {

    @Autowired
    DhtApp dhtApp;


    @ResponseBody
    @GetMapping("/dht/stop")
    public Object stop() throws SocketException {
        if(dhtApp.getAppStatus()==1)
            dhtApp.stop();
        return  "dht爬虫启动";
    }

    @ResponseBody
    @GetMapping("/dht/start")
    public Object start() throws SocketException {
        if(dhtApp.getAppStatus()!=1)
            dhtApp.run();
        return  "dht爬虫关闭";
    }

    @ResponseBody
    @GetMapping("/dht/status")
    public Object status() throws SocketException {
        return  dhtApp.getAppStatus();
    }

}
