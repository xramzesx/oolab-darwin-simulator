package oolab.darwin;

import java.util.Random;

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

}
