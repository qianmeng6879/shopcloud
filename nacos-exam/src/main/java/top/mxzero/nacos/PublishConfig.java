package top.mxzero.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.naming.NamingService;

import java.util.Properties;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class PublishConfig {
    private static final String NACOS_SERVER = "nacos-server:8848";
    private static final String DATA_ID = "top.mxzero.nacos.PublishConfig";
    private static final String NAMESPACE = "cf9582db-449f-405f-9e30-152b6f26e895";
    private static final String GROUP = "SHOPCLOUD_DEV";

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, NACOS_SERVER);
        properties.put(PropertyKeyConst.NAMESPACE, NAMESPACE);


        NamingService namingService = NacosFactory.createNamingService(properties);
        namingService.registerInstance("test.provider", "SHUPCLOUD_DEV","127.0.0.1", 8888, "CLOUD_CLUSTER");
        TimeUnit.SECONDS.sleep(Long.MAX_VALUE);


        // ===
//        ConfigService configService = NacosFactory.createConfigService(properties);
//        boolean b = configService.publishConfig(DATA_ID, GROUP, "top.mxzero");
//        System.out.println(b);
    }
}
