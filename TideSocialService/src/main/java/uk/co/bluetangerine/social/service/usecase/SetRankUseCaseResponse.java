package uk.co.bluetangerine.social.service.usecase;

/**
 * Created by tony on 13/11/2016.
 */
public class SetRankUseCaseResponse implements UseCaseResponse{
    private int storyId = 0;
    private int rank = 0;

    SetRankUseCaseResponse(int storyId, int rank) {
        this.storyId = storyId;
        this.rank = rank;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
