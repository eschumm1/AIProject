import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class AI extends Player{
    int difficulty;
    private double[] model = new double[400];
//    public Board yourField = new Board(0);

    public AI(int difficulty, String name){
        this.difficulty = difficulty;
        super.name = name;

        for(int i = 0; i < 10; i++)
            for(int j = 0; j < 10; j++){
                //super.yourField[i + 10*j] = "~";
                super.knownMap[i*10 + j] = '~';
            }
        //long st = System.currentTimeMillis();
        this.placeShip( new Ship("Carrier") );
        this.placeShip( new Ship("Battleship") );
        this.placeShip( new Ship("Cruiser") );
        this.placeShip( new Ship("Destroyer") );
        this.placeShip( new Ship("Submarine") );
        //long e = System.currentTimeMillis() - st; currently takes 1 millisecond to make a board
        //float e1 = e/1000F;
        //System.out.printf("%.10f",e1);
        Arrays.fill(this.intmap, -1);

        getModel();

    }

   /* public void vectorizeBoard() {
        int[] compmap = new int[100];
        for(int i = 0; i < 100; i++) {
            compmap[i] = ;
        }
    }*/


    public void getModel() {
        /*
        try {
            BufferedReader in = new BufferedReader(new FileReader("/Users/Silent/Documents/UMBC/CS471/proj/AIProject/model.txt"));

            List<Double> list = new ArrayList<Double>();
            //while((str = in.readLine()) != null){
            if(in.readLine() == null) { throw new FileNotFoundException("Saving your butt"); }
            for (int pp = 0; pp < 400; pp++) {
                this.model[pp] = Double.parseDouble(in.readLine());
            }
            //}
        }
        catch(IOException FileNotFoundException) {
            System.out.println("No model for you. \n");
            Arrays.fill(model, 0);
        }*/

        ArrayList<Double> doubles = new ArrayList<Double>();
        Scanner scan;
        File file = new File("model.txt");
        try {
            scan = new Scanner(file);

            while(scan.hasNextDouble())
         {
            doubles.add( scan.nextDouble() );
        }

    } catch (FileNotFoundException e1) {
        e1.printStackTrace();
    }

        for(int p = 0; p < doubles.size(); p++) {
            model[p] = doubles.get(p);
        }
    }

    public int getDifficulty(){
        return this.difficulty;
    }


    public void doMove(Player opponent){
		if(this.difficulty == 1)
			easyMove(opponent);
		else if(this.difficulty == 2)
			mediumMove(opponent);
		else
			hardMove();
	}

	private void easyMove(Player opponent){
		int row = (int)(Math.random() * 10);
		int column = (int)(Math.random() * 10);
		int pos = row*10 + column;
		String hit;
		
		do{
    			row = (int)(Math.random() * 10);
    			column = (int)(Math.random() * 10);
    			pos = row*10 + column;
    		} while(this.knownMap[pos] != '~');

        hit = opponent.mark(pos); // returns "hit [type]" "miss" or "destroyed [type]"

        System.out.println(this.name + " " + hit);
			
        knownMap[pos] = (hit.contains("miss")) ? 'O' : 'X'; // mark move in map
        intmap[pos] = (hit.contains("miss")) ? 0 : (hit.contains("hit") ? 1 : (hit.contains("royer") ? 2 : (hit.contains("ubmarin") ? 3 : (hit.contains("attlesh") ? 4 : 5))));
	}

	private void mediumMove(Player opponent){
        double max = -1;
        int n;
        int pos = 0;
        for(n = 0; n < 100; n++) {
            if((model[n] > max) && (intmap[n] < 0)) { // find greatest probability not touched yet
                max = model[n];
                pos = n;
            }
        }

        String hit = opponent.mark(pos);
        System.out.println(this.name + " " + hit);

        knownMap[pos] = (hit.contains("miss")) ? 'O' : 'X'; // mark move in map
        intmap[pos] = (hit.contains("miss")) ? 0 : (hit.contains("hit") ? 1 : (hit.contains("royer") ? 2 : (hit.contains("ubmarin") ? 3 : (hit.contains("attlesh") ? 4 : 5))));
    }
	
	private void hardMove(){
		// do with model of prob(ship in square)

		// ? optimize ship placement based on winning initial configurations
	}

	private void placeShip(Ship ship) {
        boolean valid;
        int row, column, direction;

        do {
            direction = (int)(Math.random() * 4);
            row = (int)(Math.random() * 10);
            column = (int)(Math.random() * 10);
            valid = yourField.place(ship, row, column, direction);

            if (!valid)
                continue;

            break;
        } while (!valid);
    }
}