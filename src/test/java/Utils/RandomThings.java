package Utils;

import java.util.Random;

public class RandomThings {
    public static String generateRandomString(int targetStringLength) {
        int leftLimit = 'a'; // 97
        int rightLimit = 'z'; // 122
        Random random = new Random();

        StringBuilder builder = new StringBuilder("test");
        for (int i = 0; i < targetStringLength; i++) {
            int randomChar = leftLimit + random.nextInt(rightLimit - leftLimit + 1);
            builder.append((char) randomChar);
        }

        return builder.toString();
    }

}
