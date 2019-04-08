package top.readm.demo.dhtinfoparse.dht.parse.thread.share;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Data
@Accessors(chain = true)
/**
 * 各个线程共享的数据 和 对应的锁
 */
public class ThreadShareUtils {

    //0未启动  1启动 2异常
    private static int status=0;

    public synchronized static int getStatus() {
        return status;
    }

    public synchronized static void setStatus(int status){
        ThreadShareUtils.status=status;
    }
}
