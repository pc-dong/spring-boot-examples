package cn.dpc.function;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@FunctionalSpringBootTest
public class FunctionTests {

	@Autowired
	private FunctionCatalog catalog;

	@Test
	public void toUppercase() throws Exception {
		Function<Flux<String>, Flux<String>> function = catalog.lookup(Function.class,
				"function");
		assertThat(function.apply(Flux.just("foo")).blockFirst()).isEqualTo("FOO");
	}
}
