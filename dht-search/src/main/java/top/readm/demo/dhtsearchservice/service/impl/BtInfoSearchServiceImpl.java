package top.readm.demo.dhtsearchservice.service.impl;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import top.readm.demo.dhtsearchservice.pojo.BtInfo;
import top.readm.demo.dhtsearchservice.repository.BtInfoRepository;
import top.readm.demo.dhtsearchservice.service.BtInfoSearchService;

import java.util.List;

@Service
public class BtInfoSearchServiceImpl implements BtInfoSearchService {

    @Autowired
    private BtInfoRepository btInfoRepository;

    public long getTotalNum(){

        return btInfoRepository.count();

    }


    public Page<BtInfo> searchTile(String title,int page,int size){
        if(page>0)
            page=page-1;
        //,Sort.by("fileSize")
        PageRequest pageRequest=PageRequest.of(page, size);

        QueryBuilder queryBuilder= QueryBuilders.boolQuery().should(QueryBuilders.fuzzyQuery("fileTitle", title))
                .should(QueryBuilders.fuzzyQuery("fileLIst", title))
                .should(QueryBuilders.fuzzyQuery("time", title))
                .should(QueryBuilders.fuzzyQuery("fileSize", title))
                .should(QueryBuilders.matchQuery("fileTitle",title))
                .should(QueryBuilders.wildcardQuery("fileTitle","*"+title+"*"))
                .should(QueryBuilders.wildcardQuery("fileLIst","*"+title+"*"));

        Page<BtInfo> pageObj=btInfoRepository.search(queryBuilder, pageRequest);

        return pageObj;
    }

}
