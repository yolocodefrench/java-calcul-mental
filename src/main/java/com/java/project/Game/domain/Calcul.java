package com.java.project.Game.domain;

import java.util.Arrays;

public class Calcul implements ICalcul{

	String[] calcul;
	
	private int getRandomNumber() {
		return (int)(Math.random() * 10 + 1);
	}

	private String getRandomStringNumber(){
		return String.valueOf(this.getRandomNumber());
	}
	
	private String getOperator() {
		String[] operators = {"+", "-", "/", "*", "%"};
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
		return Arrays.toString(this.calcul);
	}
	
	public int calculate() {
		//TODO calculate the operations
		
		return 0;
	}

	@Override
	public int addition(int a, int b) {
		return a+b;
	}

	@Override
	public int substraction(int a, int b) {
		return a-b;
	}

	@Override
	public int multiplication(int a, int b) {
		return a*b;
	}

	@Override
	public int division(int a, int b) {
		return a/b;
	}

	@Override
	public int modulo(int a, int b) {
		return a%b;
	}
}
