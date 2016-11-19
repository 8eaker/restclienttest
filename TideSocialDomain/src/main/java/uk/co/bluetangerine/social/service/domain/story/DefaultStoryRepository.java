package uk.co.bluetangerine.social.service.domain.story;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tony on 13/11/2016.
 */
public class DefaultStoryRepository implements StoryRepository {
    Map<Integer, Story> stories = new HashMap<Integer, Story>();

    //Use private package accessor to prevent
    //creation outside of threadsafe singleton
    DefaultStoryRepository() {
        //Cheating a bit here as we have no interface
        //to create stories, so create some here to
        //test with
        saveStory(new Story(getNextId(), 5));
        saveStory(new Story(getNextId(), 0));
        saveStory(new Story(getNextId(), 15));
    }

    public Story getStory(int id) {
        return stories.get(id);
    }

    public synchronized Story saveStory(Story story) {
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
