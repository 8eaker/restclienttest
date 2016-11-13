package uk.co.bluetangerine.social.service.usecase;

/**
 * Created by tony on 13/11/2016.
 */
public class UpdateRankUseCaseRequest implements UseCaseRequest{
    int storyId;
    boolean increment;

    public UpdateRankUseCaseRequest(int storyId, boolean increment) {
        this.storyId = storyId;
        this.increment = increment;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public boolean isIncrement() {
        return increment;
    }

    public void setIncrement(boolean increment) {
        this.increment = increment;
    }
}
