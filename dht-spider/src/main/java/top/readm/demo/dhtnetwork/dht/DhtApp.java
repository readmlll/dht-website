package top.readm.demo.dhtnetwork.dht;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import top.readm.demo.dhtnetwork.dht.Utils.HashSaveUtils;
import top.readm.demo.dhtnetwork.dht.redis.RedisHashInfoUtils;
import top.readm.demo.dhtnetwork.dht.thread.*;
import top.readm.demo.dhtnetwork.dht.pojo.DhtNode;
import top.readm.demo.dhtnetwork.dht.thread.share.ThreadShareUtils;

import java.io.*;
import java.net.*;
import java.util.*;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
@ToString

@Component
public class DhtApp {


    @Autowired
    private HashSaveUtils hashSaveUtils;

    @Autowired
    private DhtProperties dhtProperties;


    //自己节点的id  每次启动随机生成一个
    private byte[] selfId= DhtNode.bytesOrderReverse(DigestUtils.sha1(UUID.randomUUID().toString()));
    //启动节点 知名的几个超级节点
    private List<DhtNode> bootstrapNode=new ArrayList<>();

    //udp socket
    DatagramSocket udpSocket=null;


    public DhtApp() throws SocketException {
        //transmission的客户端dht.transmissionbt.com
        //bittorrent的客户端http://router.bittorrent.com
        //utorrent的客户端http://router.utorrent.com
        try {
            bootstrapNode.add(new DhtNode().setNodeIp(InetAddress.getByName("dht.transmissionbt.com").getHostAddress()).setNodePort("6881")
                    .setNodeId("2113483f69381b413f3f3f703f3f3f487372003c").nodeInfoEncode());
            bootstrapNode.add(new DhtNode().setNodeIp(InetAddress.getByName("router.bittorrent.com").getHostAddress()).setNodePort("6881")
                    .setNodeId("673f7c463f3f3f3f3f3f293f4a3f517369363f3f").nodeInfoEncode());
            bootstrapNode.add(new DhtNode().setNodeIp(InetAddress.getByName("router.utorrent.com").getHostAddress()).setNodePort("6881")
                    .setNodeId("673f7c463f3f3f3f3f3f293f4a3f5173694e3f32").nodeInfoEncode());
            bootstrapNode.add(new DhtNode().setNodeIp(InetAddress.getByName("70.50.65.64").getHostAddress()).setNodePort("12139")
                    .setNodeId("3f3f3f3f3f3f3f3f3f3f3f3f3f3f3f3f3f3f3f3f").nodeInfoEncode());

            //初始化 启动节点加入到 线程共享的 未探测的队列  和 已经存活的map队列
            bootstrapNode.forEach((node)->{
                ThreadShareUtils.unSenseQueue.add(node);
                ThreadShareUtils.survivalMap.put(node.getNodeId(), node);
            });

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        //run();

    }

    public void run() throws SocketException {

        //创建socket  发送ping 信息探测存活的节点放入 存活队列
        udpSocket=new DatagramSocket(dhtProperties.getUdpPort());
        ThreadShareUtils.setStatus(1);

        //1.ping侦测发送线程
        for(int i=0;i<dhtProperties.getPingThreadNum();i++){
            new Thread(new PingThread()
                    .setSelfId(selfId)
                    .setUdpSocket(udpSocket)
            ).start();
        }



        // 2.解析 接收 消息线程
        for(int i=0;i<dhtProperties.getMsgParseThreadNum();i++){
            new Thread(
                    new MsgParseThread().setSelfId(selfId)
                            .setUdpSocket(udpSocket).setHashSaveUtils(hashSaveUtils)
            ).start();
        }


        //3. 联系线程 发送find node
        for(int i=0;i<dhtProperties.getFindNodeThreadNum();i++){
            new Thread(
                    new FindNodeThread().setSelfId(selfId)
                            .setUdpSocket(udpSocket)
            ).start();
        }


        //4. getpeer 回复线程
        for(int i=0;i<dhtProperties.getGetPeersEchoThreadNum();i++){
            new Thread(
                    new GetPeerEchoThread().setSelfId(selfId)
                            .setUdpSocket(udpSocket)
            ).start();
        }


        //5. findNode 回复线程
        for(int i=0;i<dhtProperties.getFindNodeEchoThreadNum();i++){
            new Thread(
                    new FindNodeEchoThread().setSelfId(selfId)
                            .setUdpSocket(udpSocket)
            ).start();
        }

        //6. 维护路由表线程  也就是synPeerQueue的维护  我们只存160个存活的节点
        for(int i=0;i<dhtProperties.getRouteTableThreadNum();i++){
            new Thread(
                    new RouteTableThread()
            ).start();
        }
    }

    public void stop(){
        ThreadShareUtils.setStatus(0);
        if(udpSocket!=null)
            udpSocket.close();
    }

    public int getAppStatus(){
        return ThreadShareUtils.getStatus();
    }

}
