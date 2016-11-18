package uk.co.bluetangerine.social.rank.endpoint.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import uk.co.bluetangerine.social.dto.StoryRankDto;
import uk.co.bluetangerine.social.service.DefaultSocialRankService;
import uk.co.bluetangerine.social.service.SocialRankService;

/**
 * Created by tony on 12/11/2016.
 * Class to manage incoming requests and route them to the appropriate
 * methods
 */
public class SocialRankController implements HttpHandler {
    SocialRankService socialRankService = new DefaultSocialRankService();

    public void handle(HttpExchange httpExchange) throws IOException {
        StoryRankDto storyRankDto = null;
        //Default Bad request responseCode;
        Integer responseCode = 400;
        String responseMessage "Invalid request";

        //All current requests must have an Id, so check first
        if (httpExchange.getRequestURI().getQuery().contains("id")) {
            //Get the request parameters i.e. Id
            Map<String, Integer> params = getRequestParams(httpExchange.getRequestURI().getQuery());
            //Check the HTTP verb to help route the request
            if (httpExchange.getRequestMethod().equals("GET")) {
                Integer storyRank = socialRankService.getStoryRank(params.get("id"));
                if (null != storyRank) {
                    storyRankDto = new StoryRankDto(storyRank));
                    responseCode = 200;
                } else {
                    responseCode = 404;
                    responseMessage = "Story Not Found";
                }
            } else if (httpExchange.getRequestMethod().equals("POST")) {
                //TODO: hardcoded parameters!
                socialRankService.setStoryRank(1, 1);
                //TODO: return??
            } else if (httpExchange.getRequestMethod().equals("PUT") && httpExchange.getRequestURI().getQuery().contains("dislike")) {
                storyRankDto = new StoryRankDto(socialRankService.decrementStoryRank(params.get("id")));
                if (null != storyRankDto) {
                    responseCode = 200;
                } else {
                    responseCode = 404;
                    responseMessage = "Story Not Found. Unable to dislike story";
                }
            } else if (httpExchange.getRequestMethod().equals("PUT") && httpExchange.getRequestURI().getQuery().contains("like")) {
                storyRankDto = new StoryRankDto(socialRankService.incrementStoryRank(params.get("id")));
                if (null != storyRankDto) {
                    responseCode = 200;
                } else {
                    responseCode = 404;
                    responseMessage = "Story Not Found. Unable to like story";
                }
            }
        }
        //Send the appropriate response
        sendResponse(httpExchange, responseCode, responseMessage, storyRankDto);
    }

    /**
     * Simple pre validation routine
     *
     * @param httpExchange
     * @return
     * @throws IOException
     */
    protected Boolean validRequest(HttpExchange httpExchange) throws IOException {
        String response;
        // If the request contains at least ID param and is a GET,POST or PUT with additional parameter of 'likes'
        if (httpExchange.getRequestURI().getQuery().contains("id")) {
            if ((httpExchange.getRequestMethod().equals("GET") || httpExchange.getRequestMethod().equals("POST")) {
                return true;
            } else if (httpExchange.getRequestMethod().equals("PUT") && httpExchange.getRequestURI().getQuery().contains("likes")) {
                return true;
            }
            return false;
        }

    protected void sendResponse(HttpExchange httpExchange, int code, String message, StoryRankDto storyRankDto) throws IOException {
        Gson gson = new Gson();
        OutputStream os;
        if (code == 200) {
            httpExchange.sendResponseHeaders(code, gson.toJson(storyRankDto).length());
            os = httpExchange.getResponseBody();
            os.write(gson.toJson(storyRankDto).getBytes());
        } else {
            httpExchange.sendResponseHeaders(code, message.length());
            os = httpExchange.getResponseBody();
            os.write(message.getBytes());
        }
        os.close();
    }

    protected Map<String, Integer> getRequestParams(String query) {
        Map<String, Integer> result = new HashMap<String, Integer>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], Integer.valueOf(pair[1]));
            } else {
                result.put(pair[0], 0);
            }
        }
        return result;
    }
}
