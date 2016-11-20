package uk.co.bluetangerine.social.service.domain.story;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by tony on 20/11/2016.
 */
public class DefaultStoryRepositoryTest {
    DefaultStoryRepository underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new DefaultStoryRepository();
    }

    @Test
    public void givenValidStoryIdThenReturnStory() {
        Story response = underTest.getStory(1);
        Assert.assertEquals(response.getRank(), 5);
    }

    @Test
    public void givenValidStoryWhenSaveThenSuccess() {
        //public synchronized Story saveStory (Story story){
        Story request = new Story(underTest.getNextId(),99);
        Story response = underTest.saveStory(request);
        Assert.assertEquals(response.getStoryId(), 4);
        Assert.assertEquals(response.getRank(), 99);
    }
}
