package top.readm.demo.dhtnetwork.dht;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Accessors(chain = true)
@ToString
@Component
@ConfigurationProperties("dht")
public class DhtProperties {

    //绑定的udp端口
    private Integer udpPort=9100;
    //各个线程的数量设置  每个线程最低个数为1
    private Integer pingThreadNum=3;
    private Integer msgParseThreadNum=3;
    private Integer findNodeThreadNum=3;
    private Integer findNodeEchoThreadNum=3;
    private Integer getPeersEchoThreadNum=2;
    private Integer routeTableThreadNum=1;

}
