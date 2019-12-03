
package aima;

import facebook4j.*;
import facebook4j.auth.AccessToken;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class aima_login {
    private static final Logger logger = LogManager.getLogger(aima_app.class);
    
    Facebook facebook;
    
    public aima_login() throws FacebookException{
        facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId("452719142294191", "901cb440bd5c0c658e6875b6a03f27eb");
        String pageAccessToken =  "EAAGbvuOo0q8BALBcxdEL2a7zkoCnZADl5rSJGilYvRKKfhgx4Nk7VsSjMvrsGVhWanhnFUgzQMqUYyYCvgnqsfJbLs6X7GwaOLllE1aQ4JNAiZCgZC3ptb0vBsVw6q2X5oxqORDE2uF5ZCWIZANZBRdZBZCvMRneYqCwJ76AzZA4CZCAZDZD";
        facebook.setOAuthAccessToken(new AccessToken(pageAccessToken, null));
        
//        facebook.postStatusMessage("[Este es un mensaje Automatico desde Facebook4Java]");
        logger.info("inicializando app");
         
    }
    
    public void Publish(Facebook facebook,String Message) throws FacebookException, MalformedURLException, MalformedURLException, MalformedURLException{
         logger.info("publicando");
         facebook.postStatusMessage(Message);
         logger.info("publicado");
    
    }
   public void PublishURL(Facebook facebook) throws FacebookException, MalformedURLException{
       logger.info("publicando URL");
        PrivacyParameter privacy = new PrivacyBuilder().setValue(PrivacyType.ALL_FRIENDS).build();
        PostUpdate postUpdate = new PostUpdate(new URL("http://facebook4j.org"))
            .picture(new URL("http://facebook4j.org/images/hero.png"))
            .name("Facebook4J - A Java library for the Facebook Graph API")
            .caption("facebook4j.org")
            .description("Facebook4J is a Java library for the Facebook Graph API. This library provides the ease of use like Twitter4J. Facebook4J is an unofficial library.")
            .privacy(privacy);
        String postId = facebook.postFeed(postUpdate);
        logger.info("publicado URL");
   }
    
    public void getHome(Facebook facebook) throws FacebookException{
        ResponseList<Post> feeds = facebook.getHome();
        logger.info("Obteniendo Home");
             for (int i = 0; i < feeds.size(); i++) {
            Post post = feeds.get(i);
            String message = post.getMessage();
           
            System.out.println(message);
            PagableList<Comment> comments = post.getComments();
            String date = post.getCreatedTime().toString();
            String name = post.getFrom().getName();
            String id = post.getId();
             logger.info("Post No. "+ i );
        }  
        
    }
        
    public void getFeed(Facebook facebook, String Feed) throws FacebookException{
     ResponseList<Post> feeds = facebook.getFeed(Feed,
            new Reading().limit(25));
    logger.info("Obteniendo Feed: "+ Feed);

        for (int i = 0; i < feeds.size(); i++) {
            Post post = feeds.get(i);
            String message = post.getMessage();
            System.out.println(message);

            PagableList<Comment> comments = post.getComments();
            String date = post.getCreatedTime().toString();
            String name = post.getFrom().getName();
            String id = post.getId();
            logger.info("Post No. "+ i );
        }           
    }
    
}
