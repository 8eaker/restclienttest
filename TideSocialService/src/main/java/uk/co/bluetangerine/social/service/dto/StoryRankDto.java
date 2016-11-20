package uk.co.bluetangerine.social.service.dto;

/**
 * Created by tony on 13/11/2016.
 * Simple storyRank Dto class
 */
public class StoryRankDto {
    transient private int storyId;
    private int popularity;

    public StoryRankDto(int storyId, int popularity) {
        this.storyId = storyId;
        this.popularity = popularity;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}
