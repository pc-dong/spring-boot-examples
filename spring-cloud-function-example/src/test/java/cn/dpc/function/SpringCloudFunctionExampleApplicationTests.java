package cn.dpc.function;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import reactor.core.publisher.Flux;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringCloudFunctionExampleApplicationTests {

//	@Autowired
//	private MockMvc mockMvc;
//
//	@Test
//	public void uppercase() throws Exception {
//		MvcResult result = this.mockMvc.perform(post("/uppercase").contentType(MediaType.APPLICATION_JSON).content("[\"foo\",\"bar\"]")).andReturn();
//		mockMvc.perform(asyncDispatch(result)).andExpect(content().string("[\"FOO\",\"BAR\"]"));
//	}

	@Test
	public void contextLoads() {
//		webTestClient.post().uri("uppercase").accept(MediaType.TEXT_PLAIN)
//				.body(Flux.just("ddd","111"), String.class).exchange().expectBody(String.class)
//		.equals("DDD");
	}

}
