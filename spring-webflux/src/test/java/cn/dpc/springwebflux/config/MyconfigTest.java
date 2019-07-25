package cn.dpc.springwebflux.config;

import cn.dpc.springwebflux.bean.HelloParam;
import cn.dpc.springwebflux.controller.GreetingHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MyconfigTest {

	Myconfig myconfig = new Myconfig();

	GreetingHandler handler = new GreetingHandler();

	@Mock
	private GreetingHandler greetingHandler;

	@Test
	public void hello() {
		WebTestClient client = WebTestClient
				.bindToRouterFunction(myconfig.hello(handler))
				.build();
		String message = "world";
		client.get()
				.uri("/hello/"+message)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody(String.class)
				.isEqualTo("Hello, Spring!" + message);
	}

	@Test
	public void helloWithBody() {
		WebTestClient client = WebTestClient
				.bindToRouterFunction(myconfig.helloWithBody(greetingHandler))
				.build();
		String message = "world";
		HelloParam helloParam = new HelloParam();
		helloParam.setMessage(message);

		client.post()
				.uri("/hello")
				.body(Mono.just(helloParam), HelloParam.class)
				.exchange()
				.expectStatus()
				.isOk();

		verify(greetingHandler).helloWithMessage(helloParam);
	}
}