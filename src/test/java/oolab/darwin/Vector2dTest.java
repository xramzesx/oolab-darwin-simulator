package oolab.darwin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class Vector2dTest {
    @Test
    void equals() {
        Vector2d v1 = new Vector2d(1, 100);

        // reference
        assert(v1.equals(v1));

        // type


        // values
        assert(v1.equals( new Vector2d(1, 100) ));
        assertFalse(v1.equals(new Vector2d(110, 100)));
        assertFalse(v1.equals(new Vector2d(1, 1000)));

    }

    @Test
    void toStringTest() {
        assertEquals(
                "(1,100)",
                (new Vector2d(1, 100).toString())
        );

        assertEquals(
                "(-10,10)",
                (new Vector2d(-10, 10)).toString()
        );

        assertEquals(
                "(0,10)",
                (new Vector2d(0, 10)).toString()
        );
    }

    @Test
    void precedes() {
        Vector2d v1 = new Vector2d(1,1);
        Vector2d v2 = new Vector2d(2,2);
        Vector2d v3 = new Vector2d(2,3);

        assert(v1.precedes(v2));
        assert(v2.precedes(v3));
        assert(v1.precedes(v3));

        assert(v1.precedes(v1));
        assert(v2.precedes(v2));
        assert(v3.precedes(v3));

        assertFalse(v3.precedes(v1));
        assertFalse(v3.precedes(v2));
    }

    @Test
    void follows() {
        Vector2d v1 = new Vector2d(1,1);
        Vector2d v2 = new Vector2d(2,2);
        Vector2d v3 = new Vector2d(2,3);

        assert(v3.follows(v2));
        assert(v3.follows(v1));
        assert(v2.follows(v1));

        assert(v1.follows(v1));
        assert(v2.follows(v2));
        assert(v3.follows(v3));

        assertFalse(v1.follows(v3));
        assertFalse(v1.follows(v2));
        assertFalse(v2.follows(v3));
    }

    @Test
    void upperRight() {
        Vector2d v1 = new Vector2d(2, 1);
        Vector2d v2 = new Vector2d(1, 2);

        Vector2d u1 = new Vector2d(100,0);
        Vector2d u2 = new Vector2d(0, 100);

        assertEquals( new Vector2d(2, 2), v1.upperRight(v2));
        assertEquals( new Vector2d(100, 100), u1.upperRight(u2));

        assertEquals(new Vector2d(100, 1), v1.upperRight(u1));
        assertEquals(new Vector2d(1, 100), u2.upperRight(v2));
    }

    @Test
    void lowerLeft() {
        Vector2d v1 = new Vector2d(2, 1);
        Vector2d v2 = new Vector2d(1, 2);

        Vector2d u1 = new Vector2d(100,0);
        Vector2d u2 = new Vector2d(0, 100);

        Vector2d v3 = new Vector2d(-10, 10);
        Vector2d v4 = new Vector2d(10, -10);

        assertEquals( new Vector2d(1, 1), v1.lowerLeft(v2));
        assertEquals( new Vector2d(0, 0), u1.lowerLeft(u2));

        assertEquals(new Vector2d(2, 0), v1.lowerLeft(u1));
        assertEquals(new Vector2d(0, 2), u2.lowerLeft(v2));

        assertEquals(new Vector2d(-10, 1), v1.lowerLeft(v3));
        assertEquals(new Vector2d(1, -10), v2.lowerLeft(v4));
    }

    @Test
    void add() {
        Vector2d v1 = new Vector2d(1, 1);
        Vector2d v2 = new Vector2d(10, 10);
        Vector2d v3 = new Vector2d(-1, 0);
        Vector2d v4 = new Vector2d(0, -1);


        assertEquals(v1.add(v1), new Vector2d(2, 2));
        assertEquals(v1.add(v2), new Vector2d(11, 11));
        assertEquals(v1.add(v3), new Vector2d(0, 1));

        assertEquals(
                v1.add( v3.add( v4 ) ),
                new Vector2d(0, 0)
        );

        assertEquals(
                v2.add( v3.add( v4 ) ),
                new Vector2d(9, 9)
        );
    }

    @Test
    void subtract() {
        Vector2d v1 = new Vector2d(1, 1);
        Vector2d v2 = new Vector2d(10, 10);
        Vector2d v3 = new Vector2d(-1, 0);
        Vector2d v4 = new Vector2d(0, -1);


        assertEquals( new Vector2d(0, 0), v1.subtract(v1) );
        assertEquals( new Vector2d(-9, -9), v1.subtract(v2) );
        assertEquals( new Vector2d(2, 1), v1.subtract(v3) );

        assertEquals(
                new Vector2d(2, 0),
                v1.subtract( v3.subtract( v4 ) )
        );

        assertEquals(
                new Vector2d(11, 9),
                v2.subtract( v3.subtract( v4 ) )
        );
    }

    @Test
    void opposite() {
        assertEquals(
                new Vector2d( -1, 1),
                new Vector2d(1, -1).opposite()
        );

        assertEquals(
                new Vector2d( 1, -1),
                new Vector2d(-1, 1).opposite()
        );

        assertEquals(
                new Vector2d(0, 0),
                new Vector2d(0, 0).opposite()
        );

        assertEquals(
                new Vector2d(-10, 0),
                new Vector2d(10, 0).opposite()
        );
    }
}
