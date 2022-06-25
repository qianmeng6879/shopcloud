package top.mxzero.file.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "mxzero.file")
public class FileConfig {
    private String path;
}
