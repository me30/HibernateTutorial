package com.commons;

public class DrawEvent{

	/**
	 * 
	 */

	 private String message;
	 
	public DrawEvent(Object source, String message) {
        this.message = message;
        System.out.println("source::"+source);
        System.out.println("its called by us ..., "+message);
    }
	
    public String getMessage() {
        return message;
    }

	@Override
	public String toString() {
		return "DrawEvent [message=" + message + "]";
	}
    
    
}

