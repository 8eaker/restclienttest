package uk.co.bluetangerine.social.service.usecase;

import uk.co.bluetangerine.social.service.domain.story.DefaultStoryRepository;
import uk.co.bluetangerine.social.service.domain.story.Story;
import uk.co.bluetangerine.social.service.domain.story.StoryRepository;

/**
 * Created by tony on 12/11/2016.
 */
public class SetRankUseCase implements Interactor<SetRankUseCaseRequest, SetRankUseCaseResponse> {
    StoryRepository storyRepository = new DefaultStoryRepository();
    public SetRankUseCaseResponse execute(SetRankUseCaseRequest var1) {
        Story updatedStory = storyRepository.updateStory(new Story(var1.getRank()));
        return new SetRankUseCaseResponse(updatedStory.getRank());
    }
}
