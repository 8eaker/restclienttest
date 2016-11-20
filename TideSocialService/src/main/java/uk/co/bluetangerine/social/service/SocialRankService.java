package uk.co.bluetangerine.social.service;

import uk.co.bluetangerine.social.service.dto.StoryRankDto;

/**
 * Created by tony on 12/11/2016.
 *
 */
public interface SocialRankService {
    StoryRankDto getStoryRank(Integer id);
    StoryRankDto setStoryRank(Integer id, Integer rank);
    StoryRankDto incrementStoryRank(Integer id);
    StoryRankDto decrementStoryRank(Integer id);
}
