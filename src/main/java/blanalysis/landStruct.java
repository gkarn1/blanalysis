package blanalysis;

import java.util.*;

//In this class I am trying to create a land format or grid when I will inspect the barren land
public class landStruct {
    private int breadth = 400; //max breadth <- x
    private int length = 600; //max length <- y
    private int[][] land; //land to be inspected

    public landStruct(String input){
        land = new int[length][breadth];
        startAutoGriding();
        placeBarrenRect(input);
    }

    /**
     *  @param input input vertices for barren land as lower left x and y and the upper right x and y
     *  @param length length of grid
     *  @param breadth breadth of grid
     */
    public landStruct(String input, int length, int breadth){
        //constructor for custom input
        this.length = length;
        this.breadth = breadth;
        land = new int[length][breadth];
        startAutoGriding(); //filling grid with 1 for fertile land
        placeBarrenRect(input); //setting the barren land(rectangular)
    }
    //Initializing the grid structure using 1 for fertile land
    //Place rows on the basis of y values and columns on the basis of x values
    private void startAutoGriding() {
        for(int y = 0; y < length; y++){
            for(int x = 0; x < breadth; x++){
                land[y][x] = 1;
            }
        }
    }

    //Now I will set the barren land in our land structure or grid
    public final void placeBarrenRect(String in) throws NumberFormatException, ArrayIndexOutOfBoundsException {
        String input = in.trim().replace(",", ""); //comma removal
        String[] singleVertex = input.split(" "); //split into String array by space
        int[] vertex = new int[singleVertex.length]; //integer array initialization
        //Below we will get the input as integers and passed into an array
        for(int i = 0; i < singleVertex.length; i++){
            vertex[i] = Integer.parseInt(singleVertex[i]);
            if(vertex[i] < 0){
                throw new NumberFormatException();
            }
        }
        //Now we will get the lower and upper x and y bounds
        for(int i = 0; i < vertex.length; i += 4) {
            int x_low = vertex[i];
            int y_low = vertex[i + 1];
            int x_high = vertex[i + 2];
            int y_high = vertex[i + 3];
            //Placing barren land gird as 0
            for (int y = y_low; y <= y_high; y++) {
                for (int x = x_low; x <= x_high; x++) {
                    land[y][x] = 0;
                }
            }
        }
    }

    //Getting discrete fertile land from the grid
    public String fertileLand(){
        ArrayList<Integer> fertile = new ArrayList<>(); //Stores discrete fertile land areas
        fertile = inspect(fertile, 0, 0); //starts from origin
        Collections.sort(fertile); //Sorts from smallest to largest fertile land areas
        String out = "No fertile land";
        if(!fertile.isEmpty()){
            out = "";
            for(Integer fertLand : fertile){
                out = out.concat(fertLand.toString() + " ");
            }
        }
        resetGrid();
        return out.trim();
    }

    //Now this will start inspecting surrounding areas for fertile land from each coordinate
    private ArrayList<Integer> inspect(ArrayList<Integer> collectedAreas, int yCoord, int xCoord) {
        for(int y = yCoord; y < length; y++){
            for(int x = xCoord; x < breadth; x++){
                if (land[y][x] == 1) { //starts the inspection if it has value 1; fertile land
                    int fertilePart = survey(land, y, x); //inspecting surrounding area
                    collectedAreas.add(fertilePart); //storing the area
                    inspect(collectedAreas, y, x); //now moving onto the next coordinate
                }
            }
        }
        return collectedAreas;
    }

    //Now this will start inspecting surrounding areas for fertile land from the given coordinate
    private int survey(int[][] land, int y, int x) {
        int count = 0;
        Stack<int[]> stack = new Stack<int[]>();
        int[] coord = {y,x}; //initial coordinates
        stack.push(coord); //start stack
        while(!stack.isEmpty()){
            int[] checkCoord = stack.pop(); //checking the coordinates
            y = checkCoord[0];
            x = checkCoord[1];
            //surrounding coordinates
            int l = x - 1;
            int r = x +1;
            int d = y - 1;
            int u = y + 1;

            //Pushing fertile coordinates in the stack if the land is fertile
            if(land[y][x] == 1){
                count++; //count increment
                land[y][x] = 2; //marking land inspected to avoid recounting
                //pushing to stack if the land is fertile
                if(l >= 0 && land[y][l] == 1){
                    int[] lCoord = {y, l};
                    stack.push(lCoord);
                }
                if(r < breadth && land[y][r] == 1){
                    int[] rCoord = {y, r};
                    stack.push(rCoord);
                }
                if(d >= 0 && land[d][x] == 1){
                    int[] dCoord = {d, x};
                    stack.push(dCoord);
                }
                if(u < length && land[u][x] == 1){
                    int[] uCoord = {u, x};
                    stack.push(uCoord);
                }
            }
        }
        return count;
    }

    //Resetting grid after inspection and marking fertile land as 1
    private void resetGrid() {
        for(int y = 0; y < length; y++){
            for(int x = 0; x < breadth; x++){
                if(land[y][x] == 2){
                    land[y][x] = 1;
                }
            }
        }
    }

    //Cleans the barren land and gives only fertile land
    public final void clearBarren(){
        startAutoGriding();
    }
}
