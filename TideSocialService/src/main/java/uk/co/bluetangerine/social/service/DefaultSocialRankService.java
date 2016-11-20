package uk.co.bluetangerine.social.service;

import uk.co.bluetangerine.social.service.dto.StoryRankDto;
import uk.co.bluetangerine.social.service.usecase.*;

/**
 * Created by tony on 12/11/2016.
 * Service implementation for the main
 * actions of the application
 */

public class DefaultSocialRankService implements SocialRankService {
    private GetRankUseCase getRankUseCase;
    private SetRankUseCase setRankUseCase;
    private UpdateRankUseCase updateRankUseCase;

    public DefaultSocialRankService() {
        getRankUseCase = new GetRankUseCase();
        setRankUseCase = new SetRankUseCase();
        updateRankUseCase = new UpdateRankUseCase();
    }

    public StoryRankDto getStoryRank(Integer id) {
        GetRankUseCaseResponse getRankUseCaseResponse = getRankUseCase.execute(new GetRankUseCaseRequest(id));
        return (getRankUseCaseResponse == null) ? null : new StoryRankDto(getRankUseCaseResponse.getStoryId(), getRankUseCaseResponse.getRank());
    }

    public StoryRankDto setStoryRank(Integer id, Integer rank) {
        SetRankUseCaseResponse setUseCaseResponse = setRankUseCase.execute(new SetRankUseCaseRequest(id, rank));
        return (setUseCaseResponse == null) ? null : new StoryRankDto(setUseCaseResponse.getStoryId(), setUseCaseResponse.getRank());
    }

    public StoryRankDto incrementStoryRank(Integer id) {
        UpdateUseCaseResponse updateUseCaseResponse = updateRankUseCase.execute(new UpdateRankUseCaseRequest(id, true));
        return (updateUseCaseResponse == null) ? null : new StoryRankDto(updateUseCaseResponse.getStoryId(), updateUseCaseResponse.getRank());
    }

    public StoryRankDto decrementStoryRank(Integer id) {
        UpdateUseCaseResponse updateUseCaseResponse = updateRankUseCase.execute(new UpdateRankUseCaseRequest(id, false));
        return (updateUseCaseResponse == null) ? null : new StoryRankDto(updateUseCaseResponse.getStoryId(), updateUseCaseResponse.getRank());
    }
}
