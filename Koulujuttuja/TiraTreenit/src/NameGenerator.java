import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class NameGenerator {
    private static ArrayList<String> firstNames = new ArrayList<String>();
    private static ArrayList<String> lastNames = new ArrayList<String>();
    private static ArrayList<String> randomNames = new ArrayList<String>();
    static ArrayList<String> createdNames = new ArrayList<String>();

    public NameGenerator() {
        // firstNames ArrayList
        firstNames.add("Mic");
        firstNames.add("Beat");
        firstNames.add("Graffiti");
        firstNames.add("Rapper");
        firstNames.add("DJ");
        firstNames.add("Lyric");
        firstNames.add("Groove");
        firstNames.add("Street");
        firstNames.add("Funky");
        firstNames.add("Boom-Bap");

        //lastNames ArrayList
        lastNames.add("Shadow");
        lastNames.add("Samurai");
        lastNames.add("Ninja");
        lastNames.add("Phantom");
        lastNames.add("Monk");
        lastNames.add("Soul");
        lastNames.add("Spirit");
        lastNames.add("Warlock");
        lastNames.add("Monster");
        lastNames.add("Buddha");

        // randomNames ArrayList
        randomNames.add("Ella Johnson");
        randomNames.add("Lucas Smith");
        randomNames.add("Sophia Brown");
        randomNames.add("Oliver Davis");
        randomNames.add("Mia Wilson");
        randomNames.add("Liam Taylor");
        randomNames.add("Ava Miller");
        randomNames.add("Noah Anderson");
        randomNames.add("Isabella Martinez");
        randomNames.add("William Jones");
    }

    void nameGenerator() {
        for (int i = 0; i < randomNames.size(); i++) {
            int firstIndex = ThreadLocalRandom.current().nextInt(firstNames.size());
            int secondIndex = ThreadLocalRandom.current().nextInt(lastNames.size());
            String randomName = firstNames.get(firstIndex) + " " + lastNames.get(secondIndex);
            if (createdNames.contains(randomName)) {
                i--;
                continue;
            } else {
                createdNames.add(randomName);
            }
        }
    }

    public static void main(String[] args) {
        NameGenerator nameGenerator = new NameGenerator();
        nameGenerator.nameGenerator();
        
        System.out.println("Wu-Tang name generator for programmers");
        System.out.println("--------------------------------------");
        System.out.println("The generator can produce " + firstNames.size()*lastNames.size()+" unique names");
        // Print the generated names
       for(int i = 0; i < randomNames.size(); i++){
        System.out.format("%3d. %-10s a.k.a. %s%n", i + 1, randomNames.get(i),createdNames.get(i));

       }
    }
}
