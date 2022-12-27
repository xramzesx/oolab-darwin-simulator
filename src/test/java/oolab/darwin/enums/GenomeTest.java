package oolab.darwin.enums;

import oolab.darwin.Config;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GenomeTest {
    @Test
    void nextGenome() {
        assertEquals( Genome.R1, Genome.N0.next() );
        assertEquals( Genome.R2, Genome.R1.next() );
        assertEquals( Genome.R3, Genome.R2.next() );
        assertEquals( Genome.B0, Genome.R3.next() );
        assertEquals( Genome.L3, Genome.B0.next() );
        assertEquals( Genome.L2, Genome.L3.next() );
        assertEquals( Genome.L1, Genome.L2.next() );
        assertEquals( Genome.N0, Genome.L1.next() );
    }

    @Test
    void prevGenome() {
        assertEquals( Genome.N0, Genome.R1.prev() );
        assertEquals( Genome.R1, Genome.R2.prev() );
        assertEquals( Genome.R2, Genome.R3.prev() );
        assertEquals( Genome.R3, Genome.B0.prev() );
        assertEquals( Genome.B0, Genome.L3.prev() );
        assertEquals( Genome.L3, Genome.L2.prev() );
        assertEquals( Genome.L2, Genome.L1.prev() );
        assertEquals( Genome.L1, Genome.N0.prev() );
    }

    @Test
    void randomize() {
        assertNotEquals( Genome.N0, Genome.N0.randomize() );
        assertNotEquals( Genome.R1, Genome.R1.randomize() );
        assertNotEquals( Genome.R2, Genome.R2.randomize() );
        assertNotEquals( Genome.R3, Genome.R3.randomize() );
        assertNotEquals( Genome.B0, Genome.B0.randomize() );
        assertNotEquals( Genome.L3, Genome.L3.randomize() );
        assertNotEquals( Genome.L2, Genome.L2.randomize() );
        assertNotEquals( Genome.L1, Genome.L1.randomize() );
    }


    @Test
    void randomizedMutate() {
        Config config = new Config();
        config.mutationVariant = MutationVariant.RANDOMIZED;

        assertNotEquals( Genome.N0, Genome.N0.mutate(config) );
        assertNotEquals( Genome.R1, Genome.R1.mutate(config) );
        assertNotEquals( Genome.R2, Genome.R2.mutate(config) );
        assertNotEquals( Genome.R3, Genome.R3.mutate(config) );
        assertNotEquals( Genome.B0, Genome.B0.mutate(config) );
        assertNotEquals( Genome.L3, Genome.L3.mutate(config) );
        assertNotEquals( Genome.L2, Genome.L2.mutate(config) );
        assertNotEquals( Genome.L1, Genome.L1.mutate(config) );
    }

    boolean checkCorrectedMutation ( Genome genome, Config config ){
        Genome mutated = genome.mutate(config);
        return genome.prev() == mutated || genome.next() == mutated;
    }

    @Test
    void correctedMutate() {
        Config config = new Config();
        config.mutationVariant = MutationVariant.CORRECTED;

        assert( checkCorrectedMutation(Genome.N0, config) );
        assert( checkCorrectedMutation(Genome.R1, config) );
        assert( checkCorrectedMutation(Genome.R2, config) );
        assert( checkCorrectedMutation(Genome.R3, config) );
        assert( checkCorrectedMutation(Genome.B0, config) );
        assert( checkCorrectedMutation(Genome.L3, config) );
        assert( checkCorrectedMutation(Genome.L2, config) );
        assert( checkCorrectedMutation(Genome.L1, config) );
    }

}
