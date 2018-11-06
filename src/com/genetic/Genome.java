package com.genetic;

import java.util.Objects;

public class Genome implements Comparable<Genome>{

    private String data;
    private double score;

    private Genome(){}

    public static Genome createRandomGenome(String answer){
        Genome result = new Genome();
        result.data = RandomHelper.getRandomString(answer.length());
        result.score = result.calculateScore(answer);
        return result;
    }

    public static Genome createGenome(String answer,String data){
        Genome result = new Genome();
        result.data = data;
        result.score = result.calculateScore(answer);
        return result;
    }

    public static Genome[] createGenomeBabies(String answer,Genome mother, Genome father){

        char[] baby1 = mother.toString().toCharArray();
        char[] baby2 = father.toString().toCharArray();

        for(int i = 0 ; i < mother.length(); i++){
            if(RandomHelper.flipCoin() == 1){
                char temp = baby1[i];
                baby1[i] = baby2[i];
                baby2[i] = temp;
            }
        }

        Genome[] babies = new Genome[2];
        babies[0] = Genome.createGenome(answer,new String(baby1));
        babies[1] = Genome.createGenome(answer,new String(baby2));

        return babies;
    }


    public double getScore() {
        return new Double(score);
    }

    public double getScoreInPercentage(){
        return (this.getScore()/this.data.length())*100;
    }

    public int length(){
        return data.length();
    }

    private double calculateScore(String answer){
        int score = 0;
        for (int i = 0 ; i < answer.length() ; i++){
            if (answer.charAt(i)== this.data.charAt(i))
                score++;
        }

        return score;
    }


    public void mutate(double mutationRate,String answer){
        assert mutationRate<=1;
        char[] genomeMutation = this.data.toCharArray();
        for(int i = 0 ;i < genomeMutation.length ; i++){
            double random = RandomHelper.nextDouble();
            if(random < mutationRate){
                genomeMutation[i] = RandomHelper.getRandomChar();
            }
        }
        this.data = new String(genomeMutation);
        this.score = this.calculateScore(answer);
    }


    @Override
    public int compareTo(Genome genomeToCompare) {
        if(this.score == genomeToCompare.score)
            return this.data.compareTo(genomeToCompare.data);
        else if(this.score>genomeToCompare.score)
            return 1;
        else
            return -1;
    }

    @Override
    public String toString() {
        return this.data;
    }
}
