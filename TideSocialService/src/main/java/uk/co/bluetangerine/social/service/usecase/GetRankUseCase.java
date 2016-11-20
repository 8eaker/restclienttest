package uk.co.bluetangerine.social.service.usecase;

import uk.co.bluetangerine.social.service.domain.story.Story;
import uk.co.bluetangerine.social.service.domain.story.StoryRepository;
import uk.co.bluetangerine.social.service.domain.story.StoryRepositorySingleton;

/**
 * Created by tony on 12/11/2016.
 */
public class GetRankUseCase implements Interactor<GetRankUseCaseRequest, GetRankUseCaseResponse> {
    private StoryRepository storyRepository = StoryRepositorySingleton.getInstance();
    public GetRankUseCaseResponse execute(GetRankUseCaseRequest var1) {
        Story story = storyRepository.getStory(var1.getId());
        if (null != story) {
             return new GetRankUseCaseResponse(story.getStoryId(), story.getRank());
        }
        return null;
    }
}
