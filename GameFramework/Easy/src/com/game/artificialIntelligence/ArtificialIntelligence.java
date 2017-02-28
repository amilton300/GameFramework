package com.game.artificialIntelligence;

import com.game.interfaces.IArtificialIntelligence;

public class ArtificialIntelligence implements IArtificialIntelligence {

	public float getX(float deltaTime, float botX, float ballPositionX, float ballPositionY,
			boolean isAlly) {
		if(isAlly){
			if(botX - ballPositionX <= 100 && botX - ballPositionX >= -100){
				if(ballPositionX - 1 > botX)
				return botX + deltaTime*85;
				else if(ballPositionX <= botX)
				return botX - deltaTime*85;
			}
		}
		else{
			if(botX - ballPositionX <= 100 && botX - ballPositionX >= -100){
				if(ballPositionX + 1 > botX)
					return botX + deltaTime*60;
				else if(ballPositionX <= botX)
					return botX - deltaTime*60;
			}
		}
		return botX;
	}

	public float getY(float deltaTime, float botY, float ballPositionX, float ballPositionY, 
			boolean isAlly) {
		if(botY > 30)
			return botY - deltaTime*350;
		return 30;
	}
}