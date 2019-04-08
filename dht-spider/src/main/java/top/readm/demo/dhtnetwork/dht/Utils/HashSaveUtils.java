package top.readm.demo.dhtnetwork.dht.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.readm.demo.dhtnetwork.dht.redis.RedisHashInfoUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 持久化可靠谱的 不可靠的 hashinfo
 *
 */
@Component
public class HashSaveUtils {

    @Autowired
    private RedisHashInfoUtils redisHashInfoUtils;

   /* private File uFile=new File("C:\\Users\\Readm\\Desktop\\un.txt");
    private File reFile=new File("C:\\Users\\Readm\\Desktop\\re.txt");
    private BufferedWriter unFileWriter=null;
    private BufferedWriter reFileWriter=null;


    {
        try {
            unFileWriter = new BufferedWriter(new FileWriter(uFile));
            reFileWriter = new BufferedWriter(new FileWriter(reFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public  void unreliableInfoSave(String hashInfo){
        redisHashInfoUtils.addUnReliableInfo(hashInfo);
    }

    public  void reliableInfoSave(String hashInfo){
        redisHashInfoUtils.addReliableInfo(hashInfo);
    }


   /* public synchronized void unreliableInfoSave(String hashInfo){
        try {
            unFileWriter.append(hashInfo);
            unFileWriter.newLine();
            unFileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void reliableInfoSave(String hashInfo){
        try {
            reFileWriter.append(hashInfo);
            reFileWriter.newLine();
            reFileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/



}
