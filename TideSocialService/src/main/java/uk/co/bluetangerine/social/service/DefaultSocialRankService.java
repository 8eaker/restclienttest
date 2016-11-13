package uk.co.bluetangerine.social.service;

import uk.co.bluetangerine.social.service.usecase.*;

/**
 * Created by tony on 12/11/2016.
 */

public class DefaultSocialRankService implements SocialRankService {
    GetRankUseCase getRankUseCase;
    SetRankUseCase setRankUseCase;
    UpdateRankUseCase updateRankUseCase;

    public DefaultSocialRankService() {
        getRankUseCase = new GetRankUseCase();
        setRankUseCase = new SetRankUseCase();
        updateRankUseCase = new UpdateRankUseCase();
    }

    public Integer getStoryRank(Integer id) {
        GetRankUseCaseResponse getRankUseCaseResponse = getRankUseCase.execute(new GetRankUseCaseRequest(id));
        return (getRankUseCaseResponse == null) ? null : getRankUseCaseResponse.getRank();
    }

    public Integer setStoryRank(Integer id, Integer rank) {
        return setRankUseCase.execute(new SetRankUseCaseRequest(id, rank)).getRank();
    }

    public Integer incrementStoryRank(Integer id) {
       return updateRankUseCase.execute(new UpdateRankUseCaseRequest(id, true)).getRank();
    }

    public Integer decrementStoryRank(Integer id) {
        return updateRankUseCase.execute(new UpdateRankUseCaseRequest(id, false)).getRank();
    }
}
