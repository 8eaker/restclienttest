package uk.co.bluetangerine.social;

import com.sun.net.httpserver.HttpServer;
import uk.co.bluetangerine.social.rank.endpoint.rest.SocialRankController;

import java.net.InetSocketAddress;

/**
 * Created by tony on 12/11/2016.
 * Basic server to accept requests
 */
public class SocialServer {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8090), 0);
        server.createContext("/story", new SocialRankController());
        server.setExecutor(null);
        server.start();
    }
}
