package com.genetictest;

import org.junit.Test;
import java.util.Iterator;
import static org.junit.Assert.*;
import com.genetic.*;

public class PopulationTest {

    @Test
    public void testingMatingPoolSelection(){
        int randomPopSize = 200;
        int matingPoolSize = 100;
        Population randomPop = Population.createRandomPop("unicorn",randomPopSize);
        Population matingPool = randomPop.createMatingPool(matingPoolSize);

        assertEquals(100,matingPool.getSize());
    }

    @Test
    public void testingNewGeneration(){
        int randomPopSize = 200;
        int matingPoolSize = 100;
        int newGenerationExpectedSize = 100;
        String answer = "unicorn";

        Population randomPop = Population.createRandomPop(answer,randomPopSize);
        Population matingPool = randomPop.createMatingPool(matingPoolSize);
        Population newGeneration = Population.createNewGeneration(answer,matingPool,0);


        double scoreOldpop = randomPop.score();
        double scoreNewpop = newGeneration.score();

        assertEquals(newGenerationExpectedSize,newGeneration.getSize());


        Iterator<Genome> genomeIterator = newGeneration.iterator();
        for(int i = 0 ; i < newGenerationExpectedSize ; i++){
            Genome currentGenome = genomeIterator.next();
            assertEquals(answer.length(),currentGenome.length());
        }
    }
}