package edu.msu.chenqiju.puzzle;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class PuzzleActivity extends Activity {
	
	/**
	 * The puzzle view in this activity's view
	 */
	private PuzzleView puzzleView;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_puzzle);
		
		puzzleView = (PuzzleView)this.findViewById(R.id.puzzleView);
		
		if(bundle != null) {
			// We have saved state
			puzzleView.loadInstanceState(bundle);
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.puzzle, menu);
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
        case R.id.menu_shuffle:
            puzzleView.getPuzzle().shuffle();
            puzzleView.invalidate();
            return true;
            
        default:
		return super.onMenuItemSelected(featureId, item);
        }
	}

	@Override
	protected void onSaveInstanceState(Bundle bundle) {
		super.onSaveInstanceState(bundle);
		
		puzzleView.saveInstanceState(bundle);
	}
	
	

}
