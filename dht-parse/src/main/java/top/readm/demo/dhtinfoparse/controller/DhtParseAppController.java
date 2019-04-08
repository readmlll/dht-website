package top.readm.demo.dhtinfoparse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.readm.demo.dhtinfoparse.dht.parse.DhtParseApp;
import top.readm.demo.dhtinfoparse.dht.parse.thread.share.ThreadShareUtils;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/dhtparse")
public class DhtParseAppController {

    @Autowired
    DhtParseApp dhtParseApp;

    @RequestMapping("/start")
    public Object start(){
        if(ThreadShareUtils.getStatus()!=1)
            dhtParseApp.run();
        return "dhtParseApp启动成功";
    }

    @RequestMapping("/stop")
    public Object stop(){
        if(ThreadShareUtils.getStatus()==1)
            dhtParseApp.stop();
        return "dhtParseApp停止成功";
    }

    @RequestMapping("/status")
    public Object getStatus(){
        return ThreadShareUtils.getStatus();
    }


}
