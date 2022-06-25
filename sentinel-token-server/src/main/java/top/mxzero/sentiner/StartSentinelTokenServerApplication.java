package top.mxzero.sentiner;


import com.alibaba.csp.sentinel.cluster.server.ClusterTokenServer;
import com.alibaba.csp.sentinel.cluster.server.SentinelDefaultTokenServer;
import com.alibaba.csp.sentinel.cluster.server.config.ClusterServerConfigManager;
import com.alibaba.csp.sentinel.cluster.server.config.ServerTransportConfig;

public class StartSentinelTokenServerApplication {

    static {    // 使用系统属性代替启动参数
        System.setProperty("csp.sentinel.dashboard.server", "sentinel-server:8888");            // 控制台地址
        System.setProperty("csp.sentinel.api.port", "8719");    // sentinel端口
        System.setProperty("project.name", "token-server");        // 服务名称
        System.setProperty("csp.sentinel.log.use.pid", "true");    // 设置pid（可选）
    }

    public static void main(String[] args) throws Exception {
        ClusterTokenServer tokenServer = new SentinelDefaultTokenServer(); // 实例化Token集群管理
        ClusterServerConfigManager.loadGlobalTransportConfig(new ServerTransportConfig().setIdleSeconds(600).setPort(10217));
        tokenServer.start();// 启动服务器
    }
}
