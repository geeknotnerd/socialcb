/**
 * 
 */
package com.socialcb.controller.twitter;

import java.util.Collection;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socialcb.model.UserConnection;
import com.socialcb.service.UserConnectionService;

/**
 * @author sagarpatil
 * 
 */
@Controller
public class TwitterTimelineController {

	private static final Logger logger = LoggerFactory
			.getLogger(TwitterTimelineController.class);

	@Inject
	private UserConnectionService userConnectionService;
	
	private final Twitter twitter;
	
	public TwitterTimelineController() {
	 UserConnection userConnection= userConnectionService.getUserConnection();
	 twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}

//	@RequestMapping(value = "/twitter/timeline/{timelineType}", method = RequestMethod.GET)
//	public @ResponseBody
//	Temp showTimeline(@PathVariable("timelineType") String timelineType,
//			Model model) {
//
//		Temp tmp = new Temp();
//		tmp.setKey("KeyOne");
//		tmp.setValue("ValueOne");
//		tmp.getMyArrayList().add("One");
//		tmp.getMyArrayList().add("Two");
//		return tmp;
//	}

	@RequestMapping(value = "/twitter/timeline/{timelineType}", method = RequestMethod.GET)
	public @ResponseBody Collection<Tweet> showTimeline(
			@PathVariable("timelineType") String timelineType, Model model) {
		Collection<Tweet> timeLineTweets= null;
		if (timelineType.equals("Home")) {
			timeLineTweets = twitter.timelineOperations()
					.getHomeTimeline();
		} else if (timelineType.equals("User")) {
			timeLineTweets = twitter.timelineOperations()
					.getUserTimeline();
		} else if (timelineType.equals("Mentions")) {
			timeLineTweets = twitter.timelineOperations()
					.getMentions();
		} else if (timelineType.equals("Favorites")) {
			timeLineTweets = twitter.timelineOperations()
					.getFavorites();
		}
		
		return timeLineTweets;
	}

}
