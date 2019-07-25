package cn.dpc.springwebflux.config;

import cn.dpc.springwebflux.bean.HelloParam;
import cn.dpc.springwebflux.controller.GreetingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class Myconfig {

	@Bean
	public RouterFunction<ServerResponse> hello(GreetingHandler greetingHandler) {
		return route(GET("/hello/{message}")
				.and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
				req -> greetingHandler.hello(req.pathVariable("message")));
	}

	@Bean
	public RouterFunction<ServerResponse> helloWithBody(GreetingHandler greetingHandler) {
		return route(POST("/hello"),
				req -> req.body(toMono(HelloParam.class))
				.doOnNext(greetingHandler::helloWithMessage)
						.then(ok().build())
		);
	}
}
