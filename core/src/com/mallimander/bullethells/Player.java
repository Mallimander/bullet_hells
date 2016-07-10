package com.mallimander.bullethells;

import com.badlogic.gdx.graphics.Color;

public class Player {
	
	private float x;
	private float y;
	
	private float r;
	private float g;
	private float b;
	
	public Player (byte p, float sW, float sH){
		
		x = (float)Math.random() * sW;
		y = (float)Math.random() * sH;
		
		makeColor();
//		System.out.println( r + ", " + g + ", " + b);
	}
	
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	
	public void setX(float nX){
		x = nX;
	}
	public void setY(float nY){
		y = nY;
	}
	
	public Color makeColor(){
		double intensity = 0.6;
		double rando = 0.4;
		
		double rMod = 0;
		double gMod = 0;
		double bMod = 0;
		
		
		switch((int)(Math.random()*6)){
		 case 0: rMod = intensity;
		 break;
		 case 1: gMod = intensity;
		 break;
		 case 2: bMod = intensity;
		 break;
		 case 3: rMod = intensity; gMod = intensity;
		 break;
		 case 4: gMod = intensity; bMod = intensity;
		 break;
		 case 5: bMod = intensity; rMod = intensity;
			 
		}
		
		r = (float)(Math.random()*rando + rMod);
		g = (float)(Math.random()*rando + gMod);
		b = (float)(Math.random()*rando + bMod);
		return getColor();
	}
	
	public Color makeColor(float nR, float nG, float nB){
		r = nR;
		g = nG;
		b = nB;
		return getColor();
	}
	
	public Color getColor(){
		return new Color(r,g,b,1);
	}

}
