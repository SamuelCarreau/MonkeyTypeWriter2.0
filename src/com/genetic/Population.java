package com.genetic;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;


public class Population {

    private TreeSet<Genome> genomeTreeSet;
    private int size;
    private String answer;

    public int getSize() {
        return size;
    }

    private Population() {
        this.genomeTreeSet = new TreeSet<>(Comparator.reverseOrder());
        this.size = 0;
    }

    public static Population createRandomPop(String answer, int nbOfIndividual){
        Population population = new Population();
        population.answer = answer;
        while (population.size < nbOfIndividual)
            population.add(Genome.createRandomGenome(answer));
        return population;
    }

    public static Population createNewGeneration(String answer,Population matingPoolPopulation,double mutationRate){
        Population newGeneration = new Population();
        newGeneration.answer = answer;

        while (matingPoolPopulation.parentIsAvailable()){
            Genome[] babies = Genome.createGenomeBabies(answer,matingPoolPopulation.pollOneGenome(),matingPoolPopulation.pollOneGenome());
            for(Genome baby : babies)
                newGeneration.add(baby);
        }

        //CHECK FOR MUTATION
        if(mutationRate > 0){
            Iterator<Genome> genomeIterator = newGeneration.iterator();
            while (genomeIterator.hasNext())
                genomeIterator.next().mutate(mutationRate,answer);
        }

        return  newGeneration;
    }

    private boolean parentIsAvailable() {
        return this.size > 1;
    }

    public Population createMatingPool(int size){
        assert(this.size >= size);
        Population matingPool = new Population();
        while (matingPool.size != size){
            Genome candidate = this.pollOneGenome();
            matingPool.add(candidate);
            if(this.size <= 0){
                System.out.println("no more to add : "+(size-matingPool.size)+" more missing!");
            }
        }

        for (Genome candidate : matingPool.genomeTreeSet)
            this.add(candidate);

        return matingPool;
    }



    public Iterator<Genome> iterator(){
        return this.genomeTreeSet.iterator();
    }



    public double score(){

        double popScore = 0.0;

        if(size > 0){
            popScore = ((this.sum() / size)/this.answer.length())*100;
        }

        return popScore;
    }

    private Genome selectOneGenome(){
        int selector = RandomHelper.randomRange(0,(int)this.sum());
        for(Genome candidate : this.genomeTreeSet){
            selector -= candidate.getScore();
            if(selector <= 0){
                return candidate;
            }
        }
        return null;
    }

    private Genome pollOneGenome(){
        int sum =(int)this.sum();
        int selector = RandomHelper.randomRange(0,sum);
        Genome candidate = this.genomeTreeSet.first();
        Iterator<Genome> genomeIterator = this.iterator();
        while (selector > 0){
            candidate= genomeIterator.next();
            selector -= candidate.getScore();
        }
        this.remove(candidate);
        return candidate;
    }

    public void add(Genome genome){
        this.genomeTreeSet.add(genome);
        this.size = genomeTreeSet.size();
    }

    public void remove(Genome genome){
        this.genomeTreeSet.remove(genome);
        this.size = genomeTreeSet.size();
    }

    private Genome pollFirstGenome(){
        this.size--;
        return this.genomeTreeSet.pollFirst();
    }

    private double sum(){
        double sumOfScore = 0.0;
        for(Genome genome : this.genomeTreeSet){
            sumOfScore += genome.getScore();
        }
        return sumOfScore;
    }

    @Override
    public String toString() {
        Genome firstGenome = this.iterator().next();
        double firstScore = firstGenome.getScore();
        double firstScorePercentage = Math.round(firstGenome.getScoreInPercentage()*10000)/(double)10000;
        double averageScore = Math.round(this.score()*10000)/(double)10000;
        return ""+firstGenome.toString()+" score : "+firstScore+" // "+firstScorePercentage+"% generation average : "+averageScore+"%";
    }
}
