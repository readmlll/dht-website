package top.readm.demo.dhtinfoparse.dht.parse.elasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.readm.demo.dhtinfoparse.dht.parse.pojo.BtInfo;
import top.readm.demo.dhtinfoparse.dht.parse.repository.BtInfoRepository;

@Component
public class ElasticsearchBtInfoUtils {

    @Autowired
    private BtInfoRepository btInfoRepository;

    public void addBtInfoToEs(BtInfo btInfo){
        btInfoRepository.save(btInfo);
    }

}
