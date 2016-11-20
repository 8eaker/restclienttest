package uk.co.bluetangerine.social.rank.endpoint.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import uk.co.bluetangerine.social.service.dto.StoryRankDto;
import uk.co.bluetangerine.social.service.DefaultSocialRankService;
import uk.co.bluetangerine.social.service.SocialRankService;

/**
 * Created by tony on 12/11/2016.
 * Class to manage incoming requests and route them to the appropriate
 * methods
 */
public class SocialRankController implements HttpHandler {
    //Set up the SocialRankService. We only have one implementation so far.
    //but in the future, alternate implementations could be used using this
    //interface
    private SocialRankService socialRankService = new DefaultSocialRankService();

    public SocialRankController() {
    }

    //Constructor to assist testing
    public SocialRankController(SocialRankService socialRankService) {
        this.socialRankService = socialRankService;
    }

    /**
     * Method to handle and map the calls to the appropriate service calls
     * @param httpExchange from server
     * @throws IOException
     */
    public void handle(HttpExchange httpExchange) throws IOException {
        StoryRankDto storyRankDto = null;
        //Default Bad request responseCode;
        Integer responseCode = 400;
        String responseMessage = "Invalid request";

        //Perform simple validation before getting started
        if (validRequest(httpExchange)) {
            //Get the request parameters i.e. Id
            Map<String, Integer> params = getRequestParams(httpExchange.getRequestURI().getQuery());
            //Check the HTTP verb to help route the request
            if (httpExchange.getRequestMethod().equals("GET")) {
                //From the basic validation, we already know Id is set, so safe to go ahead
                storyRankDto = socialRankService.getStoryRank(params.get("id"));
                //If we find a story, then set the DTO and success response code
                if (null != storyRankDto) {
                    responseCode = 200;
                } else {
                    responseCode = 404;
                    responseMessage = "Story Not Found";
                }
            } else if (httpExchange.getRequestMethod().equals("POST")) {
                storyRankDto = socialRankService.setStoryRank(params.get("id"), params.get("rank"));
                if (null != storyRankDto) {
                    responseCode = 200;
                } else {
                    responseCode = 404;
                    responseMessage = "Story Not Found. Unable to set story";
                }
            } else if (httpExchange.getRequestMethod().equals("PUT") &&
                    getRequestSuffix(httpExchange.getRequestURI().getQuery()).equals("dislike")) {
                storyRankDto = socialRankService.decrementStoryRank(params.get("id"));
                if (null != storyRankDto) {
                    responseCode = 200;
                } else {
                    responseCode = 404;
                    responseMessage = "Story Not Found. Unable to dislike story";
                }
            } else if (httpExchange.getRequestMethod().equals("PUT") &&
                    getRequestSuffix(httpExchange.getRequestURI().getQuery()).equals("like")) {
                storyRankDto = socialRankService.incrementStoryRank(params.get("id"));
                if (null != storyRankDto) {
                    responseCode = 200;
                } else {
                    responseCode = 404;
                    responseMessage = "Story Not Found. Unable to like story";
                }
            }
        }
        //Send the appropriate response. IF none of the above conditions were met, we will
        //send back the default invalid request message
        sendResponse(httpExchange, responseCode, responseMessage, storyRankDto);
    }

    /**
     * Simple pre validation routine
     *
     * @param httpExchange from server
     * @return Boolean indicating if request successfully validated
     * @throws IOException
     */
    Boolean validRequest(HttpExchange httpExchange) throws IOException {
        String query = httpExchange.getRequestURI().getQuery();
        //All calls current require at least an id, so if nothing is present,
        //we can bail out early
        if (null == query) {
            return false;
        }
        //Spilt out the queries to string array
        String[] querySplit = httpExchange.getRequestURI().getQuery().split("/");
        // If the request contains at least ID param and is a GET,POST or PUT with additional parameter of 'likes'
        if (querySplit[0].substring(0, querySplit[0].indexOf('=')).equals("id")) {
            if ((httpExchange.getRequestMethod().equals("GET") || httpExchange.getRequestMethod().equals("POST"))) {
                return true;
            } else if (httpExchange.getRequestMethod().equals("PUT") && (querySplit.length > 1 &&
                    querySplit[1].equals("like") || querySplit.length > 1 && querySplit[1].equals("dislike"))) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * Send JSON response. If successful (200) parse Dto to json for response, else send code
     * and error message
     * @param httpExchange from the server
     * @param code http status code
     * @param message for codes other than success 200
     * @param storyRankDto containing details of StoryRank to output
     * @throws IOException
     */
    void sendResponse(HttpExchange httpExchange, int code, String message, StoryRankDto storyRankDto) throws IOException {
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

    /**
     * Split out request parameters into a map for easier consumption by controller
     * @param query String
     * @return Map of ids
     */
    Map<String, Integer> getRequestParams(String query) {
        Map<String, Integer> result = new HashMap<String, Integer>();
        //Get params before last / if there is one
        for (String param : query.substring(0,(query.indexOf('/') < 0) ? query.length() : query.indexOf('/')).split("&")) {
            //Split by id/value splitter of -
            String pair[] = param.split("=");
            if (pair.length > 1) {
                result.put(pair[0], Integer.valueOf(pair[1]));
            }
        }
        return result;
    }

    /**
     * Get request suffix after params if there are any.
     * Used currently for like/dislike calls
     * @param query String
     * @return String value of suffix
     */
    String getRequestSuffix(String query) {
        if (query.indexOf('/') > 0) {
            return query.substring(query.indexOf('/') + 1, query.length());
        }
        return "";
    }
}
