package world.ucode.utilits;

import java.security.SecureRandom;
import java.util.ArrayList;

public class RandomValue {
    private static ArrayList<Integer> itemNumbers = new ArrayList<>();

    public int makeItemNumber() {
        int y = 0;
        SecureRandom rand = new SecureRandom();
        y = rand.nextInt(99999999 - 10000000);
        while (itemNumbers.contains(y) == true) {
            rand = new SecureRandom();
            y = rand.nextInt(99999999 - 10000000);
        }
        itemNumbers.add(y);
        return y;
    }
}
