package com.genetictest;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import com.genetic.*;


import static org.junit.Assert.*;

public class EnvironmentTest {

    @Test
    public void testingRandomNextDouble(){
        Random r = new Random();
        int lenght = (int)10e6;
        double[] darray = new double[lenght];
        for(int i = 0; i < lenght ; i++){
            darray[i] = r.nextDouble();
        }

        HashSet<Double> hsDouble = new HashSet<>();

        for(double d : darray){
            hsDouble.add(d);
            assertTrue(d >= 0 && d <= 1);
        }

        assertTrue(hsDouble.size() == lenght);

    }

    @Test
    public void testingACoinFlip(){
        Random random = new Random();
        int nbOfCoinFlip = (int)10e6;
        int[] coinFlips = new int[nbOfCoinFlip];

        LinkedList<Integer> listOfHead = new LinkedList<>();
        LinkedList<Integer> listOfTail = new LinkedList<>();

        for(int i = 0; i < nbOfCoinFlip ; i++){
            coinFlips[i] = RandomHelper.flipCoin();
            if ((coinFlips[i] == 0)) {
                listOfHead.add(coinFlips[i]);
            } else {
                listOfTail.add(coinFlips[i]);
            }
        }

        for(int i : coinFlips){
            assertTrue(i == 0 || i == 1);
        }
    }

    @Test
    public void testCreationNouvellePop() {
        //test dont le mot est unicorn

        int nbOfElement = (int) 200;
        String answer = "unicorn";
        double newPopRatio = 0.5;
        double mutationRation = 0.05;
        Environnement testingEnvironnement = new Environnement(answer,nbOfElement,0.5,0.05);
        Population randomPop = testingEnvironnement.createRandomPop();
        Iterator<Genome> genomeIterator = randomPop.iterator();
        assertEquals(nbOfElement,randomPop.getSize());
        for(int i = 0 ; i < nbOfElement ; i++){
            Genome currentGenome = genomeIterator.next();
            assertEquals(answer.length(),currentGenome.length());
        }

    }

    @Test
    public void testGenerationIteration(){

        String answer = "Tracer does well with Heroes who have strong waveclear, who can also deal area damage over time.";
        int nbOfElement = (int)1000;
        int maxScore = answer.length();
        double newPopRatio = .5;
        double mutationRation = 0.0075;

        Environnement testingEnvironnement = new Environnement(answer,nbOfElement,newPopRatio,mutationRation);
        Population randomPop = testingEnvironnement.createRandomPop();
        Population oldPop = randomPop;

        Population newPop = testingEnvironnement.createNewGeneration(oldPop);
        int generationNumber = 0;
        do{
            System.out.println(generationNumber +" : "+ newPop.toString());

            oldPop = newPop;
            newPop = testingEnvironnement.createNewGeneration(oldPop);
            generationNumber++;
        }
        while (newPop.iterator().next().getScore() < maxScore && generationNumber < 100000) ;



        System.out.println(generationNumber +" : "+newPop.toString());
        if(generationNumber < 100000)
            assertEquals(answer,newPop.iterator().next().toString());
        else {
            assertEquals(100000,generationNumber);
        }


    }
}