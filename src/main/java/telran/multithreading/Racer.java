package telran.multithreading;

public class Racer extends Thread {
    private final Race race;
    private final int number;

    public Racer(Race race, int number) {
        this.race = race;
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 1; i <= race.getDistance(); i++) {
            System.out.printf("Racer #%d completed iteration %d%n", number, i);

            try {
                Thread.sleep(race.getRandomSleepTime());
            } catch (InterruptedException e) {
            }
        }
        race.setWinner(number);
    }
}
