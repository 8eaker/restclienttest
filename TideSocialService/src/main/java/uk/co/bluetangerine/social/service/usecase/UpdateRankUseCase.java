package uk.co.bluetangerine.social.service.usecase;

import uk.co.bluetangerine.social.service.domain.story.Story;
import uk.co.bluetangerine.social.service.domain.story.StoryRepository;
import uk.co.bluetangerine.social.service.domain.story.StoryRepositorySingleton;

/**
 * Created by tony on 12/11/2016.
 */
public class UpdateRankUseCase implements Interactor<UpdateRankUseCaseRequest, UpdateUseCaseResponse> {
    private StoryRepository storyRepository = StoryRepositorySingleton.getInstance();
    public UpdateUseCaseResponse execute(UpdateRankUseCaseRequest var1) {
        Story story = storyRepository.getStory(var1.getStoryId());

        if (null != story) {
            if (var1.isIncrement()) {
                story.incrementRank();
            } else {
                story.decrementRank();
            }
        } else {
            return null;
        }
        return new UpdateUseCaseResponse(story.getStoryId(), story.getRank());
    }
}
