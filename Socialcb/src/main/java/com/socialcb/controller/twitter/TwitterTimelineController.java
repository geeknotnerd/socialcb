/**
 * 
 */
package com.socialcb.controller.twitter;

import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
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

	private static final Logger logger = LoggerFactory.getLogger(TwitterTimelineController.class);

	// @Inject
	// ClassPathResource resource;

	@Inject
	private UserConnectionService userConnectionService;

	private Twitter twitter;

	@RequestMapping(value = "/twitter/timeline/{timelineType}", method = RequestMethod.GET)
	public @ResponseBody
	Collection<Tweet> showTimeline(@PathVariable("timelineType") String timelineType) throws IOException {

		Resource resource = new ClassPathResource("application.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		String consumerKey = props.getProperty("twitter.consumerKey");
		String consumerSecret = props.getProperty("twitter.consumerSecret");

		UserConnection userConnection = userConnectionService.getUserConnection();
		twitter = new TwitterTemplate(consumerKey, consumerSecret, userConnection.getAccessToken(),
				userConnection.getSecret());

		Collection<Tweet> timeLineTweets = null;
		if (timelineType.equals("Home")) {
			timeLineTweets = twitter.timelineOperations().getHomeTimeline();
		} else if (timelineType.equals("User")) {
			timeLineTweets = twitter.timelineOperations().getUserTimeline();
		} else if (timelineType.equals("Mentions")) {
			timeLineTweets = twitter.timelineOperations().getMentions();
		} else if (timelineType.equals("Favorites")) {
			timeLineTweets = twitter.timelineOperations().getFavorites();
		}

		return timeLineTweets;
	}

}
