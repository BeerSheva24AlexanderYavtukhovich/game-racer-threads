package telran.multithreading;

import java.util.ArrayList;
import java.util.List;

public class Race {
    private final int distance;
    private final int minSleep;
    private final int maxSleep;
    private final List<Racer> photoFinishList = new ArrayList<>();
    private long startTime;

    public Race(int distance, int minSleep, int maxSleep) {
        this.distance = distance;
        this.minSleep = minSleep;
        this.maxSleep = maxSleep;
    }

    public synchronized void startRace() {
        this.startTime = System.currentTimeMillis();
    }

    public synchronized void addToPhotoFinishList(Racer racer) {
        photoFinishList.add(racer);
    }

    public List<Racer> getPhotoFinishList() {
        return photoFinishList;
    }

    public int getDistance() {
        return distance;
    }

    public int getMinSleep() {
        return minSleep;
    }

    public int getMaxSleep() {
        return maxSleep;
    }

    public long getStartTime() {
        return startTime;
    }

}