import java.util.ArrayList;

public abstract class Player {
	ArrayList<String> usedSpaces = new ArrayList<String>(15);
    Object[][] yourField = new Object[10][10];
    char[][] knownMap = new char[10][10];
    public String name;
    boolean loss = false;
    
    public boolean getLoss(){
		return false;
    }
    
    @Override
    public String toString(){
		String total = "";
		
		for(char[] temp : knownMap){
			for(char temp2 : temp)
				total += temp2 + " ";
			total += '\n';
		}
		
		total += "-------------------\n";
		
		for(Object[] temp1 : yourField){
			for(Object temp2 : temp1)
				total += temp2.toString().charAt(0) + " ";
			total += '\n';
		}
		return total;
    }

}
