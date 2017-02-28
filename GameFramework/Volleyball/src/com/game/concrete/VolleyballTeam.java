package com.game.concrete;

import com.game.factory.Volleyball;
import com.game.interfaces.IGameController;
import com.game.interfaces.IGraphicController;
import com.game.interfaces.IPrototype;
import com.game.abstractClasses.ITeam;

public class VolleyballTeam extends ITeam implements IPrototype{
	
	public void init() {
		this.gameController = Volleyball.core.getGameController();
		this.graphicController = Volleyball.core.getGraphicController();
		
		this.playersX = new float[4];
		this.playersY = new float[4];
		
		this.didLand = true;
		
		reset();
	}
	
	public void reset(){
		this.playersX[0] = 230;
		this.playersY[0] = 30;
		
		this.playersX[1] = 370;
		this.playersY[1] = 30;
		
		this.gameController.setPlayersX(playersX);
		this.gameController.setPlayersY(playersY);
	}

	public void updateTeam() {
		this.updatePlayer();
		this.updateBot();
		
		this.gameController.setPlayersX(playersX);
		this.gameController.setPlayersY(playersY);
		
		playersX = this.gameController.getPlayersX();
		playersY = this.gameController.getPlayersY();
			
		this.graphicController.drawPlayer(playersX[0], playersY[0], 20, 30, false);
		this.graphicController.drawPlayer(playersX[1], playersY[1], 20, 30, false);
	}
	
	public IPrototype pClone() {
		return new VolleyballTeam();
	}
	
	public VolleyballTeam(){	
	}
	
	private void updatePlayer(){
		if(this.gameController.getLeftButtonPressed())
			this.playersX[0] -= this.gameController.getDeltaTime()*120;
		else if(this.gameController.getRightButtonPressed())
			if(this.playersX[0] < 300)
				this.playersX[0] += this.gameController.getDeltaTime()*120;
		
		if(this.gameController.getUpButtonPressed() && this.didLand == true)
			if(this.playersY[0] < 60)
				this.playersY[0] += this.gameController.getDeltaTime()*150;
			else
				this.didLand = false;
		else if(this.playersY[0] > 30)
			this.playersY[0] -= this.gameController.getDeltaTime()*150;
		else if(this.playersY[0] <= 30){
			this.playersY[0] = 30;
			this.didLand = true;
		}
	}
	
	private void updateBot(){
		this.playersX[1] = this.ai.getX(this.gameController.getDeltaTime()* 2, this.playersX[1], this.gameController.getBallX(), this.gameController.getBallY(), false);
		if(this.playersX[1] <= 325)
			this.playersX[1] = 325;
	}
	
	private IGameController gameController;
	private IGraphicController graphicController;
	
	private float []playersX, playersY;
	private boolean didLand;
}
