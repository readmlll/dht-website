package top.readm.demo.dhtsearchservice.service;

import org.springframework.data.domain.Page;
import top.readm.demo.dhtsearchservice.pojo.BtInfo;

import java.util.List;

/**
 * bt种子信息查找服务
 */
public interface BtInfoSearchService {
    long getTotalNum();
    Page<BtInfo> searchTile(String title, int page, int size);
}
