package uk.co.bluetangerine.social.service.usecase;

/**
 * Created by tony on 13/11/2016.
 */
public class UpdateRankUseCaseRequest implements UseCaseRequest{
    private int storyId;
    private boolean increment;

    public UpdateRankUseCaseRequest(int storyId, boolean increment) {
        this.storyId = storyId;
        this.increment = increment;
    }

    int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    boolean isIncrement() {
        return increment;
    }

    public void setIncrement(boolean increment) {
        this.increment = increment;
    }
}
