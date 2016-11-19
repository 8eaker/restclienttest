package uk.co.bluetangerine.social.service.usecase;

import uk.co.bluetangerine.social.service.domain.story.DefaultStoryRepository;
import uk.co.bluetangerine.social.service.domain.story.Story;
import uk.co.bluetangerine.social.service.domain.story.StoryRepository;
import uk.co.bluetangerine.social.service.domain.story.StoryRepositorySingleton;

/**
 * Created by tony on 12/11/2016.
 */
public class SetRankUseCase implements Interactor<SetRankUseCaseRequest, SetRankUseCaseResponse> {
    StoryRepository storyRepository = StoryRepositorySingleton.getInstance();
    public SetRankUseCaseResponse execute(SetRankUseCaseRequest var1) {
        Story updatedStory = storyRepository.updateStory(new Story(var1.getStoryId(), var1.getRank()));
        //TODO: What id
        return new SetRankUseCaseResponse(updatedStory.getRank());
    }
}
