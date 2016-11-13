package uk.co.bluetangerine.social.service.usecase;

/**
 * Created by tony on 13/11/2016.
 */
public class SetRankUseCaseResponse implements UseCaseResponse{
    int Rank = 0;

    public SetRankUseCaseResponse(int rank) {
        Rank = rank;
    }

    public int getRank() {
        return Rank;
    }

    public void setRank(int rank) {
        Rank = rank;
    }
}
