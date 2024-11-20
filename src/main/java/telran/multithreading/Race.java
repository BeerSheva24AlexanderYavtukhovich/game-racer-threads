package telran.multithreading;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Race {
    private final int distance;
    private final int minSleepTime;
    private final int maxSleepTime;
    private final AtomicInteger winner = new AtomicInteger(0);

    public Race(int distance, int minSleepTime, int maxSleepTime) {
        this.distance = distance;
        this.minSleepTime = minSleepTime;
        this.maxSleepTime = maxSleepTime;
    }

    public int getDistance() {
        return distance;
    }

    public int getRandomSleepTime() {
        return new Random().nextInt(maxSleepTime - minSleepTime + 1) + minSleepTime;
    }

    public boolean setWinner(int racerNumber) {
        return winner.compareAndSet(0, racerNumber);
    }

    public int getWinner() {
        return winner.get();
    }
}
