package com.shaurya;

/**
 * Created by ShauryaSinha on 06/06/17.
 */
public class Soundex {

    public static String soundexCode(String inputString){
        String code = "";
        String str = "";
        String spacesString = inputString.toUpperCase();
        char[] letters1 = {'F', 'P'};
        char[] letters2 = {'B', 'V', 'W'};
        char[] letters3 = {'J', 'G'};
        char[] letters4 = {'K', 'C', 'S'};
        char[] letters5 = {'D', 'T', 'L', 'M', 'N', 'R', 'Q', 'X', 'Z'};

        //Removing Spaces and Vowels
        String vowelString = spacesString.replace(" ", "");

        code += vowelString.charAt(0);

        String string1a = vowelString.replace("A", "");
        String string1e = string1a.replace("E", "");
        String string1i = string1e.replace("I", "");
        String string1o = string1i.replace("O", "");
        String string1u = string1o.replace("U", "");
        String string1h = string1u.replace("H", "");
        String string1 = string1h.replace("Y", "");

        // Sanitising input strings

        str += string1.charAt(0);
        for (int i=0; i<string1.length()-1; i++){
            //1
            if (contains(letters1, string1.charAt(i))){
                int k = 0;
                for (int j=i+1; j<string1.length(); j++){
                    if (contains(letters1, string1.charAt(j))){
                        k++;
                    }else{
                        break;
                    }
                }
                i += k;
            }

            //2
            if (contains(letters2, string1.charAt(i))){
                int k = 0;
                for (int j=i+1; j<string1.length(); j++){
                    if (contains(letters2, string1.charAt(j))){
                        k++;
                    }else{
                        break;
                    }
                }
                i += k;
            }

            //3
            if (contains(letters3, string1.charAt(i))){
                int k = 0;
                for (int j=i+1; j<string1.length(); j++){
                    if (contains(letters3, string1.charAt(j))){
                        k++;
                    }else{
                        break;
                    }
                }
                i += k;
            }

            //4
            if (contains(letters4, string1.charAt(i))){
                int k = 0;
                for (int j=i+1; j<string1.length(); j++){
                    if (contains(letters4, string1.charAt(j))){
                        k++;
                    }else{
                        break;
                    }
                }
                i += k;
            }

            //5
            if (contains(letters5, string1.charAt(i))){
                int k = 0;
                for (int j=i+1; j<string1.length(); j++){
                    if (contains(letters5, string1.charAt(j))){
                        k++;
                    }else{
                        break;
                    }
                }
                i += k;
            }


            if (i + 1 < string1.length()){
                str += string1.charAt(i+1);
            }


        }

        // Soundex code for str
        for(int i=0; i<str.length(); i++){
            switch(str.toUpperCase().charAt(i)){
                case 'F':case 'P' :
                    code += "1";
                    break;
                case 'B':case 'V':case 'W':
                    code += "2";
                    break;
                case 'J':case 'G':
                    code += '3';
                    break;
                case 'K':case 'C':case 'S':
                    code += 4;
                    break;
                case 'D':case 'T': case 'L': case 'M': case 'N':case'R':case 'Q':case 'X':case 'Z':
                    code += 5;
                    break;
                default: break;
            }
        }


        String finalCode = (code + "000").substring(0,4);

        return finalCode;
    }

    public static int compareSoundex(String inputString1, String inputString2){
        String code1 = soundexCode(inputString1);
        String code2 = soundexCode(inputString2);
        int sim = 0;

        for(int i=0; i<4; i++){
            if (code1.charAt(i) == code2.charAt(i)){
                sim++;
            }
        }

        return sim;
    }

    public static boolean contains(char[] arr, char targetValue) {
        for(char s: arr){
            if(s == targetValue)
                return true;
        }
        return false;
    }
}
