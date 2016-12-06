import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.math.*;

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
        this.placeShip( new Ship("carrier") );
        this.placeShip( new Ship("battleship") );
        this.placeShip( new Ship("cruiser") );
        this.placeShip( new Ship("destroyer") );
        this.placeShip( new Ship("submarine") );
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
        File file = new File("/Users/Silent/Documents/UMBC/CS471/proj/local_bship/model.txt");
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

        /* replacing soon - left in so revision works */
        boolean valid = false;
        double N = 10;
        double dir;
        double orient, pos;
        int l_size = ship.size;

        do {
            double t = (Math.random()*42);
            dir = Math.pow(-1.0, t);
            orient = (Math.random()*10 % 2 == 0) ? -1 : 1;
            pos = (orient==1)  ? ((dir>0) ? ((Math.random()*10.0) % (N-l_size+1))*((Math.random()*10.0) % N)  : (((l_size-1)+((Math.random()*10.0) % (N-l_size+1)))*((Math.random()*10.0) % N))) : ((dir>0) ? ((N*(l_size-1)) + ((Math.random()*10.0) % ((N*N)-(N*(l_size-1))))) : ((Math.random()*10.0) % (N*N-(N*l_size-1))));
            valid = yourField.place(ship, (int)(pos % 10), (int)(pos/10), (int)dir);

            if (!valid) {
                continue;
            }

            break;

        } while (!valid);


    }
        
}