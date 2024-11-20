package telran.multithreading;

import java.util.Arrays;
import java.util.stream.IntStream;

import telran.view.InputOutput;
import telran.view.StandardInputOutput;

public class Main {
    private static final int MIN_SLEEP_TIME = 50;
    private static final int MAX_SLEEP_TIME = 100;

    public static void main(String[] args) {
        InputOutput io = new StandardInputOutput();
        int nRacers = io.readInt("Enter the number of racers:", "Incorrect input");
        int distance = io.readInt("Enter the distance:", "Incorrect input");

        Race race = new Race(distance, MIN_SLEEP_TIME, MAX_SLEEP_TIME);

        Racer[] racers = IntStream.rangeClosed(1, nRacers)
                .mapToObj(i -> new Racer(race, i))
                .toArray(Racer[]::new);

        System.out.println("Race started!");
        Arrays.stream(racers).forEach(Thread::start);

        Arrays.stream(racers).forEach(racer -> {
            try {
                racer.join();
            } catch (InterruptedException e) {
            }
        });

        System.out.printf("Congratulations to Racer #%d - the winner!%n", race.getWinner());
    }
}
