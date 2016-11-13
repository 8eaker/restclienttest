package uk.co.bluetangerine.social.rank.endpoint.rest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by tony on 12/11/2016.
 */
public class SocialRankController  implements HttpHandler{
    public void handle(HttpExchange httpExchange) throws IOException {
       if (validRequest(httpExchange)) {

           if (httpExchange.getRequestMethod().equals("GET")) {

           } else if (httpExchange.getRequestMethod().equals("POST")) {

           } else if (httpExchange.getRequestMethod().equals("PUT") && httpExchange.getRequestURI().getQuery().contains("like")) {

           } else if (httpExchange.getRequestMethod().equals("PUT") && httpExchange.getRequestURI().getQuery().contains("dislike")) {

           }

           sendResponse(httpExchange, 200, "Success");
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
            sendResponse(httpExchange, 400, response);
        }
        return false;

    }

    protected void sendResponse(HttpExchange httpExchange, int code, String message) throws IOException {
        httpExchange.sendResponseHeaders(code, message.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(message.getBytes());
        os.close();
    }
}
