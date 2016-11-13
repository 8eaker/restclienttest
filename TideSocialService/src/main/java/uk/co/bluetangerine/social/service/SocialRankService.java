package uk.co.bluetangerine.social.service;

/**
 * Created by tony on 12/11/2016.
 */
public interface SocialRankService {
    Integer getStoryRank(Integer id);
    Integer setStoryRank(Integer id, Integer rank);
    Integer incrementStoryRank(Integer id);
    Integer decrementStoryRank(Integer id);
}
