package UseCase;

/**
 * Created by tony on 12/11/2016.
 */
public class GetRankUseCaseResponse implements UseCaseResponse {
    int Rank = 0;

    public GetRankUseCaseResponse(int rank) {
        Rank = rank;
    }

    public int getRank() {
        return Rank;
    }

    public void setRank(int rank) {
        Rank = rank;
    }
}
