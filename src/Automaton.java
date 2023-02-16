// Worked on the Automaton class with Keeton ;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class Automaton {
	private Rule rule;
	private ArrayList <Generation> generations;
	public char falseSymbol = '0';
	public char trueSymbol = '1';
	
	public Automaton (int ruleNum, Generation initial) {
		generations = new ArrayList<Generation>();
		generations.add(initial);
		rule = new Rule(ruleNum);
		falseSymbol = '0';
		trueSymbol = '1';
	}
	
	public Automaton (String filename) throws FileNotFoundException {
		Scanner file = new Scanner(new File(filename));
		String temp;
		rule = new Rule(file.nextInt());
		file.nextLine();
		temp = file.next();
		falseSymbol = temp.charAt(0);
		temp = file.next();
		trueSymbol = temp.charAt(0);
		file.nextLine();
		Generation gen = new Generation(file.next(),trueSymbol);
		generations = new ArrayList<Generation>();
		generations.add(gen);
		file.close();
		
	}
	
	public int evolve(int numSteps) {
		if(numSteps <= 0) {
			return 0;
		}
		else {
		for(int i =0; i <numSteps; ++i) {
			generations.add(rule.evolve(generations.get(generations.size()-1)));
			
		}
		return numSteps;
		}
	}
	
	public Generation getCurrentGeneration() {
		return generations.get(generations.size() - 1); 
	}
	
	public Generation getGeneration(int stepNum) {
		if(stepNum > generations.size()) {
			evolve(stepNum - generations.size() + 1);
		}
		return generations.get(stepNum);
	}
	
	public int getRuleNum() {
		return rule.getRuleNum();
	}
	
	public int getTotalSteps() {
		return generations.size() - 1;
	}
	
	public void saveEvolution(String filename) throws FileNotFoundException {
	PrintWriter writer = new PrintWriter(filename);
	writer.print(toString());
	writer.close();
	
	}
	
	public String toString() {
		StringJoiner sj = new StringJoiner(System.lineSeparator());
		for(int i = 0; i < generations.size(); ++i) {
			sj.add(generations.get(i).getStates(falseSymbol, trueSymbol));
		}
		return sj.toString();
	}
}
