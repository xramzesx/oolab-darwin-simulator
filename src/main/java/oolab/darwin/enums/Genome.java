package oolab.darwin.enums;

import oolab.darwin.Config;
import oolab.darwin.Utils;
import oolab.darwin.elements.Animal;

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


    public static ArrayList<Genome> evolve(Config config, Animal mother, Animal father) {
        ArrayList<Genome> genomes = new ArrayList<>();

        //// TYPE STRONGER ////

        Animal stronger, weaker;

        if (mother.energy > father.energy ){
            stronger = mother;
            weaker = father;
        } else {
            stronger = father;
            weaker = mother;
        }

        //// COMBINE PARENT GENOMES ////

        int midPoint = (config.genomeLength * stronger.energy )/ (stronger.energy + weaker.energy);

        boolean strongerStarts = Utils.drawResult(50);

        for (int i = 0; i < midPoint; i++ ) {
            if (strongerStarts) {
                genomes.add(stronger.getGenomes().get(i));
            } else {
                genomes.add(weaker.getGenomes().get(i));
            }
        }

        for (int i = midPoint; i < config.genomeLength; i++ ) {
            if (strongerStarts) {
                genomes.add(weaker.getGenomes().get(i));
            } else {
                genomes.add(stronger.getGenomes().get(i));
            }
        }

        //// MUTATE GENOMES ////

        int mutationQuantity = Utils.getRandomInt( config.minMutationQuantity, config.maxMutationQuantity );

        for (int i = 0; i < mutationQuantity; i++ ) {
            int index = Utils.getRandomInt(0, config.genomeLength - 1);

            genomes.set(
                    index,
                    genomes .get(index)
                            .randomize()
            );
        }


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
