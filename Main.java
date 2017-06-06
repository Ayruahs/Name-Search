package com.shaurya;

import me.xdrop.fuzzywuzzy.*;
import java.util.Scanner;
import java.io.*;

public class Main{
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("names.txt");

        try{
            Scanner in = new Scanner(System.in);
            Scanner sc = new Scanner(file);
            int matchRatio;
            final int CONST_MATCH_RATIO = 80;
            final int SOUNDEX_SIM = 4;
            String exitCharacter = "q";
            String name;
            String[][] secondPassList;
            String[] finalList;
            String header;
            Quicksort quickSort = new Quicksort();

            System.out.print("Enter name of passenger to check or q to exit: ");
            String inputName = in.nextLine();

            while (!inputName.equals(exitCharacter)){
                // Get i for making 2D array
                int i = 0;

                header = sc.nextLine();
                while (sc.hasNext()) {
                    name = sc.nextLine();

                    i++;
                }
                sc.close();
                secondPassList = new String[i][2];
                sc = new Scanner(file);

                // Populate Array
                i = 0;
                header = sc.nextLine();
                while (sc.hasNext()){
                    name = sc.nextLine();

                    matchRatio = matchRatio(inputName, name);

                    secondPassList[i][0] = name;
                    secondPassList[i][1] = Integer.toString(matchRatio);
                    i++;

                }

                quickSort.sort(secondPassList);

                System.out.println("First Pass Results: ");
                for(i=secondPassList.length-1; i>0; i--){
                    if (Integer.parseInt(secondPassList[i][1]) >= CONST_MATCH_RATIO){
                        String output = String.format("The name '%10s' matches with %10s with" +
                                " a ratio of %s%%", inputName,
                                secondPassList[i][0], secondPassList[i][1]);
                        System.out.println(output);
                    }
                }
                /*
                System.out.println();
                System.out.println("Second Pass Results: ");
                for(i=secondPassList.length-1; i>0; i--){
                    if (Integer.parseInt(secondPassList[i][1]) >= CONST_MATCH_RATIO){
                        if(Soundex.compareSoundex(inputName, secondPassList[i][0])
                                >= SOUNDEX_SIM){
                            String output = String.format("The name '%10s' matches with %10s", inputName,
                                    secondPassList[i][0]);
                            System.out.println(output);
                        }
                    }

                }
                */

                //Ask for more names or exit
                System.out.print("\nEnter name of passenger to check or q to exit: ");
                inputName = in.nextLine();
                sc = new Scanner(file);
            }
            in.close();
            sc.close();

        }
        catch (FileNotFoundException ex){
            System.out.println("List of names not found.");
        }

    }

    private static int matchRatio(String string1, String string2){
        final int SMALL_DIFF = 4;
        final int MED_DIFF = 7;
        int matchRatio;

        if(Math.abs(string1.length() - string2.length()) < SMALL_DIFF){
            matchRatio = FuzzySearch.weightedRatio(string1.toLowerCase(), string2.toLowerCase());
        }else if(Math.abs(string1.length() - string2.length()) < MED_DIFF){
            matchRatio = FuzzySearch.ratio(string1.toLowerCase(), string2.toLowerCase());
        }else{
            matchRatio = FuzzySearch.partialRatio(string1.toLowerCase(), string2.toLowerCase());
        }
        return matchRatio;
    }


}