package uk.co.bluetangerine.social.service.usecase;

import uk.co.bluetangerine.social.service.domain.story.DefaultStoryRepository;
import uk.co.bluetangerine.social.service.domain.story.Story;
import uk.co.bluetangerine.social.service.domain.story.StoryRepository;

/**
 * Created by tony on 12/11/2016.
 */
public class GetRankUseCase implements Interactor<GetRankUseCaseRequest, GetRankUseCaseResponse> {
    //TODO: get same object for executors
    StoryRepository storyRepository = new DefaultStoryRepository();
    public GetRankUseCaseResponse execute(GetRankUseCaseRequest var1) {
        Story story = storyRepository.getStory(var1.getId());
        if (null != story) {
             return new GetRankUseCaseResponse(story.getRank());
        }
        return null;
    }
}