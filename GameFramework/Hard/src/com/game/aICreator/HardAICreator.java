package com.game.aICreator;

import com.game.abstractClasses.AICreator;
import com.game.artificialIntelligence.ArtificialIntelligence;
import com.game.interfaces.IArtificialIntelligence;

public class HardAICreator extends AICreator {
	
	@Override
	public IArtificialIntelligence createAI() {
		return new ArtificialIntelligence();
	}
}
