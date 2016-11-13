package uk.co.bluetangerine.social.service.usecase;

import uk.co.bluetangerine.social.service.domain.story.DefaultStoryRepository;
import uk.co.bluetangerine.social.service.domain.story.Story;
import uk.co.bluetangerine.social.service.domain.story.StoryRepository;

/**
 * Created by tony on 12/11/2016.
 */
public class UpdateRankUseCase implements Interactor<UpdateRankUseCaseRequest, UpdateUseCaseResponse> {
    StoryRepository storyRepository = new DefaultStoryRepository();
    public UpdateUseCaseResponse execute(UpdateRankUseCaseRequest var1) {
        Story story = storyRepository.getStory(var1.getStoryId());

        if (var1.isIncrement()) {
            story.incrementRank();
        } else {
            story.decrementRank();
        }
        return new UpdateUseCaseResponse(story.getRank());
    }
}
