package com.java.project.Game.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calcul{

	String[] calcul;
	
	public Calcul() {
		super();
	}

	private int getRandomNumber() {
		return (int)(Math.random() * 10 + 1);
	}

	private String getRandomStringNumber(){
		return String.valueOf(this.getRandomNumber());
	}
	
	private String getOperator() {
		String[] operators = {"+", "-", "/", "x"};
		return operators[(int)(Math.random() * operators.length)];
	}

	public String[] generateCalcul(int operandCount){
		String[] newCalcul = new String[operandCount+operandCount-1];
		newCalcul[0] = getRandomStringNumber();
		for(int i=1; i<newCalcul.length; i++){
			if(i%2==0){
				newCalcul[i]=this.getRandomStringNumber();
			}else{
				newCalcul[i]=this.getOperator();
			}
		}

		this.calcul = newCalcul;

		return newCalcul;
	}

	public String getCalcul(){
		StringBuilder sb = new StringBuilder();
		for(String s:this.calcul) {
			sb.append(s);
		}
		return sb.toString();
	}
	
	public boolean isEqual(String response) {
		Float responseNumber = Float.parseFloat(response);
		Float result = Float.parseFloat(this.calculate());
		return Float.compare(responseNumber, result) == 0;
	}
	
	private String calculate() {
		List<Integer> indexes = this.getOperandStrongIndexes();
		
		float finalResult = 0;
		
		for(Integer i: indexes) {
			float first = Float.parseFloat(this.calcul[i-1]);
			float second = Float.parseFloat(this.calcul[i+1]);
			//calculate numbers around this index
			float res = this.calcFromOperand(first, this.calcul[i], second);
			
			this.calcul[i-1] = null;
			this.calcul[i] = null;
			this.calcul[i+1] = 	Float.toString(res);
		}
		
		List<String> restOfCalcul = new ArrayList<>();
		for(String s: this.calcul) {
			if(s != null) {
				restOfCalcul.add(s);
			}
		}
		
		for(int i=0; i<restOfCalcul.size(); i++){
			
			if(i==0) {
				finalResult+= Float.parseFloat(restOfCalcul.get(i));
			}else if(restOfCalcul.get(i-1).equals("+")) {
				finalResult+= Float.parseFloat(restOfCalcul.get(i));
			}else if(restOfCalcul.get(i-1).equals("-")) {
				finalResult-= Float.parseFloat(restOfCalcul.get(i));
			}
		}
		
		String stringResult = Float.toString(finalResult);
		
		return stringResult;
    }
	
	private float calcFromOperand(float first, String operand, float second) {
		float res = 0;
		switch(operand) {
			case "x":
				res = first * second;
				break;
			case "/":
				res = first / second;
				break;
			case "+":
				res = first + second;
				break;
			case "-":
				res = first - second;
		}
		
		return res;
	}
	
	private List<Integer> getOperandStrongIndexes(){
		List<Integer> indexes = new ArrayList<>();
		int i=0;
		for(String s: this.calcul) {
			if(s.equals("x") || s.equals("/")) {
				indexes.add(i);
			}
			i++;
		}
		
		return indexes;
	}
}
