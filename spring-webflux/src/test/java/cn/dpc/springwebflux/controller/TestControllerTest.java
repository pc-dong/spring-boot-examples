package cn.dpc.springwebflux.controller;

import cn.dpc.springwebflux.bean.HelloParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.reactive.WebTestClientAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebFluxTest(TestController.class)
public class TestControllerTest {

	@Autowired
	WebTestClient webClient;

	@Test
	public void get() {
		webClient.get().uri("/test")
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody(String.class)
				.equals("Hello world!");
	}

	@Test
	public void post() {
		String message = "world";
		HelloParam helloParam = new HelloParam();
		helloParam.setMessage(message);
		webClient.post().uri("/test")
				.body(Mono.just(helloParam),HelloParam.class)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody(String.class)
				.equals(message);
	}
}