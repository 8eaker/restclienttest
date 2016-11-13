package uk.co.bluetangerine.social.rank.endpoint.rest;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import uk.co.bluetangerine.social.dto.StoryRankDto;
import uk.co.bluetangerine.social.service.DefaultSocialRankService;
import uk.co.bluetangerine.social.service.SocialRankService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tony on 12/11/2016.
 */
public class SocialRankController  implements HttpHandler{
    SocialRankService socialRankService = new DefaultSocialRankService();

    public void handle(HttpExchange httpExchange) throws IOException {
        StoryRankDto storyRankDto = null;

       if (validRequest(httpExchange)) {
           Map<String, Integer> params = getRequestParams(httpExchange.getRequestURI().getQuery());
           if (httpExchange.getRequestMethod().equals("GET")) {
               Integer storyRank = socialRankService.getStoryRank(params.get("id"));
               if (null != storyRank) {
                   storyRankDto = new StoryRankDto(socialRankService.getStoryRank(params.get("id")));
               }
                if (null != storyRankDto) {
                    sendResponse(httpExchange, 200, null, storyRankDto);
                } else {
                    sendResponse(httpExchange, 404, "Story Not found", null);
                }
           } else if (httpExchange.getRequestMethod().equals("POST")) {
               socialRankService.setStoryRank(1,1);
           } else if (httpExchange.getRequestMethod().equals("PUT") && httpExchange.getRequestURI().getQuery().contains("dislike")) {
               storyRankDto = new StoryRankDto(socialRankService.decrementStoryRank(params.get("id")) );
               sendResponse(httpExchange, 200, null, storyRankDto);
           } else if (httpExchange.getRequestMethod().equals("PUT") && httpExchange.getRequestURI().getQuery().contains("like")) {
               storyRankDto = new StoryRankDto(socialRankService.incrementStoryRank(params.get("id")));
               sendResponse(httpExchange, 200, null, storyRankDto);
           }

           sendResponse(httpExchange, 404, "Success", null);
       }
    }

    protected Boolean validRequest(HttpExchange httpExchange) throws IOException {
        String response;

        if ((httpExchange.getRequestMethod().equals("GET") || httpExchange.getRequestMethod().equals("POST"))
                && httpExchange.getRequestURI().getQuery().contains("id")) {
            return true;
        } else if (httpExchange.getRequestMethod().equals("PUT") ) {
            return true;
        } else {
            response = "Invalid request parameter specified";
            sendResponse(httpExchange, 400, response, null);
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
            }else{
                result.put(pair[0], 0);
            }
        }
        return result;
    }
}
