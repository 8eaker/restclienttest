package UseCase;

/**
 * Created by tony on 12/11/2016.
 */
public class GetRankUseCase implements Interactor<GetRankUseCaseRequest, GetRankUseCaseResponse> {
    public GetRankUseCaseResponse execute(GetRankUseCaseRequest var1) {
        return new GetRankUseCaseResponse(1);
    }
}
