package com.game.controller;

import com.game.interfaces.IGameController;

public class GameController implements IGameController{
	
	/*GETTERS*/
	@Override
	public float getBallX(){
		return this.ballX;
	}

	@Override
	public float getBallY(){
		return this.ballY;
	}

	@Override
	public float getDeltaTime(){
		return this.deltaTime;
	}
	
	@Override
	public float[] getPlayersX(){
		return this.playersX;
	}

	@Override
	public float[] getPlayersY(){
		return this.playersY;
	}

	@Override
	public boolean getLeftButtonPressed(){
		return this.leftButtonPressed;
	}

	@Override
	public boolean getRightButtonPressed(){
		return this.rightButtonPressed;
	}

	@Override
	public boolean getUpButtonPressed(){
		return this.upButtonPressed;
	}

	@Override
	public boolean getCollisionLeft(){
		return this.collisionLeft;
	}

	@Override
	public boolean getCollisionRight(){
		return this.collisionRight;
	}
	
	@Override
	public boolean getGameOver(){
		return this.gameOver;
	}
	
	@Override
	public boolean getSpecialItemCollided(){
		return this.specialItemCollided;
	}
	
	@Override
	public int getEnemyScore(){
		return this.enemyScore;
	}

	@Override
	public int getPlayerScore(){
		return this.playerScore;
	}
	
	@Override
	public int getSpecialItemPlayerCollidedID(){
		return this.specialItemPlayerCollidedID;
	}
	
	@Override
	public float getSpecialItemX() {
		return this.specialItemX;
	}

	@Override
	public float getSpecialItemY() {
		return this.specialItemY;
	}
	
	/*SETTERS*/
	@Override
	public void setBallX(float x){
		this.ballX = x;
	}

	@Override
	public void setBallY(float y){
		this.ballY = y;
	}
	
	@Override
	public void setSpecialItemX(float x) {
		this.specialItemX = x;
		
	}

	@Override
	public void setSpecialItemY(float y) {
		this.specialItemY = y;
	}

	@Override
	public void setEnemyScore(int score){
		this.enemyScore = score;
	}

	@Override
	public void setPlayerScore(int score){
		this.playerScore = score;
	}

	@Override
	public void setPlayersX(float[] x){
		this.playersX = x;
	}

	@Override
	public void setPlayersY(float[] y){
		this.playersY = y;
	}

	@Override
	public void setCollisionLeft(boolean co){
		this.collisionLeft = co;
	}

	@Override
	public void setCollisionRight(boolean co){
		this.collisionRight = co;
	}

	@Override
	public void setLeftButtonPressed(boolean pressed){
		this.leftButtonPressed = pressed;
	}

	@Override
	public void setRightButtonPressed(boolean pressed){
		this.rightButtonPressed = pressed;
	}

	@Override
	public void setUpButtonPressed(boolean pressed){
		this.upButtonPressed = pressed;
	}

	@Override
	public void setGameOver(boolean gameOver){
		this.gameOver = gameOver;
	}
	
	@Override
	public void setDeltaTime(float delta){
		this.deltaTime = delta;
	}
	
	@Override
	public void setSpecialItemCollided(boolean collided) {
		this.specialItemCollided = collided;
	}

	@Override
	public void setSpecialItemPlayerCollidedID(int playerID) {
		this.specialItemPlayerCollidedID = playerID;	
	}
	
	public void reset(){
		this.upButtonPressed = this.leftButtonPressed = this.rightButtonPressed = this.collisionRight =
				this.collisionLeft = this.gameOver = this.specialItemCollided = false;
		this.ballX = this.ballY = this.deltaTime = this.specialItemX = this.specialItemY = 0;
		this.playerScore = this.enemyScore = 0;
		this.playersX = this.playersY = null;
		this.specialItemPlayerCollidedID = -1;
	}

	public static GameController getInstance(){
		if(instance == null)
			instance = new GameController();
		return instance;
	}
	
	private GameController(){	
	}
	
	private static GameController instance;
	
	private boolean upButtonPressed = false,
			leftButtonPressed = false,
			rightButtonPressed = false,
			collisionRight = false,
			collisionLeft = false,
			gameOver = false,
			specialItemCollided = false;
	private float ballX = 0,
			ballY = 0,
			deltaTime = 0,
			specialItemX = 0,
			specialItemY = 0;
	private float[] playersX, playersY;
	private int playerScore, enemyScore, specialItemPlayerCollidedID;
}
