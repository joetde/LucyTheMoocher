package com.lucythemoocher.actors;

import com.lucythemoocher.R;
import com.lucythemoocher.controls.AIController;
import com.lucythemoocher.physics.Cinematic;
import com.lucythemoocher.util.Direction;

/**
 * Target of the game.
 * I.e. Freddy!!!
 */
public class TargetCharacter extends Actor {
	/**
	 * Constructor.
	 * Provided controller expresses the behavior of Freddy
	 * @param controller AI controller
	 * @param x
	 * @param y
	 */
	public TargetCharacter(AIController controller, float x, float y) {
		super();
		getDrawer().initializeAnimation(R.drawable.freddy);
		setCinematic(new Cinematic(0.6f));
		getCinematic().addBox(x, y, getH(), getW());
		controller_ = controller;
		controller_.setControllable(this);
		
		int tab[] = {0,1,2,3};
		getDrawer().getAnim().setAnimation(tab, 100);
		dir_ = Direction.RIGHT;
	}
	
	public void update() {
		super.update();
		if (dir_ == Direction.LEFT) {
			moveLeft();
			if ( pos_.hasLeftCollision() ) {
				dir_ = Direction.RIGHT;
				int tab[] = {0,1,2,3};
				getDrawer().getAnim().setAnimation(tab, 100);
			}
		} else {
			moveRight();
			if ( pos_.hasRightCollision() ) {
				dir_ = Direction.LEFT;
				int tab[] = {4,5,6,7};
				getDrawer().getAnim().setAnimation(tab, 100);
			}
		}
	}
	
	private AIController controller_;
	private int dir_;
}
