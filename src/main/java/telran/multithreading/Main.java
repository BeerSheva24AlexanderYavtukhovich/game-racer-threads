package telran.multithreading;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import telran.view.InputOutput;
import telran.view.StandardInputOutput;

public class Main {
    private static final int MIN_SLEEP_TIME = 50;
    private static final int MAX_SLEEP_TIME = 100;
    private static final String HEADER_FORMAT = "| %-6s | %-12s | %-10s |%n";
    private static final String ROW_FORMAT = "| %-6d | %-12d | %-10d |%n";
    private static final String LINE = "+--------+--------------+------------+";

    public static void main(String[] args) {
        InputOutput io = new StandardInputOutput();
        int nRacers = io.readInt("Enter the number of racers:", "Incorrect input");
        int distance = io.readInt("Enter the distance:", "Incorrect input");
        Race race = new Race(distance, MIN_SLEEP_TIME, MAX_SLEEP_TIME);
        Racer[] racers = IntStream.rangeClosed(1, nRacers)
                .mapToObj(i -> new Racer(race, i))
                .toArray(Racer[]::new);

        start(race, racers);
        join(racers);
        finish(race);
    }

    private static void finish(Race race) {
        System.out.println("\nRace finished! Results:");
        System.out.println(LINE);
        System.out.printf(HEADER_FORMAT, "Place", "Racer Number", "Time (ms)");
        System.out.println(LINE);
        AtomicInteger placeCounter = new AtomicInteger(1);

        race.getPhotoFinishList().forEach(racer -> {
            long runningTime = racer.getFinishTime() - race.getStartTime();
            System.out.printf(ROW_FORMAT,
                    placeCounter.getAndIncrement(),
                    racer.getNumber(),
                    runningTime);
        });

        System.out.println(LINE);
    }

    private static void start(Race race, Racer[] racers) {
        race.startRace();
        System.out.println("Race started!");
        Arrays.stream(racers).forEach(Thread::start);
    }

    private static void join(Racer[] racers) {
        Arrays.stream(racers).forEach(racer -> {
            try {
                racer.join();
            } catch (InterruptedException e) {
            }
        });
    }
}
