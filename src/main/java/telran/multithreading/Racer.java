package telran.multithreading;

import java.util.Random;

public class Racer extends Thread {
    private final Race race;
    private final int number;
    private long finishTime;

    public Racer(Race race, int number) {
        this.race = race;
        this.number = number;
    }

    @Override
    public void run() {
        int minSleep = race.getMinSleep();
        int maxSleep = race.getMaxSleep();
        int distance = race.getDistance();
        Random random = new Random();
        for (int i = 0; i < distance; i++) {
            try {
                sleep(random.nextInt(minSleep, maxSleep + 1));
                System.out.printf("%d - step %d\n", number, i);
            } catch (InterruptedException e) {
            }
        }

        synchronized (race) {
            race.addToPhotoFinishList(this);
            setFinishTime(System.currentTimeMillis());
        }
    }

    public long getFinishTime() {
        return finishTime;
    }

    public int getNumber() {
        return number;
    }

    private void setFinishTime(long time) {
        this.finishTime = time;
    }
}