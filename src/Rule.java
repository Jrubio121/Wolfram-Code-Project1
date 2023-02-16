// Worked on the Rule class with Keeton ; Guillermo
// And John

public abstract class Rule {
	int ruleNum;
	private String binaryRule;
	
	public Rule(int ruleNum) {
		if(ruleNum < 0) {
			this.ruleNum = 0;
		}
		else if(ruleNum > 255) {
			this.ruleNum = 255;
		}
		else {
			this.ruleNum = ruleNum;
		}
		this.binaryRule = String.format("%8s",Integer.toBinaryString(this.ruleNum)).replace(' ', '0');
	}
	
	public int getRuleNum() {
		return ruleNum; //stub
	}
	
	public static boolean[] getNeighborhood(int idx, Generation gen) {
		boolean[] neighborhood = new boolean[3];
		if(idx>0) {
			neighborhood[0] = gen.getState(idx - 1);
		}
		else if(idx == 0) {
			neighborhood[0]=gen.getState(gen.size()-1);
		}
		neighborhood[1] = gen.getState(idx);
		
		if(idx == gen.size() - 1) {
			neighborhood[2] = gen.getState(0);
			
		}
		else if(idx < gen.size() - 1) {
			neighborhood[2] = gen.getState(idx+1);
		}
		
		return neighborhood;
	}
	
	public boolean evolve (boolean[] neighborhood) {
		int [] test = new int[]{111,110,101,100,11,10,1,000};
		
		char next = ' ';
		String neighborhoodString = "";
		
		for(int i = 0; i < neighborhood.length; ++i) {
			if(neighborhood[i] == true) {
				neighborhoodString += "1";
			}
			else if(neighborhood[i] == false) {
				neighborhoodString += "0";
			}
		}
		
		int val = Integer.valueOf(neighborhoodString);
		
		for(int i = 0; i < test.length; ++i) {
			if(val == test[i]) {
				next = binaryRule.charAt(i);
			}
		}
		
		if(next == '0') {
			return false;
		}
		else {
			return true;
		}
		
	}
	
	public Generation evolve(Generation gen) {
		boolean[] temp = new boolean[gen.size()];
		
		for(int i = 0; i < gen.size(); ++i) {
			temp[i] = evolve(getNeighborhood(i,gen));
		}
		Generation tempGen = new Generation(temp);
		
		return tempGen;
	}
	
}
