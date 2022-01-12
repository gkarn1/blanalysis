package blanalysis;

import java.util.Scanner;

public class BLAnalysis {

    //Take user input, analyze them and will finally display the output of each fertile zone from smallest to largest

    private static landStruct land;

    public static void main(String[] args) {
        System.out.println("Land size 400 x 600 with lower left corner (0,0) and upper right corner (399,599)");
        System.out.println("Enter barren land rectangle with 4 vertices (lower left coordinate followed by upper right coordinate)");
        System.out.println("Enter total coordinates must be multiple of 4 with no negative coordinates");
        System.out.println("Example: 0 124 344 380, 111 212 311 400");

        //checking if the input is valid
        boolean validInput = false;
        while(!validInput){
            try{
                System.out.println("Enter the coordinates of the barren land: ");
                Scanner in = new Scanner(System.in);
                String input = in.nextLine();
                land = new landStruct(input);
                validInput = true;
            }
            catch(NumberFormatException | ArrayIndexOutOfBoundsException ex){
                System.out.println("Input not valid. Please follow the format");
            }
        }
        String out = land.fertileLand();
        System.out.println(out);
    }
}
