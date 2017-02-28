package com.game.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.game.abstractClasses.SubjectTemplateMethod;
import com.game.interfaces.ICommand;
import com.game.interfaces.ICommandInvoker;

public class PlayerCommandInvoker implements ICommandInvoker, Observer {
	
	@Override
	public void execute(){
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			if(!this.listCommands.isEmpty()){
				this.listCommands.get(this.listCommands.size() - 1).action();
				
				if(!this.clearAll)
					this.listCommands.remove(this.listCommands.size() - 1);
				else {
					this.listCommands.clear();
					this.clearAll = false;
				}
			}	
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		this.playerEnergy = ((SubjectTemplateMethod) o).getEnergy();
		
		if(this.playerEnergy > 50)
			this.clearAll = true;
	}
	
	@Override
	public void addCommand(ICommand command){
		this.listCommands.add(command);
	}
	
	@Override
	public boolean hasCommand() {
		return !this.listCommands.isEmpty();
	}
	
	public PlayerCommandInvoker(){
		this.listCommands = new ArrayList<ICommand>();
	}
	
	private List<ICommand> listCommands;
	
	private int playerEnergy = 0;
	private boolean clearAll = false;
}
