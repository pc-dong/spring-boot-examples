package cn.dpc.function;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.function.context.FunctionalSpringApplication;

import java.util.function.Function;


@SpringBootConfiguration
public class SpringCloudFunctionExampleApplication implements Function<String, String> {

//	@Override
//	public Flux<String> uppercase(Flux<String> value){
//		return value.map(val -> val.toUpperCase());
//	}

	public static void main(String[] args) {

		FunctionalSpringApplication.run(SpringCloudFunctionExampleApplication.class, args);
	}

	@Override
	public String apply(String value) {
		return  value.toUpperCase();
	}
}
