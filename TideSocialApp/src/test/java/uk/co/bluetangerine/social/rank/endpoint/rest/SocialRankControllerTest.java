package uk.co.bluetangerine.social.rank.endpoint.rest;

import com.sun.net.httpserver.HttpExchange;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URI;

/**
 * Created by tony on 12/11/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class SocialRankControllerTest {

    SocialRankController underTest;
    @Mock
    HttpExchange httpExchangeMock;
    @Mock
    OutputStream outputStream;

    URI uri;


    @Before
    public void setUp() throws Exception {
        underTest = new SocialRankController();
        httpExchangeMock = Mockito.mock(HttpExchange.class);
        outputStream = Mockito.mock(OutputStream.class);
    }

    @Test
    public void givenValidRequestWhenGetStoryThenReturn200Status() throws Exception {
        uri = new URI("http", "a.b.c", null, "id=1", null);


        Mockito.doReturn("GET").when(httpExchangeMock).getRequestMethod();
        Mockito.doReturn(uri).when(httpExchangeMock).getRequestURI();
        Mockito.doReturn(outputStream).when(httpExchangeMock).getResponseBody();

        Assert.assertTrue(underTest.validRequest(httpExchangeMock));

     }

    @Test
    public void givenInvalidRequestWhenGetStoryThenReturn400Status() throws Exception {
        uri = new URI("http", "a.b.c", null, "name=something", null);

        Mockito.doReturn("GET").when(httpExchangeMock).getRequestMethod();
        Mockito.doReturn(uri).when(httpExchangeMock).getRequestURI();
        OutputStream os = new ByteArrayOutputStream();
        Mockito.doReturn(os).when(httpExchangeMock).getResponseBody();
        Assert.assertFalse(underTest.validRequest(httpExchangeMock));
        Mockito.verify(httpExchangeMock, Mockito.times(1)).sendResponseHeaders(400, 35);
    }




}