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
}
