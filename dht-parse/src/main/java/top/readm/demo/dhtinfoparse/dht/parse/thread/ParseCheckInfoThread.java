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
public class ParseCheckInfoThread implements  Runnable{

    private RedisHashInfoUtils redisHashInfoUtils=null;
    private ElasticsearchBtInfoUtils elasticsearchBtInfoUtils=null;

    @Override
    public void run() {
        while (ThreadShareUtils.getStatus()==1){

            try {
                Thread.sleep(300);
                //从redis 已经检测可用的set中得到hashinfo 以便下面解析
                String hashInfo=redisHashInfoUtils.popCheckedInfo();

                if(hashInfo==null)
                {
                    Thread.sleep(60*1000);
                    System.out.println("CheckedInfo暂时没有数据休息一会 ");
                }


                //根据已经存在的种子库中发送get请求 爬取信息
                BtInfo btInfo=BtInfo.parseBtInfo(hashInfo, null);
                if(btInfo==null){
                    System.out.println("CheckedInfo 解析失败 放弃 继续下一个 ");
                    continue;
                }

                //解析成功 那么 补充btinfo的id
                btInfo.setId(String.valueOf(redisHashInfoUtils.getBtInfoIdFromRedis()));
                String json=null;

                //保存btinfo的json字符串到redis
                ObjectMapper objectMapper=new ObjectMapper();
                json=objectMapper.writeValueAsString(btInfo);

                //保存实体的json数据到redis中 btInfo的list
                redisHashInfoUtils.addBtInfoFromJson(json);
                //保存btinfo实体到elasticsearch
                elasticsearchBtInfoUtils.addBtInfoToEs(btInfo);

                System.out.println("CheckedInfo 解析hashinfo成功 "+btInfo.toString());

            }catch (Exception e){
                e.printStackTrace();
            }

        }


    }
}
