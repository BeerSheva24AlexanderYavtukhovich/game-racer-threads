package telran.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class Race {
    private final int distance;
    private final int minSleep;
    private final int maxSleep;
    AtomicInteger winner = new AtomicInteger(-1);
    private long startTime;

    public Race(int distance, int minSleep, int maxSleep) {
        this.distance = distance;
        this.minSleep = minSleep;
        this.maxSleep = maxSleep;
    }

    public int getWinner() {
        return winner.get();
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

    public synchronized void startRace() {
        this.startTime = System.currentTimeMillis();
    }

}