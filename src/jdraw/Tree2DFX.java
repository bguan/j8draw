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
	final Point2D ORIGIN = new Point2D(MAX_WIDTH / 2, .9 * MAX_HEIGHT);
	final double ANGLE_SCALE = 1;
	final double LENGTH_SCALE = 0.88;

	// draw a Tree by making initial call to drawBranch to start recursion chain
	void drawTree() {
		double initAngL = 30 * Math.PI / 180;
		double initAngR = 24 * Math.PI / 180;
		double initLen = -100;
		int maxBranchDepth = 14;
		drawBranch(ORIGIN, 0, initAngL, initAngR, initLen, maxBranchDepth);
	}

	// draw a branch starting at pos, pointing at direction dir, length of len,
	// then draw 2 branches left and right from there, by +/- turn from dir,
	// branching recursively up to depth level
	void drawBranch(Point2D pos, double dir, double turnL, double turnR, double len, int dep) {

		// if we've descended down to depth of 0 (or less) terminate recursion
		if (dep <= 0)
			return;

		// draw a line of length from pos at angle (0' is Up, +ve is right)
		// Use trigonometry to find next X, next Y

		Point2D nextPos = new Point2D(pos.getX() + len * Math.sin(dir), pos.getY() + len * Math.cos(dir));

		System.out.println(dep + ": draw from " + pos + " to " + nextPos);

		gc.setLineWidth(dep);
		gc.setStroke(Color.rgb(0, Math.max(10, 255 - 20*dep), 0));
		gc.strokeLine(pos.getX(), pos.getY(), nextPos.getX(), nextPos.getY());

		if (dep%3==1 && Math.random() > .99) 
			drawApple(nextPos, dep+2);

		double nextDirLeft = dir - turnL;
		double nextDirRight = dir + turnR;
		double nextLength = len * LENGTH_SCALE * (.75 + Math.random()/4);
		double nextTurnL = turnL * ANGLE_SCALE * (.75 + Math.random()/4);
		double nextTurnR = turnR * ANGLE_SCALE * (.75 + Math.random()/4);

		drawBranch(nextPos, nextDirLeft, nextTurnL, nextTurnR, nextLength, dep - 1);
		drawBranch(nextPos, nextDirRight, nextTurnL, nextTurnR, nextLength, dep - 1);
	}

	// draw an apple at the position of radius
	void drawApple(Point2D pos, double radius) {
		gc.setLineWidth(1);
		gc.setStroke(Color.BROWN);
		gc.arcTo(pos.getX(), pos.getY(), pos.getX()+radius, pos.getY()+radius, 4*radius);
		gc.setFill(Color.rgb((255 - 3*(int)radius),0,0));
		gc.fillOval(pos.getX(), pos.getY(), 2 * radius, 2 * radius);
	}

}
