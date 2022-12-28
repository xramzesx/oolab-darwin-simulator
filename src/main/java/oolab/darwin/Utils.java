package oolab.darwin;

import java.util.Random;
import java.util.Set;

public class Utils {
    public static int getRandomInt( int start, int end ) {
        Random random = new Random();
        return random.nextInt(
            ( ( end - start ) + 1 ) + start
        );
    }

    public static Vector2d getRandomVector2d(
        Vector2d startBorder,
        Vector2d endBorder
    ) {
        return new Vector2d(
            getRandomInt(startBorder.x, endBorder.x),
            getRandomInt(startBorder.y, endBorder.y)
        );
    }

    public static boolean drawResult( int positiveChances ) {
        return getRandomInt(1, 100) <= positiveChances;
    }

    public static int getAnotherIndex( int current, int length) {
        int index = getRandomInt(0, length - 2);
        return index >= current
                ? (index + 1) % length
                : index;
    }

    public static <T> T getRandomElement(Set<? extends T> set ) {
        Random random = new Random();

        int chosenIndex = getRandomInt(0, set.size());
        int currentIndex = 0;

        T element = null;

        for (T e : set) {
            element = e;

            if (currentIndex == chosenIndex)
                return element;

            currentIndex++;
        }

        return element;
    }

}
