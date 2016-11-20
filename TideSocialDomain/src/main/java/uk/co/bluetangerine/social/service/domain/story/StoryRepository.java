package uk.co.bluetangerine.social.service.domain.story;

/**
 * Created by tony on 13/11/2016.
 * Interface for StoryRepository
 * in order to support future implementations
 */
public interface StoryRepository {
    Story getStory(int id);
    Story saveStory(Story story);
    Story updateStory(Story story);
}
