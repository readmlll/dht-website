package top.readm.demo.dhtinfoparse.dht.parse.pojo;

import lombok.*;
import lombok.experimental.Accessors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.annotation.Id;
import org.springframework.util.StringUtils;
import top.readm.demo.dhtinfoparse.dht.parse.utils.OkHttpUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
@ToString
/**
 * 种子信息实体
 */
@org.springframework.data.elasticsearch.annotations.Document(indexName = "bt",type = "btinfo")
public class BtInfo implements Serializable {

    @Id
    private String id;
    private String fileTitle;  //基本信息 标题
    private int fileNum;    //文件数量
    private List<String> fileLIst; //文件列表
    private String infoHash;
    private String fileSize;    //文件大小
    private String time;        //时间


    public static boolean checkInfo(String infoHash,Integer reTryCount){
        String baseUrl="https://www.zhongziso.la/info-";
        //4a5f214ca17f84ce7610b810611718e7fb5e7447
        try {
            if(reTryCount==null){
                //重试次数
                reTryCount=5;
            }
            for(int i=0;i<reTryCount;i++){
                String page=null;
                try {
                    page= OkHttpUtils.get(baseUrl+infoHash, null);
                    if(StringUtils.isEmpty(page)){
                        System.out.println("解析链接站点失败 重试");
                        continue;
                    }

                    if(page.contains("Error: 404 Not Found")){
                        System.out.println(infoHash+" 检测不可用 放弃");
                        return false;
                    }
                }catch (Exception e){
                    System.out.println("解析链接站点失败 重试");
                    e.printStackTrace();
                    continue;
                }

                //page有内容 且不为 not found  说明info可用
                return true;
            }//retry for

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 利用别人的种子库 解析 hashinfo
     * @param infoHash
     * @param reTryCount  重试次数 为null 则默认为10
     * @return 成功解析返回BtInfo对象  否则返回null
     */
    public static BtInfo parseBtInfo(String infoHash,Integer reTryCount){

        String baseUrl="https://www.zhongziso.la/info-";
        //4a5f214ca17f84ce7610b810611718e7fb5e7447

        try {
            if(reTryCount==null){
                //重试次数
                reTryCount=60;
            }

            for(int i=0;i<reTryCount;i++){
                String page=null;
                try {
                    page= OkHttpUtils.get(baseUrl+infoHash, null);
                    if(StringUtils.isEmpty(page)){
                        System.out.println("解析链接站点失败 重试");
                        continue;
                    }

                    if(page.contains("Error: 404 Not Found")){
                        System.out.println(infoHash+" 没有信息,继续下一个");
                        return null;
                    }
                }catch (Exception e){
                    System.out.println("解析链接站点失败 重试");
                    e.printStackTrace();
                    continue;
                }

                Document doc = Jsoup.parse(page);

                Elements elements= doc.select(".panel-title .text-left");
                String name=elements.first().text();

                Element element= doc.select(".dl-horizontal.magnetmore").first();
                elements=element.children();
                String key=null,value=null;
                Map<String,Object> map = new HashMap<>();
                int count=0;
                for(Element el:elements){
                    if(++count>12){
                        break;
                    }
                    if(count%2==1){
                        key=el.text().replace(":", "");
                    }else{
                        value=el.text();
                        map.put(key, value);
                    }
                }
                map.put("标题", name);

                elements=doc.select("select.form-control option");
                List<String> fileNameList=new ArrayList<>();
                int fileListLen=0;
                for (Element el:elements){
                    fileListLen++;
                    fileNameList.add(el.text());
                    if(fileListLen==50)
                        break;
                }
                //System.out.println(map);

                BtInfo btInfo =new BtInfo();
                btInfo.setFileLIst(fileNameList)
                        .setFileSize((String) map.get("文件大小"))
                        .setInfoHash(infoHash)
                        .setFileNum(Integer.parseInt((String) map.get("文件数量")))
                        .setFileNum(Integer.parseInt((String) map.get("文件数量")))
                        .setFileTitle(name)
                        .setTime((String)map.get("创建日期"));

                return btInfo;
            }//retry for

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


        return null;
    }

}
