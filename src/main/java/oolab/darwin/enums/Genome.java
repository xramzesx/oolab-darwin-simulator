package oolab.darwin.enums;

import oolab.darwin.Config;
import oolab.darwin.Utils;

import java.util.ArrayList;

public enum Genome {

    // NEUTRAL/GO //
    N0,

    // TURN RIGHT //
    R1, R2, R3,

    // TURN BACK //
    B0,

    // TURN LEFT //
    L3, L2, L1;


    private static Genome[] vals = values();

    //// GENERATING ////

    public static Genome random() {
        return vals [ Utils.getRandomInt( 0, vals.length - 1 ) ];
    }

    public static ArrayList<Genome> generate(Config config) {
        ArrayList<Genome> genomes = new ArrayList<>();

        for (int i = 0; i < config.genomeLength; i++)
            genomes.add(Genome.random());

        return genomes;
    }

    public Genome mutate(Config config) {
        if ( config.mutationVariant == MutationVariant.RANDOMIZED ) {
            return this.randomize();
        } else {
            return Utils.getRandomInt(0,1) == 1
                ? this.prev()
                : this.next();
        }
    }

    public Genome next(){
        return vals[ ( this.ordinal() + 1 ) % vals.length ];
    }

    public Genome prev() {
        return vals[ ( this.ordinal() - 1 + vals.length ) % vals.length ];
    }

    public Genome randomize() {
        int genome = Utils.getRandomInt(0, vals.length - 2);

        return vals [
            genome >= this.ordinal()
                ? (genome + 1) % vals.length
                : genome
        ];
    }
}
