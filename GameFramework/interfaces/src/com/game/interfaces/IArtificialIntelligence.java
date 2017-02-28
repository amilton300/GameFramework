package com.game.interfaces;

public interface IArtificialIntelligence {
	public float getX(float deltaTime, float botX, float ballPositionX, float ballPositionY,
			boolean isAlly);
	public float getY(float deltaTime, float botY, float ballPositionX, float ballPositionY, 
			boolean isAlly);
}