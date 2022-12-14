package oolab.darwin.enums;

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
}