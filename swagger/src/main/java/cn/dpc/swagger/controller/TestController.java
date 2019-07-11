package cn.dpc.swagger.controller;

import cn.dpc.swagger.entity.PostParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@Api(tags = "测试控制器")
public class TestController {

	@ApiOperation("post 方法")
	@PostMapping("/")
	public String post(@RequestBody PostParam param){
		return param.toString();
	}

	@ApiOperation("get 方法")
	@GetMapping("/")
	public String get(@ApiParam("名称") @RequestParam String name){
		return name;
	}
}
