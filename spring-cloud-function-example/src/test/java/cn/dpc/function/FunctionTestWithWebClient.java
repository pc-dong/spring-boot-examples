package cn.dpc.function;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@RunWith(SpringRunner.class)
@FunctionalSpringBootTest
@AutoConfigureWebTestClient
public class FunctionTestWithWebClient {
	@Autowired
	private WebTestClient client;

	@Test
	public void words() {
		client.post().uri("/").body(Flux.just("foo"), String.class).exchange()
				.expectStatus().isOk().expectBody(String.class).isEqualTo("FOO");
	}
}
