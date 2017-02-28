package com.game.command;

import com.game.abstractClasses.ITeam;
import com.game.interfaces.ICommand;

public class ForceAbilityCommand implements ICommand {

	/*For now, only the Player 1 can execute special ability.*/
	@Override
	public void action() {
		this.team.executeSpecialAbility(0);
	}
	
	public ForceAbilityCommand(ITeam team){
		this.team = team;
	}
	
	private ITeam team;
}
