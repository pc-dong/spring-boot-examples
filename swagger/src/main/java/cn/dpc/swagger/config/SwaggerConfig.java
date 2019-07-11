package cn.dpc.swagger.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@Controller
@ApiIgnore
@Slf4j
public class SwaggerConfig {
	@Autowired
	SwaggerProperties swaggerProperties;

	@Bean
	public Docket createRestApi() {
		ParameterBuilder ticketPar = new ParameterBuilder();
		List<Parameter> pars = new ArrayList<>();
		ticketPar.name("token").description("user token").modelRef(new ModelRef("string")).parameterType("header")
				.required(false).build(); // header中的ticket参数非必填，传空也可以
		pars.add(ticketPar.build()); // 根据每个方法名也知道当前方法在设置什么参数

		return new Docket(DocumentationType.SWAGGER_2).enable(swaggerProperties.getEnabled()).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage())).paths(PathSelectors.any()).build()
				.globalOperationParameters(pars);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(swaggerProperties.getAppTitle()).description(swaggerProperties.getAppDesc()).
				termsOfServiceUrl(swaggerProperties.getServiceUrl()).version(swaggerProperties.getVersion())
				.build();
	}

	@RequestMapping("/api")
	public void api(HttpServletResponse response) {
		try {
			String rUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/swagger-ui.html").build()
					.toUriString();
			response.sendRedirect(rUrl);
		} catch (IOException e) {
			log.error("", e);
		}
	}
}
