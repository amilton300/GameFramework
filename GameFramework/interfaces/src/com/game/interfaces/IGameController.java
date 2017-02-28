package com.game.interfaces;

public interface IGameController {
	
	public int getPlayerScore();
	public int getEnemyScore();
	public int getSpecialItemPlayerCollidedID();

	public float[] getPlayersX();
	public float[] getPlayersY();
	public float getBallX();
	public float getBallY();
	public float getDeltaTime();
	public float getSpecialItemX();
	public float getSpecialItemY();
	
	public boolean getLeftButtonPressed();
	public boolean getRightButtonPressed();
	public boolean getUpButtonPressed();
	public boolean getCollisionRight();
	public boolean getCollisionLeft();
	public boolean getGameOver();
	public boolean getSpecialItemCollided();

	//public IArtificialIntelligence getAI();
	
	public void setPlayersX(float[] x);
	public void setPlayersY(float[] y);
	public void setBallX(float x);
	public void setBallY(float y);
	public void setSpecialItemX(float x);
	public void setSpecialItemY(float y);
	public void setDeltaTime(float deltaTime);
	public void setPlayerScore(int score);
	public void setEnemyScore(int score);
	public void setSpecialItemPlayerCollidedID(int playerID);
	public void setLeftButtonPressed(boolean pressed);
	public void setRightButtonPressed(boolean pressed);
	public void setUpButtonPressed(boolean pressed);
	public void setCollisionRight(boolean collided);
	public void setCollisionLeft(boolean collided);
	public void setGameOver(boolean gameOver);
	public void setSpecialItemCollided(boolean collided);
	public void reset();
	
	//public void setArtificialIntelligence(IArtificialIntelligence ai);
}
