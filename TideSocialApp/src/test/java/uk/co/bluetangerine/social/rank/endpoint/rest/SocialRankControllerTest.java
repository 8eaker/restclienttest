package uk.co.bluetangerine.social.rank.endpoint.rest;

import com.sun.net.httpserver.HttpExchange;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.bluetangerine.social.service.SocialRankService;
import uk.co.bluetangerine.social.service.dto.StoryRankDto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

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
    @Mock
    SocialRankService socialRankServiceMock;

    URI uri;


    @Before
    public void setUp() throws Exception {
        socialRankServiceMock = Mockito.mock(SocialRankService.class);
        underTest = new SocialRankController(socialRankServiceMock);
        httpExchangeMock = Mockito.mock(HttpExchange.class);
        outputStream = Mockito.mock(OutputStream.class);
    }

    @Test
    public void givenValidRequestWhenGetStoryThenReturn200Status() throws Exception {
        uri = new URI("http", "a.b.c", null, "id=1", null);


        Mockito.doReturn("GET").when(httpExchangeMock).getRequestMethod();
        Mockito.doReturn(uri).when(httpExchangeMock).getRequestURI();
        Assert.assertTrue(underTest.validRequest(httpExchangeMock));

     }

    @Test
    public void givenInvalidRequestWhenGetStoryThenReturn400Status() throws Exception {
        uri = new URI("http", "a.b.c", null, "name=something", null);

        Mockito.doReturn(uri).when(httpExchangeMock).getRequestURI();
        OutputStream os = new ByteArrayOutputStream();
        Assert.assertFalse(underTest.validRequest(httpExchangeMock));
    }

    @Test
    public void givenValidParamStringWithSuffixThenReturnStringMap() {
        String queryMock = "id=19/like";
        Map<String, Integer> result = underTest.getRequestParams(queryMock);
        Assert.assertEquals(result.size(), 1);
        Integer i = result.get("id");
        Assert.assertEquals(i, new Integer(19));
    }

    @Test
    public void givenValidParamStringWithoutSuffixThenReturnStringMap() {
        String queryMock = "id=19";
        Map<String, Integer> result = underTest.getRequestParams(queryMock);
        Assert.assertEquals(result.size(), 1);
        Integer i = result.get("id");
        Assert.assertEquals(i, new Integer(19));
    }

    @Test
    public void givenInvalidParamStringThenReturnEmptyStringMap() {
        String queryMock = "incorrectQueryValue";
        Map<String, Integer> result = underTest.getRequestParams(queryMock);
        Assert.assertEquals(result.size(), 0);
    }

    @Test
    public void givenValidParamStringWhenSuffixAppendedThenReturnString() {
        String queryMock = "id=19/like";
        String result = underTest.getRequestSuffix(queryMock);
        Assert.assertEquals(result, "like");
    }

    @Test
    public void givenValidParamStringWhenNoSuffixAppendedThenReturnEmptyString() {
        String queryMock = "id=19";
        String result = underTest.getRequestSuffix(queryMock);
        Assert.assertEquals(result, "");
    }

    @Test
    public void givenValidDtoWhenStatusCode200ThenSendResponse() throws IOException {
        String message = "Valid response";
        int code = 200;
        OutputStream osMock = Mockito.mock(OutputStream.class);
        StoryRankDto storyRankDto = new StoryRankDto(1,55);
        Mockito.doReturn(osMock).when(httpExchangeMock).getResponseBody();
        underTest.sendResponse(httpExchangeMock, code, message, storyRankDto);

        Mockito.verify(httpExchangeMock, Mockito.atLeast(1)).sendResponseHeaders(200, 17);
        Mockito.verify(osMock, Mockito.atLeast(1)).write(Mockito.any(byte[].class));
        Mockito.verify(osMock, Mockito.atLeast(1)).close();
    }

    @Test
    public void givenValidDtoWhenStatusCode400ThenSendResponse() throws IOException {
        String message = "Something went wrong";
        int code = 400;
        OutputStream osMock = Mockito.mock(OutputStream.class);
        StoryRankDto storyRankDto = new StoryRankDto(1,55);
        Mockito.doReturn(osMock).when(httpExchangeMock).getResponseBody();
        underTest.sendResponse(httpExchangeMock, code, message, storyRankDto);

        Mockito.verify(httpExchangeMock, Mockito.atLeast(1)).sendResponseHeaders(400, 20);
        Mockito.verify(osMock, Mockito.atLeast(1)).write(Mockito.any(byte[].class));
        Mockito.verify(osMock, Mockito.atLeast(1)).close();
    }


    //@Ignore //Ignorning due to issue with sendResponse nullPointer Exception
    @Test
    public void givenValidRequestThenHandleSendResponse() throws IOException {
        SocialRankController underTestSpy = Mockito.spy(underTest);
        URI uriMock = Mockito.mock(URI.class);

        Mockito.doReturn(true).when(underTestSpy).validRequest(httpExchangeMock);
        Map<String, Integer> queryMap = new HashMap();
        queryMap.put("id", 1);

        StoryRankDto storyRankDto = new StoryRankDto(1,55);

        Mockito.doReturn(true).when(underTestSpy).validRequest(httpExchangeMock);
        Mockito.doReturn(uriMock).when(httpExchangeMock).getRequestURI();
        Mockito.doReturn("id=1").when(uriMock).getQuery();
        Mockito.doReturn("GET").when(httpExchangeMock).getRequestMethod();
        Mockito.doReturn(storyRankDto).when(socialRankServiceMock).getStoryRank(1);
        Mockito.doNothing().when(underTestSpy).sendResponse(Mockito.any(HttpExchange.class), Mockito.anyInt(), Mockito.anyString(), Mockito.any(StoryRankDto.class));

        underTestSpy.handle(httpExchangeMock);

        //Mockito.verify(underTestSpy, Mockito.calls(1)).sendResponse(Mockito.any(HttpExchange.class), Mockito.anyInt(), Mockito.anyString(), Mockito.any(StoryRankDto.class));

    }
}
