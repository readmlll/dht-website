package top.readm.demo.dhtsearchservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import top.readm.demo.dhtsearchservice.pojo.BtInfo;

import java.util.List;

public interface BtInfoRepository extends ElasticsearchRepository<BtInfo,String> {


}
