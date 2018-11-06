package com.genetic;

import java.util.Random;

public class RandomHelper {

    private static final Random RANDOM = new Random();
    private static final String POSSIBLE_CHAR = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm '-,.!?";

    private RandomHelper() {
        //class static
    }

    public static double nextDouble(){
        return RANDOM.nextDouble();
    }

    public static int randomRange(int min, int max)
    {
        int range = (max - min) + 1;
        return RANDOM.nextInt((range));
    }

    public static int flipCoin(){
        return randomRange(0,1);
    }

    public static int rankSpaceEquation(double rankSpace){
        /**
         * Pc= rankSpace ex : 0.5
         *
         * p1 = pc
         * p2 =(1-pc)*pc
         * p3 = (1-pc)^2 * pc
         * ...
         * Pn-1(1-pc)n-1 * pc
         */

        double random = RANDOM.nextDouble();
        double result = 1;
        int i = 0;

        do{
            if(i == 0){
                result = rankSpace;
            }
            else{
                result += (Math.pow((1-rankSpace),i))*rankSpace;
            }

            i++;
        }while (random > result);

        return i-1;
    }

    public static char getRandomChar(){
        int index = randomRange(0,POSSIBLE_CHAR.length()-1);
        char result = POSSIBLE_CHAR.charAt(index);
        return result;
    }

    public static String getRandomString(int StringLength){
        char[] result = new char[StringLength];

        for(int i = 0 ; i < StringLength ; i++)
            result[i] = RandomHelper.getRandomChar();

        return new String(result);
    }
}
