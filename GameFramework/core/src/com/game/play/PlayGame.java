/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.game.command.PlayerCommandInvoker;
import com.game.command.ForceAbilityCommand;
import com.game.controller.GraphicController;
import com.game.core.Core;
import com.game.interfaces.IArtificialIntelligence;
import com.game.interfaces.IBall;
import com.game.interfaces.IField;
import com.game.interfaces.IFlexibleFactory;
import com.game.interfaces.IGameController;
import com.game.interfaces.IGraphicController;
import com.game.interfaces.IRules;
import com.game.interfaces.ISpecialItem;
import com.game.utils.Background;
import com.game.utils.DateTime;
import com.game.utils.PlayerEnergyBar;
import com.game.abstractClasses.ITeam;
import com.game.abstractClasses.SubjectTemplateMethod;

/**
 *
 * @author Amilton
 */
public class PlayGame implements Screen {

	@Override
	public void show() {
	}

	@Override
	public void render(float f) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.gameController.setDeltaTime(Gdx.graphics.getDeltaTime());

		this.background.draw();

		this.batch.begin();
		this.font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		this.font.draw(batch,
				String.valueOf(this.gameController.getPlayerScore()), 200, 200);
		this.font.draw(batch, " : ", 300, 200);
		this.font.draw(batch,
				String.valueOf(this.gameController.getEnemyScore()), 400, 200);
		this.font
				.draw(batch,
						String.valueOf(DateTime.getInstance().getTimeString()),
						50, 300);
		this.batch.end();

		this.field.showField();

		if (this.player != null)
			this.playerEnergyBar.draw();

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			this.gameController.setLeftButtonPressed(true);

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			this.gameController.setRightButtonPressed(true);

		if (Gdx.input.isKeyPressed(Input.Keys.UP))
			this.gameController.setUpButtonPressed(true);

		this.team.updateTeam();

		this.rules.verifyCollision();

		if (this.specialItem != null) {
			this.specialItem.draw();
			if (this.gameController.getSpecialItemCollided()) {
				this.commandInvoker.addCommand(new ForceAbilityCommand(
						this.team));
				this.gameController.setSpecialItemCollided(false);
				this.player.updateEnergy();
			}
			this.commandInvoker.execute();
			if (this.commandInvoker.hasCommand()) {
				this.batch.begin();
				this.font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
				this.font.draw(batch, "POWER ENABLED! PRESS SPACE", 250, 250);
				this.batch.end();
			}
		}

		this.playerScore = this.gameController.getPlayerScore();
		this.enemyScore = this.gameController.getEnemyScore();
		this.rules.verifyPlayerPoint();
		this.rules.verifyEnemyPoint();

		if (this.playerScore != this.gameController.getPlayerScore()
				|| this.enemyScore != this.gameController.getEnemyScore()) {
			this.team.init();
			this.ball.init();
		}

		this.ball.update();

		this.rules.verifyGameOver();

		this.gameController.setLeftButtonPressed(false);
		this.gameController.setRightButtonPressed(false);
		this.gameController.setUpButtonPressed(false);

		if (this.gameController.getGameOver()) {
			this.gameOver = GameOver.getInstance();
			this.gameOver.setScore(this.gameController.getPlayerScore(),
					this.gameController.getEnemyScore());
			Core.getInstance().setScreen(this.gameOver);
			this.dispose();
		}
	}

	@Override
	public void resize(int i, int i1) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		this.ball = null;
		this.team = null;
		this.rules = null;
		this.field = null;
		this.specialItem = null;
		this.player = null;
		this.playerEnergyBar = null;

		this.flexibleFactory.removePrototype("Field");
		this.flexibleFactory.removePrototype("Ball");
		this.flexibleFactory.removePrototype("Team");
		this.flexibleFactory.removePrototype("Rules");
		this.flexibleFactory.removePrototype("SpecialItem");
		this.flexibleFactory = null;

		this.batch.dispose();
		// this.stage.dispose();
		this.font.dispose();

		this.gameController.reset();
	}

	public void init(IFlexibleFactory flexibleFactory, IArtificialIntelligence artificialIntelligence){
      	this.gameController = Core.getInstance().getGameController();
    	
    	this.flexibleFactory = flexibleFactory;
    	
    	this.flexibleFactory.setCore(Core.getInstance());
        this.flexibleFactory.addPrototype("Field");
        this.flexibleFactory.addPrototype("Ball");
        this.flexibleFactory.addPrototype("Team");
        this.flexibleFactory.addPrototype("Rules");
        
        if(this.flexibleFactory.addPrototype("SpecialItem")){
        	this.commandInvoker = new PlayerCommandInvoker();
        	this.specialItem = (ISpecialItem)flexibleFactory.create("SpecialItem");
        }
        
    	
    	this.field = (IField)flexibleFactory.create("Field");
        this.ball = (IBall)flexibleFactory.create("Ball");
        this.team = (ITeam)flexibleFactory.create("Team");
        this.team.setAI(artificialIntelligence);
        this.rules = (IRules)flexibleFactory.create("Rules");
        this.player = this.team.getSubject();
        
        this.field.init();
        this.ball.init();
        this.team.init();
        this.rules.init();
        
        this.playerScore = 0;
        this.enemyScore = 0;
        
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        
        this.background = new Background();
        
        if(this.player != null) {
        	this.playerEnergyBar = new PlayerEnergyBar();
        	this.player.addObserver(this.playerEnergyBar);
        	this.player.addObserver(this.team);
        	this.player.addObserver(this.background);
        	
        	if(this.commandInvoker != null)
        		this.player.addObserver(this.commandInvoker);
        }
    }

	public static PlayGame getInstance() {
		if (instance == null)
			instance = new PlayGame();
		return instance;
	}

	private PlayGame() {
	}

	private static PlayGame instance;

	private IFlexibleFactory flexibleFactory;
	private IField field;
	private IBall ball;
	private ITeam team;
	private IRules rules;
	private GameOver gameOver;
	private IGameController gameController;
	private Background background;
	private PlayerEnergyBar playerEnergyBar;
	private ISpecialItem specialItem;
	private PlayerCommandInvoker commandInvoker;
	private SubjectTemplateMethod player;

	private BitmapFont font;
	private SpriteBatch batch;

	private int playerScore, enemyScore;
}