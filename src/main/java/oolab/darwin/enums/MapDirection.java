package oolab.darwin.enums;

import oolab.darwin.Utils;
import oolab.darwin.Vector2d;

public enum MapDirection {
    North,
    NorthEast,
    East,
    SouthEast,
    South,
    SouthWest,
    West,
    NorthWest;

    private static MapDirection[] vals = values();

    public MapDirection next() {
        return vals[ ( this.ordinal() + 1 ) % vals.length ];
    }

    public MapDirection previous() {
        return vals[ ( this.ordinal() - 1 + vals.length ) % vals.length ];
    }

    public MapDirection rotate( Genome genome ) {
        return vals[ (this.ordinal() + genome.ordinal() ) % vals.length ];
    }

    public static MapDirection random() {
        return vals [ Utils.getRandomInt( 0, vals.length - 1 ) ];
    }

    public Vector2d toUnitVector() {
        return switch (this){
            case North      -> new Vector2d(  0,  1 );
            case NorthEast  -> new Vector2d(  1,  1 );
            case East       -> new Vector2d(  1,  0 );
            case SouthEast  -> new Vector2d(  1, -1 );
            case South      -> new Vector2d(  0, -1 );
            case SouthWest  -> new Vector2d( -1, -1 );
            case West       -> new Vector2d( -1,  0 );
            case NorthWest  -> new Vector2d( -1,  1 );
        };
    }

    @Override
    public String toString() {
        return switch (this){
            case North      -> "N";
            case NorthEast  -> "NE";
            case East       -> "E";
            case SouthEast  -> "SE";
            case South      -> "S";
            case SouthWest  -> "SW";
            case West       -> "W";
            case NorthWest  -> "NW";
        };
    }
}
