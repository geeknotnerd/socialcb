/**
 * 
 */
package com.socialcb.controller.twitter;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author sagarpatil
 * 
 */
@Controller
public class TwitterTimelineController {

	private static final Logger logger = LoggerFactory
			.getLogger(TwitterTimelineController.class);

	@RequestMapping(value = "/twitter/timeline/{timelineType}", method = RequestMethod.GET)
	public @ResponseBody Temp showTimeline(
			@PathVariable("timelineType") String timelineType, Model model) {
	
		Temp tmp= new Temp();
		tmp.setKey("KeyOne");
		tmp.setValue("ValueOne");
		tmp.getMyArrayList().add("One");
		tmp.getMyArrayList().add("Two");
		return tmp;
	}
	
	
	 private static class Temp{
		private String key;
		private String value;
		private List<String> myArrayList = new ArrayList<String>();
		
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public List<String> getMyArrayList() {
			return myArrayList;
		}
		public void setMyArrayList(List<String> myArrayList) {
			this.myArrayList = myArrayList;
		}
		
		 
		
		
	 }
	

}
