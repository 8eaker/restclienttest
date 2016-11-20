package uk.co.bluetangerine.social.service.usecase;

/**
 * Created by tony on 12/11/2016.
 */
public class GetRankUseCaseRequest implements UseCaseRequest {
    private int id;

    public GetRankUseCaseRequest(int id) {
        this.id = id;
    }

    int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
