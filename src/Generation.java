// Worked on the Generation class with Guillermo and John

import java.util.Arrays;

public class Generation {
	
	private boolean[] cellStates;
	
	public Generation (boolean... states) {
		
		if(states != null && states.length !=0) {
			boolean[] copyArray = Arrays.copyOf(states, states.length);
			cellStates = copyArray;
		}
		else {
			cellStates = new boolean[] {false};
			}
		}
	
		
	public Generation (String states, char trueSymbol) {
		if(states == null || states.length() == 0){
			cellStates = new boolean[] {false};
		}
		
		else {
			cellStates = new boolean[states.length()];
			for(int i =0; i < states.length(); ++i) {
				if(states.charAt(i)==trueSymbol) {
					cellStates[i] = true;
				}
				else if(states.charAt(i)!=trueSymbol) {
					cellStates[i] = false;
				}
			}
		}
	}
	
	public boolean getState(int idx) {
		return cellStates[idx];
	}
	
	public boolean[] getStates() {
		boolean[] copyArray = Arrays.copyOf(cellStates, cellStates.length);
		return copyArray;
	}
	
	public String getStates(char falseSymbol, char trueSymbol) {
		String states = "";
		for(int i =0; i < cellStates.length; ++i) {
			if(cellStates[i] == false) {
				states = states + falseSymbol;
			}
			else if(cellStates[i] == true) {
				states = states + trueSymbol;
			}
		}
		return states;
	}
	
	public int size() {
		return cellStates.length; 
	}
	
}
