package oolab.darwin.enums;

import oolab.darwin.Vector2d;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MapDirectionTest {
    @Test
    void nextDirection() {
        assertEquals(MapDirection.North.next(), MapDirection.NorthEast);
        assertEquals(MapDirection.NorthEast.next(), MapDirection.East);
        assertEquals(MapDirection.East.next(), MapDirection.SouthEast);
        assertEquals(MapDirection.SouthEast.next(), MapDirection.South);
        assertEquals(MapDirection.South.next(), MapDirection.SouthWest);
        assertEquals(MapDirection.SouthWest.next(), MapDirection.West);
        assertEquals(MapDirection.West.next(), MapDirection.NorthWest);
        assertEquals(MapDirection.NorthWest.next(), MapDirection.North);
    }


    @Test
    void prevDirection() {
        assertEquals(MapDirection.North, MapDirection.NorthEast.prev());
        assertEquals(MapDirection.NorthEast, MapDirection.East.prev());
        assertEquals(MapDirection.East, MapDirection.SouthEast.prev());
        assertEquals(MapDirection.SouthEast, MapDirection.South.prev());
        assertEquals(MapDirection.South, MapDirection.SouthWest.prev());
        assertEquals(MapDirection.SouthWest, MapDirection.West.prev());
        assertEquals(MapDirection.West, MapDirection.NorthWest.prev());
        assertEquals(MapDirection.NorthWest, MapDirection.North.prev());
    }

    @Test
    void flipDirection() {
        assertEquals(MapDirection.North.flipHorizontal(),       MapDirection.South);
        assertEquals(MapDirection.NorthEast.flipHorizontal(),   MapDirection.SouthEast);
        assertEquals(MapDirection.East.flipHorizontal(),        MapDirection.East);
        assertEquals(MapDirection.SouthEast.flipHorizontal(),   MapDirection.NorthEast);
        assertEquals(MapDirection.South.flipHorizontal(),       MapDirection.North);
        assertEquals(MapDirection.SouthWest.flipHorizontal(),   MapDirection.NorthWest);
        assertEquals(MapDirection.West.flipHorizontal(),        MapDirection.West);
        assertEquals(MapDirection.NorthWest.flipHorizontal(),   MapDirection.SouthWest);
    }

    @Test
    void toUnitVector() {
        assertEquals(MapDirection.North.toUnitVector(),     new Vector2d(  0,  1 ));
        assertEquals(MapDirection.NorthEast.toUnitVector(), new Vector2d(  1,  1 ));
        assertEquals(MapDirection.East.toUnitVector(),      new Vector2d(  1,  0 ));
        assertEquals(MapDirection.SouthEast.toUnitVector(), new Vector2d(  1, -1 ));
        assertEquals(MapDirection.South.toUnitVector(),     new Vector2d(  0, -1 ));
        assertEquals(MapDirection.SouthWest.toUnitVector(), new Vector2d( -1, -1 ));
        assertEquals(MapDirection.West.toUnitVector(),      new Vector2d( -1,  0 ));
        assertEquals(MapDirection.NorthWest.toUnitVector(), new Vector2d( -1,  1 ));
    }

    @Test
    void rotate() {
        for (MapDirection direction : MapDirection.values()) {
            for ( int i = 0; i < Genome.values().length; i++ ) {
                MapDirection currentDirection = direction;

                for ( int j = 0; j < i; j++)
                    currentDirection = currentDirection.next();

                assertEquals( currentDirection, direction.rotate(Genome.values()[i]) );
            }
        }
    }
}
