/**
 * Created by tony on 12/11/2016.
 */
public interface SocialRankService {
    int getStoryRank(int id);
    int setStoryRank(int id, int rank);
    int incrementStoryRank(int id);
    int decrementStoryRank(int id);
}
