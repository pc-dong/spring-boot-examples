package cn.dpc.springwebflux.controller;

import cn.dpc.springwebflux.bean.HelloParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping
	public String get(){
		return "Hello World!";
	}

	@PostMapping
	public String post(@RequestBody HelloParam helloParam){
		return helloParam.getMessage();
	}
}
