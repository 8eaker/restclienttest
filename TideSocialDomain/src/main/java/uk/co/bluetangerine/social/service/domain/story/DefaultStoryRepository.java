package uk.co.bluetangerine.social.service.domain.story;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tony on 13/11/2016.
 */
public class DefaultStoryRepository implements StoryRepository {
    Map<Integer, Story> stories = new HashMap<Integer, Story>();

    public DefaultStoryRepository() {
        // As this just in memory, start with a few Stories
        // to test with.
        saveStory(new Story(5));
        saveStory(new Story(0));
        saveStory(new Story(15));
    }

    public Story getStory(int id) {
        return stories.get(id);
    }

    public Story saveStory(Story story) {
        story.setStoryId(getNextId());
        stories.put(story.getStoryId(), story);
        return stories.get(story.getStoryId());
    }

    public synchronized Story updateStory(Story story) {
        Story toUpdate = stories.get(story.getStoryId());
        toUpdate.setRank(story.getRank());
        return toUpdate;
    }

    private synchronized int getNextId() {
        return stories.size() + 1;
    }
}
