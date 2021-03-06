package com.lucythemoocher.actors.maincharacter.state;

import com.lucythemoocher.Globals.Globals;
import com.lucythemoocher.actors.PlayerCharacter;
import com.lucythemoocher.graphics.Animation;
import com.lucythemoocher.physics.Cinematic;
import com.lucythemoocher.util.Direction;

public class StateWallSliding extends State {

	private float begin_;

	public StateWallSliding(PlayerCharacter pc, Cinematic pos, Animation anim, int dir) {
		super(pc, pos, anim, dir);
		if ( dir_ == Direction.LEFT ) {
			int tab[] = {16,17,18,19};
			anim_.setAnimation(tab, ANIMATION_SPEED);
		} else {
			int tab[] = {12,13,14,15};
			anim_.setAnimation(tab, ANIMATION_SPEED);
		}
		begin_ = Globals.getInstance().getGame().getTime();
	}

	@Override
	public void update() {
		super.update();
		if ( Globals.getInstance().getGame().getTime() - begin_ > WALL_WALKING_PAUSE ) {
			pc_.changeState(new StateFalling(pc_, pos_, anim_, dir_));
		}

		if ( dir_ == Direction.LEFT ) {
			if ( pos_.hasLeftCollision() ) {
				pos_.stay();
			} else {
				pc_.changeState(new StateFalling(pc_, pos_, anim_, dir_));
			}
		} else {
			if ( pos_.hasRightCollision() ) {
				pos_.stay();
			} else {
				pc_.changeState(new StateFalling(pc_, pos_, anim_, dir_));
			}
		}
	}

	@Override
	public void moveUp() {
		if ( dir_ == Direction.LEFT ) {
			if ( pos_.hasLeftCollision() ) {
				pc_.changeState(new StateJumping(pc_, pos_, anim_, Direction.RIGHT));
			}
		} else {
			if ( pos_.hasRightCollision() ) {
				pc_.changeState(new StateJumping(pc_, pos_, anim_, Direction.LEFT));
			}
		}

	}

	@Override
	public void moveLeft() {
		if ( dir_ == Direction.RIGHT ) {
			if ( pos_.hasRightCollision() ) {
				pc_.changeState(new StateJumping(pc_, pos_, anim_, Direction.LEFT));
			}
		}
	}

	@Override
	public void moveRight() {
		if ( dir_ == Direction.LEFT ) {
			if ( pos_.hasLeftCollision() ) {
				pc_.changeState(new StateJumping(pc_, pos_, anim_, Direction.RIGHT));
			}
		}
	}
}
