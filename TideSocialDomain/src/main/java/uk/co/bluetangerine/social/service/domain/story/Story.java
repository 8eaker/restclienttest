package uk.co.bluetangerine.social.service.domain.story;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by tony on 13/11/2016.
 * Implementation of story object
 * using AtomicInteger to prevent
 * issues with updates or reads as
 * ++ is two stage increment on regular
 * integers
 */
public class Story {
    private int storyId;
    private AtomicInteger rank = new AtomicInteger();

    public Story(int id, int rank) {
        this.storyId = id;
        this.rank.set(rank);
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }

    public int incrementRank() {
        return this.rank.incrementAndGet();
    }

    public int decrementRank() {
        return this.rank.decrementAndGet();
    }

    public int getRank() {
        return rank.get();
    }

    public int setRank(int rank) {
        this.rank.set(rank);
        return this.rank.get();
    }


}
