package com.genetic;

import java.util.Iterator;

public class Environnement {
    private  String answer;
    private  int populationSize;
    private double newPopRatio;
    private double mutationRate;

    /*
     * 1. Create a random pop of N elements "Unicorn"
     *  1.1 Variation
     *      unijorm - 5
     *      pancake - 1
     *      aaaaaah - 0
     *      popcorn - 4
     *  1.2 Heredity
     *  1.3 Selection
     *
     *  2. DRAW
 *        2.1 Calculate fitness for N elements
     *    2.2 Reproduction / Selection
     *      2.2.1 pick 2 "parents" (Selection)
     *      2.2.2 make a new element
     *          a.Crossover -->(Make two elements totaly diferent but from the same parent)
     *          b.Mutation
     *      2.2.3
     *
      * */

    public Environnement(){}

    public Environnement(String answer,int populationSize,double newPopRation,double mutationRate){
        assert (newPopRation > 0 && newPopRation <= 1);
        assert (mutationRate > 0 && newPopRation <= 1);
        this.answer = answer;
        this.populationSize = populationSize;
        this.newPopRatio = newPopRation;
        this.mutationRate = mutationRate;

    }

    public Population createRandomPop(){
        return Population.createRandomPop(this.answer,this.populationSize);
    }

    public Population createNewGeneration(Population oldPop){

        Population matingPool = oldPop.createMatingPool((int)(this.populationSize*this.newPopRatio));
        Population newGeneration = Population.createNewGeneration(this.answer,matingPool,this.mutationRate);

        Iterator<Genome> oldPopIterator = oldPop.iterator();
        while (newGeneration.getSize() < this.populationSize){
            newGeneration.add(oldPopIterator.next());
        }

        return newGeneration;
    }








}
