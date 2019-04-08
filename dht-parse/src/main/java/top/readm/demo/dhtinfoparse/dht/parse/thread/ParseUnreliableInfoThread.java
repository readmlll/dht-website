package top.readm.demo.dhtinfoparse.dht.parse.thread;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.experimental.Accessors;
import top.readm.demo.dhtinfoparse.dht.parse.elasticsearch.ElasticsearchBtInfoUtils;
import top.readm.demo.dhtinfoparse.dht.parse.pojo.BtInfo;
import top.readm.demo.dhtinfoparse.dht.parse.redis.RedisHashInfoUtils;
import top.readm.demo.dhtinfoparse.dht.parse.thread.share.ThreadShareUtils;

@Data
@Accessors(chain = true)
public class ParseUnreliableInfoThread implements Runnable{

    private RedisHashInfoUtils redisHashInfoUtils=null;
    private ElasticsearchBtInfoUtils elasticsearchBtInfoUtils=null;

    @Override
    public void run() {
        while (ThreadShareUtils.getStatus()==1){

            try {
                Thread.sleep(200);
                //从redis中得到hashinfo 以便下面解析
                String hashInfo=redisHashInfoUtils.popUnReliableInfo();

                if(hashInfo==null)
                    Thread.sleep(60*1000);

                //根据已经存在的种子库中发送get请求 爬取信息
                boolean valid=BtInfo.checkInfo(hashInfo,null);
                if(!valid){
                    //检查不成功 放弃
                    System.out.println(hashInfo+" 不可靠来源info 检测 不可用");
                    continue;
                }
                //检测可用则加入 redis 可用infoset
                System.out.println(hashInfo+" 不可靠来源info 检测 可用 加入已经检测的info set集合中");
                redisHashInfoUtils.addCheckedInfo(hashInfo);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
