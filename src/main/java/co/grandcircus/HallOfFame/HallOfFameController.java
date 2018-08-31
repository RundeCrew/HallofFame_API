package co.grandcircus.HallOfFame;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.HallOfFame.model.Complete;
import co.grandcircus.HallOfFame.model.Results;
import co.grandcircus.HallOfFame.model.Tiny;

@Controller
public class HallOfFameController {

	
	@RequestMapping("/")
	public ModelAndView tinyList() {
		ModelAndView mav = new ModelAndView("index");
		
		RestTemplate restTemplate = new RestTemplate();
		
		String url = "https://dwolverton.github.io/fe-demo/data/computer-science-hall-of-fame.json";
		
//		Tiny tiny = restTemplate.getForObject(
//        		url, Tiny.class);
		
		ResponseEntity<Results> response = restTemplate.exchange(url, HttpMethod.GET,
				new HttpEntity<>(null), Results.class);
		
		Results result = response.getBody();
		mav.addObject("tiny", result.getTinys());	
		return mav;
	}
	
	@RequestMapping("/complete")
	public ModelAndView completeList() {
		ModelAndView mav = new ModelAndView("complete");
		
		RestTemplate restTemplate = new RestTemplate();
		
		String url = "https://dwolverton.github.io/fe-demo/data/computer-science-hall-of-fame.json";

		ResponseEntity<Results> response = restTemplate.exchange(url, HttpMethod.GET,
				new HttpEntity<>(null), Results.class);
		
		Results result = response.getBody();
		
		mav.addObject("completes", result.getCompletes());
	
		return mav;
	}
}
