package uk.co.bluetangerine.social.service.usecase;

import uk.co.bluetangerine.social.service.domain.story.Story;
import uk.co.bluetangerine.social.service.domain.story.StoryRepository;
import uk.co.bluetangerine.social.service.domain.story.StoryRepositorySingleton;

/**
 * Created by tony on 12/11/2016.
 */
public class SetRankUseCase implements Interactor<SetRankUseCaseRequest, SetRankUseCaseResponse> {
    private StoryRepository storyRepository = StoryRepositorySingleton.getInstance();
    public SetRankUseCaseResponse execute(SetRankUseCaseRequest var1) {
        SetRankUseCaseResponse response = null;
        Story updatedStory = storyRepository.updateStory(new Story(var1.getStoryId(), var1.getRank()));
        //TODO: What id
        if (null != updatedStory) {
            response = new SetRankUseCaseResponse(updatedStory.getStoryId(), updatedStory.getRank());
        }
        return response;
    }
}
