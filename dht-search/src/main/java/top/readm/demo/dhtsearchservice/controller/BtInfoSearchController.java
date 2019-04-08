package top.readm.demo.dhtsearchservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.readm.demo.dhtsearchservice.pojo.BtInfo;
import top.readm.demo.dhtsearchservice.service.BtInfoSearchService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/search")
public class BtInfoSearchController {

    @Autowired
    private BtInfoSearchService btInfoSearchService;

    @GetMapping("/total")
    public Object getTotalNum(@RequestParam(required = false,defaultValue = "callback") String callback){

        long totalNum=0;
        try {
            totalNum=btInfoSearchService.getTotalNum();
        }catch (Exception e){
            e.printStackTrace();
        }


        return callback+"("+totalNum+")";
    }

    @GetMapping("/title")
    public Object searchByTitle(@RequestParam String q,
                                      @RequestParam(required = false,defaultValue = "1") int page,
                                      @RequestParam(required = false,defaultValue = "20") int size,
                                @RequestParam(required = false,defaultValue = "callback") String callback){

        Page<BtInfo> pageObj=null;
        String json="";

        try {
            pageObj=btInfoSearchService.searchTile(q,page,size);

            if(pageObj==null)
                pageObj=new PageImpl<>(new ArrayList<>());

            ObjectMapper objectMapper=new ObjectMapper();
            json=objectMapper.writeValueAsString(pageObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  callback+"("+json+")";
    }

}
