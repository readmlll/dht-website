package top.readm.demo.dhtinfoparse.dht.parse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.readm.demo.dhtinfoparse.dht.parse.elasticsearch.ElasticsearchBtInfoUtils;
import top.readm.demo.dhtinfoparse.dht.parse.redis.RedisHashInfoUtils;
import top.readm.demo.dhtinfoparse.dht.parse.thread.ParseCheckInfoThread;
import top.readm.demo.dhtinfoparse.dht.parse.thread.ParseReliableInfoThread;
import top.readm.demo.dhtinfoparse.dht.parse.thread.ParseUnreliableInfoThread;
import top.readm.demo.dhtinfoparse.dht.parse.thread.share.ThreadShareUtils;

@Component
public class DhtParseApp {

    @Autowired
    private RedisHashInfoUtils redisHashInfoUtils;
    @Autowired
    private ElasticsearchBtInfoUtils elasticsearchBtInfoUtils;

    public void run(){

        ThreadShareUtils.setStatus(1);
        System.out.println("DhtParseApp启动");

        new Thread(
                new ParseReliableInfoThread().setRedisHashInfoUtils(redisHashInfoUtils)
                .setElasticsearchBtInfoUtils(elasticsearchBtInfoUtils)
        ).start();


        new Thread(
                new ParseUnreliableInfoThread().setRedisHashInfoUtils(redisHashInfoUtils)
                        .setElasticsearchBtInfoUtils(elasticsearchBtInfoUtils)
        ).start();
        new Thread(
                new ParseUnreliableInfoThread().setRedisHashInfoUtils(redisHashInfoUtils)
                        .setElasticsearchBtInfoUtils(elasticsearchBtInfoUtils)
        ).start();

        new Thread(
                new ParseCheckInfoThread().setRedisHashInfoUtils(redisHashInfoUtils)
                        .setElasticsearchBtInfoUtils(elasticsearchBtInfoUtils)
        ).start();
    }

    public void stop(){
        ThreadShareUtils.setStatus(0);
        System.out.println("DhtParseApp停止");
    }
}
