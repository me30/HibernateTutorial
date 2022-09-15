package com.commons;

public class MainTest {

	public static void main (String[] args) {
		int i = 0;
		for (i=10; i<50; i++) {
			switch(i) {
			case 0:i += 5;
			case 1:i += 2;
			case 2:i += 5;
			case 3:i += 2;
			case 4:i += 5;
			case 5:i += 2;
			default:i += 5;
			break;
			} 
			System.out.println(i);
		}
	}
}