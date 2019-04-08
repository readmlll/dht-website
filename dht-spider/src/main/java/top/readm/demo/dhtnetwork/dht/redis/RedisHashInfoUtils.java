package top.readm.demo.dhtnetwork.dht.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.readm.demo.dhtnetwork.dht.pojo.BtInfo;

@Data
@NoArgsConstructor
@Component
public class RedisHashInfoUtils {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    //不太可靠的 getpper获得的infohash
    private final String unreliableInfoSETKEY="unreliableInfoSet";
    //较为可靠的 anno获得的infohash
    private final String reliableInfoSETKEY="reliableInfoSet";
    //经过检测可用的infohash
    private final String checkedInfoSETKEY="checkedInfoSet";

    //保存种子信息的json字符串
    private final String btInfoLISTKEY="btInfoList";

    public void addReliableInfo(String infohash){
        BoundSetOperations<String,String> unreliableInfoSetOps=redisTemplate.boundSetOps(reliableInfoSETKEY);
        unreliableInfoSetOps.add(infohash);
    }

    public String popReliableInfo(){
        BoundSetOperations<String,String> unreliableInfoSetOps=redisTemplate.boundSetOps(reliableInfoSETKEY);
        return unreliableInfoSetOps.pop();
    }

    public void addUnReliableInfo(String infohash){
        BoundSetOperations<String,String> unreliableInfoSetOps=redisTemplate.boundSetOps(unreliableInfoSETKEY);
        unreliableInfoSetOps.add(infohash);
    }

    public String popUnReliableInfo(){
        BoundSetOperations<String,String> unreliableInfoSetOps=redisTemplate.boundSetOps(unreliableInfoSETKEY);
        return unreliableInfoSetOps.pop();
    }

    public void addCheckedInfo(String infohash){
        BoundSetOperations<String,String> unreliableInfoSetOps=redisTemplate.boundSetOps(unreliableInfoSETKEY);
        unreliableInfoSetOps.add(infohash);
    }

    public String popCheckedInfo(){
        BoundSetOperations<String,String> unreliableInfoSetOps=redisTemplate.boundSetOps(unreliableInfoSETKEY);
        return unreliableInfoSetOps.pop();
    }

    public void addBtInfoFromJson(String btInfoJson){
        BoundListOperations<String, String> btInfoListOps=redisTemplate.boundListOps(btInfoLISTKEY);
        btInfoListOps.rightPush(btInfoJson);
    }

    public boolean addBtInfoFromObj(BtInfo btInfoObj){

        try {
            if(btInfoObj==null)
                throw  new RuntimeException("BtInfo对象为null");
            BoundListOperations<String, String> btInfoListOps=redisTemplate.boundListOps(btInfoLISTKEY);
            ObjectMapper objectMapper=new ObjectMapper();
            String btInfoJson=objectMapper.writeValueAsString(btInfoObj);
            btInfoListOps.rightPush(btInfoJson);
            return true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }



}
