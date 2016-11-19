package uk.co.bluetangerine.social.service.domain.story;

/**
 * Created by tony on 18/11/2016.
 */
public class StoryRepositorySingleton {
    //Class load time thread safety
    private static final StoryRepository instance = new DefaultStoryRepository();

    private StoryRepositorySingleton() { }

    public static StoryRepository getInstance() {
        return instance;
    }
}
