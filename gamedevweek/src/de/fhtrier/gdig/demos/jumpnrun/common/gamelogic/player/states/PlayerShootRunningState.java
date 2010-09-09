package de.fhtrier.gdig.demos.jumpnrun.common.gamelogic.player.states;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import de.fhtrier.gdig.demos.jumpnrun.common.gamelogic.player.Player;
import de.fhtrier.gdig.demos.jumpnrun.common.gamelogic.player.states.identifiers.PlayerActions;
import de.fhtrier.gdig.demos.jumpnrun.identifiers.Assets;
import de.fhtrier.gdig.demos.jumpnrun.identifiers.Constants;
import de.fhtrier.gdig.demos.jumpnrun.identifiers.EntityOrder;
import de.fhtrier.gdig.engine.gamelogic.Entity;
import de.fhtrier.gdig.engine.graphics.entities.AssetEntity;
import de.fhtrier.gdig.engine.management.Factory;

public class PlayerShootRunningState extends PlayerAssetState {
	private Animation anim;

	public PlayerShootRunningState(Player player, Factory factory)
			throws SlickException {
		super(player, Assets.PlayerShootRunningAnimId,
				Assets.PlayerShootRunningImagePath, EntityOrder.Player, factory);
	
		AssetEntity e = getGfxEntity();
		
		anim = e.Assets().getAnimation(e.getAssetId());
		anim.setLooping(false);
	}

	@Override
	public void enter() {
		if (anim.isStopped()) {
			anim.restart();
		}
		}

	@Override
	public void leave() {
	}

	@Override
	public void update() {
	
		// check if vel < threshold --> stop running
		if (Math.abs(getPlayer().getVel()[Entity.X]) < Constants.GamePlayConstants.playerIdleTriggerSpeed) {
			getPlayer().applyAction(PlayerActions.StopRunning);
		}
		
		// check if currentPos < prevPos --> start falling
		if (getPlayer().getVel()[Entity.Y] > Constants.GamePlayConstants.playerFallingTriggerSpeed) {
			getPlayer().applyAction(PlayerActions.Fall);
			System.out.println("State: FallShooting");
		}
		
		if (anim.isStopped()) {
			getPlayer().applyAction(PlayerActions.StopShooting);
		}
	}
}
