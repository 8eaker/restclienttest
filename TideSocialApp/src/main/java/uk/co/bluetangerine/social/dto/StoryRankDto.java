package uk.co.bluetangerine.social.dto;

/**
 * Created by tony on 13/11/2016.
 */
public class StoryRankDto {
    private int popularity;

    public StoryRankDto(int popularity) {
        this.popularity = popularity;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}
