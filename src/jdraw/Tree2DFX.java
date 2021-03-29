package jdraw;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
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
	final double ANGLE_SCALE = 0.8;
	final double LENGTH_SCALE = 0.8;

	// draw a Tree by making initial call to drawBranch to start recursion chain
	void drawTree() {
		double initAngL = 36 * Math.PI / 180;
		double initAngR = 24 * Math.PI / 180;
		double initLen = -100;
		int maxBranchDepth = 6;
		drawBranch(ORIGIN, 0, initAngL, initAngR, initLen, maxBranchDepth);
	}

	double weightedRandom(double weight) {
		return (1.0 - weight/2) + Math.random()*weight;
	}
	
	// draw a branch starting at pos, pointing at direction dir, length of len,
	// 2 branches from there by turning left and right by resp. angles turnL & turnR,
	// each time adjusting branch length and angles by a scaling factor incl. some random 
	// noise, branching recursively up to dep level
	void drawBranch(Point2D pos, double dirM, double dirL, double dirR, double len, double dep) {

		// if we've descended down to depth of 0 (or less) terminate recursion
		if (dep <= 0)
			return;

		// draw a line of length from pos at angle (0' is Up, +ve is right)
		// Use trigonometry to find next X, next Y

		Point2D nextPos = new Point2D(pos.getX() + len * Math.sin(dirM), pos.getY() + len * Math.cos(dirM));

		System.out.println(dep + ": draw from " + pos + " to " + nextPos);

		gc.setLineWidth(dep);
		gc.setStroke(Color.rgb(0, Math.max(10, 255 - 20*(int)dep), 0));
		gc.setLineCap(StrokeLineCap.ROUND);
		gc.strokeLine(pos.getX(), pos.getY(), nextPos.getX(), nextPos.getY());

		if (Math.random() > .99) 
			drawApple(nextPos, dep+2);

		double nextDirL = dirM - dirL;
		double nextDirR = dirM + dirR;
		double nextDirM = (nextDirL+nextDirR)* weightedRandom(.2)/2;
		double nextLenL = len * LENGTH_SCALE * weightedRandom(.2);
		double nextLenR = len * LENGTH_SCALE * weightedRandom(.2);
		double nextLenM = len * Math.sqrt(LENGTH_SCALE)* weightedRandom(.1);
		double nextTurnL = dirL * ANGLE_SCALE * weightedRandom(.2);
		double nextTurnR = dirR * ANGLE_SCALE * weightedRandom(.2);

		drawBranch(nextPos, nextDirL, nextTurnL, nextTurnR, nextLenL, dep - weightedRandom(.5));
		drawBranch(nextPos, nextDirR, nextTurnL, nextTurnR, nextLenR, dep - weightedRandom(.5));
		drawBranch(nextPos, nextDirM, nextTurnL, nextTurnR, nextLenM, dep - weightedRandom(.5)/2);
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
