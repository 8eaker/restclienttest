package UseCase;

/**
 * Created by tony on 12/11/2016.
 */
public interface Interactor <REQ extends UseCaseRequest, RES extends UseCaseResponse> {
    RES execute(REQ var1);
}
