package com.cos.controllerdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //파일을 리턴할 것이기 때문!!
public class HttpRespController {
	
	@GetMapping("/txt")
	public String txt() {
		return "a.txt"; //프레임워크 사용(틀이 이미 정해져 있음) - 일반 정적 파일들은  resources/static 폴더 내부에 두면 디폴트경로
	}
	
	@GetMapping("/mus")
	public String mus() {
		return "b";
	}
	
	@GetMapping("/jsp")
	public String jsp() {
		return "c";
	}
}
