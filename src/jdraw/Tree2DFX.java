package jdraw;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Tree2DFX extends Application {

	// constants useful for javaFX and logic
	final int MAX_WIDTH = 800;
	final int MAX_HEIGHT = 800;

	///////////////////////////////////////////////////////////////////////////////////////////////
	// Java and JavaFX Graphics plumbing, not relevant to main logic
	///////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		launch(args);
	}

	// initialize graphics stuff
	Group root = new Group();
	Canvas canvas = new Canvas(MAX_WIDTH, MAX_HEIGHT);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	{
		gc.setFill(Color.RED);
		gc.setStroke(Color.GREEN);
		root.getChildren().add(canvas);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Drawing Recursive Tree");
		drawTree();
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// Main logic of Tree drawing
	///////////////////////////////////////////////////////////////////////////////////////////////

	// constants useful for logic
	final Point2D ORIGIN = new Point2D(MAX_WIDTH / 2, MAX_HEIGHT / 2);
	final double ANGLE_SCALE = 0.75;
	final double LENGTH_SCALE = 0.75;

	// draw a Tree by making initial call to drawBranch to start recursion chain
	void drawTree() {
		double initialAngle = Math.PI / 4;
		double initialLength = -100;
		int maxBranchDepth = 3;
		drawBranch(ORIGIN, 0, initialAngle, initialLength, maxBranchDepth);
	}

	// draw a branch starting at pos, pointing at direction dir, length of len,
	// then draw 2 branches left and right from there, by +/- turn from dir,
	// branching recursively up to depth level
	void drawBranch(Point2D pos, double dir, double turn, double len, int dep) {
		
		// if we've descended down to depth of 0 (or less) terminate recursion
		if (dep <= 0) return;

		// draw a line of length from pos at angle (0' is Up, +ve is right)
		// Use trigonometry to find next X, next Y

		Point2D nextPos = new Point2D(
			pos.getX() + len * Math.sin(dir),
			pos.getY() + len * Math.cos(dir)
		);

		System.out.println(dep + ": draw from "+pos+" to "+nextPos);

		gc.setLineWidth(1);
		gc.strokeLine(pos.getX(), pos.getY(), nextPos.getX(), nextPos.getY());

		//drawApple(pos, 2*dep);
        
		double nextDirLeft = dir - turn;
		double nextDirRight = dir + turn;
		double nextLength = len * LENGTH_SCALE;
		double nextTurn = turn * ANGLE_SCALE;

		drawBranch(nextPos, nextDirLeft, nextTurn, nextLength, dep - 1);
		drawBranch(nextPos, nextDirRight, nextTurn, nextLength, dep - 1);
	}

	// draw an apple at the position of radius
	void drawApple(Point2D pos, double radius) {
        gc.fillOval(pos.getX()-radius, pos.getY()-radius, 2*radius, 2*radius);
	}

}
