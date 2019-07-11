package cn.dpc.swagger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "swagger")
@Component
@Data
public class SwaggerProperties {
	private Boolean enabled = true;

	private String basePackage = "cn.dpc";

	private String appTitle = "swagger测试项目";

	private String appDesc = "swagger测试项目";

	private String serviceUrl = "http://localhost:8080";

	private String version = "1.0.0";
}
