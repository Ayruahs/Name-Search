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
            final int CONST_MATCH_RATIO = 75;
            final int SOUNDEX_SIM = 4;
            final int SMALL_DIFF = 4;
            final int MED_DIFF = 7;
            final int LARGE_DIFF = 9;
            String exitCharacter = "q";
            String name;
            String[][] secondPassList;
            String[] finalList;
            String header;

            System.out.print("Enter name of passenger to check or q to exit: ");
            String inputName = in.nextLine();

            while (!inputName.equals(exitCharacter)){
                // Get i for making 2D array
                int i = 0;

                header = sc.nextLine();
                while (sc.hasNext()) {
                    name = sc.nextLine();

                    if (Math.abs(inputName.length() - name.length()) < SMALL_DIFF) {
                        matchRatio = FuzzySearch.weightedRatio(inputName.toLowerCase(), name.toLowerCase());
                    }else if (Math.abs(inputName.length() - name.length()) < MED_DIFF){
                        matchRatio = FuzzySearch.ratio(inputName.toLowerCase(), name.toLowerCase());
                    }else if(Math.abs(inputName.length() - name.length()) < LARGE_DIFF){
                        matchRatio = FuzzySearch.partialRatio(inputName.toLowerCase(), name.toLowerCase());
                    }else{
                        matchRatio = 0;
                    }

                    if (matchRatio >= CONST_MATCH_RATIO){
                        i++;
                    }
                }
                
                sc.close();
                secondPassList = new String[i][2];
                sc = new Scanner(file);
                System.out.println("\nFirst Pass results: \n");
                // Populate Array
                i = 0;
                header = sc.nextLine();
                while (sc.hasNext()){
                    name = sc.nextLine();

                    if(Math.abs(inputName.length() - name.length()) < SMALL_DIFF){
                        matchRatio = FuzzySearch.weightedRatio(inputName.toLowerCase(), name.toLowerCase());
                    }else if(Math.abs(inputName.length() - name.length()) < MED_DIFF){
                        matchRatio = FuzzySearch.ratio(inputName.toLowerCase(), name.toLowerCase());
                    }else if(Math.abs(inputName.length() - name.length()) < LARGE_DIFF){
                        matchRatio = FuzzySearch.partialRatio(inputName.toLowerCase(), name.toLowerCase());
                    }else{
                        matchRatio = 0;
                    }

                    if (matchRatio >= CONST_MATCH_RATIO){
                        i++;
                        String output = String.format(i + ") The passenger %s has a %d%% match with %s", inputName, matchRatio, name);
                        System.out.println(output);
                        secondPassList[i-1][0] = name;
                        secondPassList[i-1][1] = Integer.toString(matchRatio);

                    }
                }

                System.out.println();

                //Second pass code from here:
                int j = 0;
                for(i=0; i<secondPassList.length; i++){
                    if (Soundex.compareSoundex(inputName, secondPassList[i][0]) >= SOUNDEX_SIM) {
                        j++;
                    }
                }

                finalList = new String[j];
                j = 0;
                for(i=0; i<secondPassList.length; i++){
                    if (Soundex.compareSoundex(inputName, secondPassList[i][0]) >= SOUNDEX_SIM) {
                        finalList[j] = secondPassList[i][0];
                        j++;
                    }
                }

                System.out.println("Second Pass results: ");
                for(i=0; i<finalList.length; i++){
                    System.out.println(i + 1 + ")" + inputName + " matches with: " + finalList[i]);
                    System.out.println(Soundex.soundexCode(inputName) + " | " + Soundex.soundexCode(finalList[i]));
                }

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



}