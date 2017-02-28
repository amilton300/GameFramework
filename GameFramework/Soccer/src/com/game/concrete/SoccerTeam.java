package com.game.concrete;

import java.util.Observable;
import java.util.Observer;

import com.game.factory.Soccer;
import com.game.interfaces.IGameController;
import com.game.interfaces.IGraphicController;
import com.game.interfaces.IPrototype;
import com.game.players.Player;
import com.game.power.IPowerStrategy;
import com.game.power.StrongPower;
import com.game.power.WeakPower;
import com.game.abstractClasses.ITeam;
import com.game.abstractClasses.SubjectTemplateMethod;

public class SoccerTeam extends ITeam implements IPrototype, Observer{
	
	@Override
	public void init(){
		this.gameController = Soccer.core.getGameController();
		this.graphicController = Soccer.core.getGraphicController();
		
		this.didLand = true;	
		
		this.playersX = new float[4];
		this.playersY = new float[4];	
		
		reset();
	}
	
	@Override
	public void reset(){
		
		playersX[0] = 220;
		playersY[0] = 30;
		
		playersX[1] = 100;
		playersY[1] = 30;
		
		playersX[2] = 370;
		playersY[2] = 30;
		
		playersX[3] = 500;
		playersY[3] = 30;
		
		this.gameController.setPlayersX(playersX);
		this.gameController.setPlayersY(playersY);
	}
	
	@Override
	public void updateTeam() {
		this.updatePlayer();
		this.updateBots();
		
		playersX = this.gameController.getPlayersX();
		playersY = this.gameController.getPlayersY();
		
		if(this.playerEnergy > 50)
			this.graphicController.drawPlayer(playersX[0], playersY[0], 30, 40, true);
		else
			this.graphicController.drawPlayer(playersX[0], playersY[0], 30, 40, false);
		this.graphicController.drawPlayer(playersX[1], playersY[1], 30, 40, false);
		this.graphicController.drawPlayer(playersX[2], playersY[2], 30, 40, false);
		this.graphicController.drawPlayer(playersX[3], playersY[3], 30, 40, false);
	}
	
	/*This one is from Observer*/
	@Override
	public void update(Observable o, Object arg){
		this.playerEnergy = ((SubjectTemplateMethod) o).getEnergy();
		
		if(this.playerEnergy <= 50 && !(this.powerStrategy instanceof WeakPower)){
			this.powerStrategy = new WeakPower();
			System.out.println("MOU ICHIDO!");
		}
		else if(this.playerEnergy > 50 && !(this.powerStrategy instanceof StrongPower)) {
			this.powerStrategy = new StrongPower();
			System.out.println("MOOOOU!");
		}
	}
	
	@Override
	public void executeSpecialAbility(int playerID){
		this.playerReleasedSpecialAbilityID = playerID;
	}
	
	public IPrototype pClone() {
		return new SoccerTeam();
	}
	
	public SoccerTeam(){
		this.player = new Player();
	}
	
	private void updatePlayer(){
		if(this.playerReleasedSpecialAbilityID > 0){
			//this.playersX[0] -= this.gameController.getDeltaTime()*300;
			//this.powerStrategy.executeSpecialAbility(this.playerReleasedSpecialAbilityID);
			//this.player.reset();
		}
		else{
			if(this.gameController.getLeftButtonPressed())
				this.playersX[0] -= this.gameController.getDeltaTime()*70;
			else if(this.gameController.getRightButtonPressed())
				this.playersX[0] += this.gameController.getDeltaTime()*70;
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
		
		this.gameController.setPlayersX(playersX);
		this.gameController.setPlayersY(playersY);
	}
	
	private void updateBots(){
		if(this.playerReleasedSpecialAbilityID >= 0){
			this.timer += this.gameController.getDeltaTime()*2;
			
			if(this.timer > 2){
				this.timer = 0;
				this.playerReleasedSpecialAbilityID = -1;
				this.player.reset();
			}
			/*
			for(int i = 1; i < playersX.length; ++i){
				if(i != this.playerReleasedSpecialAbilityID)
					this.playersX[i] += this.gameController.getDeltaTime()*300;
					this.playersY[i] += this.gameController.getDeltaTime()*400;
			}*/
			this.powerStrategy.executeSpecialAbility(this.playerReleasedSpecialAbilityID);
			playersX = this.powerStrategy.getPlayersX();
			playersY = this.powerStrategy.getPlayersY();
		}
		else{
			this.playersX[1] = this.ai.getX(this.gameController.getDeltaTime(), this.playersX[1], this.gameController.getBallX(), this.gameController.getBallY(), true);
			this.playersY[1] = this.ai.getY(this.gameController.getDeltaTime(), this.playersY[1], this.gameController.getBallX(), this.gameController.getBallY(), true);
		
			this.playersX[2] = this.ai.getX(this.gameController.getDeltaTime(), this.playersX[2], this.gameController.getBallX(), this.gameController.getBallY(), false);
			this.playersY[2] = this.ai.getY(this.gameController.getDeltaTime(), this.playersY[2], this.gameController.getBallX(), this.gameController.getBallY(), false);
		
			this.playersX[3] = this.ai.getX(this.gameController.getDeltaTime(), this.playersX[3], this.gameController.getBallX(), this.gameController.getBallY(), false);
			this.playersY[3] = this.ai.getY(this.gameController.getDeltaTime(), this.playersY[3], this.gameController.getBallX(), this.gameController.getBallY(), false);
		}
		
		this.gameController.setPlayersX(playersX);
		this.gameController.setPlayersY(playersY);
	}
	
	private IGameController gameController;
	private IGraphicController graphicController;
	private IPowerStrategy powerStrategy;
	
	private float []playersX, playersY;
	private float timer = 0;
	private int playerReleasedSpecialAbilityID = -1, playerEnergy;
	private boolean didLand;
}
