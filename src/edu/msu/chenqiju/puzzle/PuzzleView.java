package edu.msu.chenqiju.puzzle;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PuzzleView extends View {
	  @Override
	    public boolean onTouchEvent(MotionEvent event) {
	        return puzzle.onTouchEvent(this, event);
	    }

	/**
	 * Paint object we will use to draw a line
	 */
	private Puzzle puzzle;
	
	public PuzzleView(Context context) {
		super(context);
		init(context);
	}

	public PuzzleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public PuzzleView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	private void init(Context context){
		puzzle = new Puzzle(context);
		puzzle.setPuzzleView(this);
		
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		puzzle.draw(canvas);
	  	puzzle.drawComplete(this, canvas);
	}
	
	/**
	 * Save the puzzle to a bundle
	 * @param bundle The bundle we save to
	 */
	public void saveInstanceState(Bundle bundle) {
		puzzle.saveInstanceState(bundle);
	}
	
	/**
	 * Load the puzzle from a bundle
	 * @param bundle The bundle we save to
	 */
	public void loadInstanceState(Bundle bundle) {
		puzzle.loadInstanceState(bundle);
	}
	
    public Puzzle getPuzzle() {
        return puzzle;
    }
}
