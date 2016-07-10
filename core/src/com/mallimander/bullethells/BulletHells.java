package com.mallimander.bullethells;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BulletHells extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture pTexture;
	Texture midLine;
	Input input;
	
	private int screenWidth = 1280;
	private int screenHeight = 720;
	
//	private int pW = 32;
//	private int pH = 32;
	private int pW = 64;
	private int pH = 64;
	private int pXOff = (int)(pW*0.5f);
	private int pYOff = (int)(pH*0.5f);
	
	private int numPlay = 40;
	
	private double bGAura = 0;
	
	Player players[];
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture(Gdx.files.internal("badlogic.jpg"));
		
		Pixmap pixmap = new Pixmap( pW, pH, Format.RGBA8888 );
		pixmap.setColor( 1, 1, 1, 1 );
		pixmap.fillCircle( pXOff, pYOff, pXOff-1 );
		pixmap.setColor( 0.5f, 0.5f, 0.5f, 1 );
		pixmap.fillCircle( pXOff, pYOff, (int)(pXOff*0.25f) );
		pTexture = new Texture( pixmap );
		
		pixmap = new Pixmap( pW, pW, Format.RGBA8888 );
		pixmap.setColor( 1, 1, 1, 1 );
		pixmap.drawLine(0, 0, 0, pW);
		for(int i = 0; i < pW; i ++){
			
			if(i%4 == 1){
				pixmap.drawLine(i, 0, i, pW);
				System.out.println(i + " : " + i%2);
			}
		}
//		pixmap.fillRectangle(0, 0, 3, pW);
//		pixmap.fillRectangle(pW-3, 0, 3, pW);
//		pixmap.fillRectangle(pXOff-2, 0, 4, pW);
//		pixmap.fillRectangle(0, 0, pW, pW);
		midLine = new Texture( pixmap );
		pixmap.dispose();
		
		
		makePlayers();
		
		input = Gdx.input;
		
		
		
	}
	
	public void input(){
		
		//Touch the screens
		for(int touch = 0; touch < 2; touch++){
			
			if(input.isTouched(touch)){
				if(input.getX(touch) < (screenWidth*0.5f) ){
					players[0].setX(input.getX(touch) - pXOff);
					players[0].setY(screenHeight - input.getY(touch) - pYOff);
				}else{
					players[1].setX(input.getX(touch) - pXOff);
					players[1].setY(screenHeight - input.getY(touch) - pYOff);
				}
			}
		}
		
		//Debug inputs
		if( input.isKeyJustPressed(Keys.R)){
			makePlayers();
		}
		
		//Exit
		if( ( input.isKeyPressed(Keys.CONTROL_LEFT) && input.isKeyPressed(Keys.CONTROL_RIGHT ) )
				&& input.isKeyJustPressed(Keys.Q) ){
			Gdx.app.exit();
		}
		
		
		
	}
	
	public void update(){
		
	}

	@Override
	public void render () {
		input();
		update();
		
		
		//Render
		if(bGAura > Math.PI){
			bGAura = 0;
		}else{
			bGAura += 0.01;
		}
//		Gdx.gl.glClearColor(0.1f, 0, 0.1f, 1);
		Gdx.gl.glClearColor((float)Math.cos(bGAura)*0.2f, 0, (float)Math.sin(bGAura)*0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		
		
		for (int i = 0; i < numPlay; i++){
			batch.setColor(players[i].getColor());
			batch.draw(pTexture, players[i].getX(), players[i].getY());
			
		}
		
		batch.setColor(0.5f,1,0.5f,0.75f);
//		batch.draw(midLine, (int)(screenWidth*0.5f) - pXOff, 0, pW, screenHeight);
		Color color1 = players[0].getColor();
		color1.a = 0.75f;
		batch.setColor(color1);
		batch.draw(midLine, (int)(screenWidth*0.5f) - pXOff, 0, pXOff, screenHeight);
		color1 = players[1].getColor();
		color1.a = 0.75f;
		batch.setColor(color1);
		batch.draw(midLine, (int)(screenWidth*0.5f) , 0, pXOff, screenHeight);
		
		
		
		batch.end();
	}
	
	public void makePlayers(){
		players = new Player[numPlay];
		for(int p = 0; p < numPlay; p ++){
			
			players[p] = new Player((byte)0, screenWidth, screenHeight-20-pH);
			players[p].setY(players[p].getY()+10);
			
			if(p%2 == 0){
				players[p].setX((float)Math.random()*((screenWidth-20-pW)*0.5f)+10);
			}else{
				players[p].setX((float)Math.random()*((screenWidth-10-pW)*0.5f)+(screenWidth*0.5f) - (pW-10));
			}
			
		}
		
		
	}
	
	
}
