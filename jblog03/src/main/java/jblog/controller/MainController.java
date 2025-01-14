package jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping({"/", "/main"})
	// 왜 메인쓰면 뭐라고함? 장수민씨?
	public String index(Model model) {
		return "main/index";
	}
}
