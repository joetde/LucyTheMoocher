package com.lucythemoocher.loops;

import android.graphics.Color;

import com.lucythemoocher.Globals.Globals;

public class LivesMenuLoop extends Loop {

	private int lives_;
	private float currMs_;
	private Loop next_;
	private boolean drawn_ = false;
	private boolean init_ = false;
	
	/**
	 * Show "lives" lives for "msToLive" ms and then change to "next" loop
	 * @param lives
	 * @param msToLive
	 * @param next
	 */
	public LivesMenuLoop(int lives, float msToLive, Loop next) {
		super();
		lives_ = lives;
		currMs_ = msToLive;
		next_ = next;
	}
	
	@Override
	public void update() {
		currMs_ -= Globals.getInstance().getTimer().getDt();
		if (currMs_ <= 0 && init_) {
			changeCurrentLoop(next_);
			next_.start();
		}
		if (drawn_ && !init_) {
			next_.load();
			// advice garbage collection here, since we are in a load screen
			System.gc();
			init_ = true;
		}
	}

	@Override
	public void render() {
		if (!drawn_) {
			Globals.getInstance().getCamera().drawFullColor(0xFF00FFCC);
			Globals.getInstance().getCamera().drawCenterText("LIVES x"+lives_, Color.WHITE);
			drawn_ = true;
		}
	}

}