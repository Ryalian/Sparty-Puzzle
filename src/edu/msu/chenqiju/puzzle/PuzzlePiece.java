package edu.msu.chenqiju.puzzle;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class PuzzlePiece {
	/**
	 * THe image for the actual piece.
	 */
	private Bitmap piece;
	
	/**
	 * The puzzle piece ID
	 */
	private int id;
	
	/**
	 * x location. 
	 * We use relative x locations in the range 0-1 for the center
	 * of the puzzle piece.
	 */
	private float x = .5f;
	
	/**
	 * y location
	 */
	private float y = 0.5f;
	
	/**
	 * x location when the puzzle is solved
	 */
	private float finalX;
	
	/**
	 * y location when the puzzle is solved
	 */
	private float finalY;
    /**
     * We consider a piece to be in the right location if within
     * this distance.
     */
    final static float SNAP_DISTANCE = 0.05f;
	
	public PuzzlePiece(Context context, int id, float finalX, float finalY) {
		this.finalX = finalX;
		this.finalY = finalY;
		this.id = id;
		
		piece = BitmapFactory.decodeResource(context.getResources(), id);
	}

	/**
	 * Draw the puzzle piece
	 * @param canvas Canvas we are drawing on
	 * @param marginX Margin x value in pixels
	 * @param marginY Margin y value in pixels
	 * @param puzzleSize Size we draw the puzzle in pixels
	 * @param scaleFactor Amount we scale the puzzle pieces when we draw them
	 */
	public void draw(Canvas canvas, int marginX, int marginY, int puzzleSize, float scaleFactor) {
		canvas.save();
		
		// Convert x,y to pixels and add the margin, then draw
		canvas.translate(marginX + x * puzzleSize, marginY + y * puzzleSize);
		
		// Scale it to the right size
		canvas.scale(scaleFactor, scaleFactor);
		
		// This magic code makes the center of the piece at 0, 0
		canvas.translate(-piece.getWidth() / 2, -piece.getHeight() / 2);
		
		// Draw the bitmap
		canvas.drawBitmap(piece, 0, 0, null);
		canvas.restore();
	}
	
    /**
     * Test to see if we have touched a puzzle piece
     * @param testX X location as a normalized coordinate (0 to 1)
     * @param testY Y location as a normalized coordinate (0 to 1)
     * @param puzzleSize the size of the puzzle in pixels
     * @param scaleFactor the amount to scale a piece by
     * @return true if we hit the piece
     */
    public boolean hit(float testX, float testY, int puzzleSize, float scaleFactor) {
        // Make relative to the location and size to the piece size
        int pX = (int)((testX - x) * puzzleSize / scaleFactor) + piece.getWidth() / 2;
        int pY = (int)((testY - y) * puzzleSize / scaleFactor) + piece.getHeight() / 2;
        
        if(pX < 0 || pX >= piece.getWidth() ||
           pY < 0 || pY >= piece.getHeight()) {
            return false;
        }
        
        // We are within the rectangle of the piece.
        // Are we touching actual picture?
        return (piece.getPixel(pX, pY) & 0xff000000) != 0;
    }
    
    /**
     * If we are within SNAP_DISTANCE of the correct
     * answer, snap to the correct answer exactly.
     * @return
     */
    public boolean maybeSnap() {
        if(Math.abs(x - finalX) < SNAP_DISTANCE &&
                Math.abs(y - finalY) < SNAP_DISTANCE) {
            
            x = finalX;
            y = finalY;
            return true;
        }
        return false;
    }
    
    /**
     * Determine if this piece is snapped in place
     * @return true if snapped into place
     */
    public boolean isSnapped() {
         return maybeSnap();
    }
    /**
     * Move the puzzle piece by dx, dy
     * @param dx x amount to move
     * @param dy y amount to move
     */
    public void move(float dx, float dy) {
        x += dx;
        y += dy;
    }
	
	/**
	 * Shuffle the location of this piece
	 * @param rand A random number generator
	 */
	public void shuffle(Random rand) {
		x = rand.nextFloat();
		y = rand.nextFloat();
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getId(){
		return id;}
	public float getFinalX() {
	return finalX;
}
	public float getFinalY(){return finalY;}
}
