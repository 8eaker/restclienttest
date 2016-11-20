package uk.co.bluetangerine.social.service.usecase;

/**
 * Created by tony on 12/11/2016.
 */
public class GetRankUseCaseResponse implements UseCaseResponse {
    private int storyId = 0;
    private int rank = 0;

    GetRankUseCaseResponse(int storyId, int rank) {
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
        rank = rank;
    }
}
