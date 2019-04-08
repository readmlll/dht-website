package top.readm.demo.dhtinfoparse.dht.parse.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import top.readm.demo.dhtinfoparse.dht.parse.pojo.BtInfo;

public interface BtInfoRepository extends ElasticsearchRepository<BtInfo,String> {

}
