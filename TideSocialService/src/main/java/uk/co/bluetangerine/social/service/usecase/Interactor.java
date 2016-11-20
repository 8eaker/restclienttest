package uk.co.bluetangerine.social.service.usecase;

/**
 * Created by tony on 12/11/2016.
 */
interface Interactor <REQ extends UseCaseRequest, RES extends UseCaseResponse> {
    RES execute(REQ var1);
}
