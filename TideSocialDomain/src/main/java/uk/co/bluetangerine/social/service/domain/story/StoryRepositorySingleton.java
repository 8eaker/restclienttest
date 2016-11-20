package uk.co.bluetangerine.social.service.domain.story;

/**
 * Created by tony on 18/11/2016.
 * Singleton to return same StoryRepository
 * to each consumer. Using load time to construct
 * instance to avoid common singleton issues with
 * potential muliple creation
 */
public class StoryRepositorySingleton {
    //Class load time thread safety
    private static final StoryRepository instance = new DefaultStoryRepository();

    private StoryRepositorySingleton() { }

    public static StoryRepository getInstance() {
        return instance;
    }
}
