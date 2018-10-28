package main;

import java.math.BigInteger;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GeneratePI extends Application {
	private Canvas c;
	private GraphicsContext gc;
	private Random r = new Random();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane p = new BorderPane();
		Scene s = new Scene(p, 300, 150);
		stage.setTitle("PI berechnen");
		c = new Canvas(s.getWidth(), s.getHeight());
		gc = c.getGraphicsContext2D();
		
		p.setCenter(c);

		stage.setScene(s);
		stage.show();
		long i, dev = 0;
		long max = (long) Math.pow(10, 7);
		for (i = 0; i < max; i++) {
			long i1 = r.nextLong();
			long i2 = r.nextLong();
			
			if (gcd(i1, i2) == 1) {
				dev++;
			}
		}
		double prob = (double) dev / max;
		double pi = Math.sqrt(6 / prob);
		gc.clearRect(0, 0, c.getWidth(), c.getHeight());
		gc.fillText("Durchläufe: ", 10, 15);
		gc.fillText(max + "", 150, 15);
		
		gc.fillText("Coprime: ", 10, 30);
		gc.fillText(dev + "", 150, 30);
		
		gc.fillText("Wahrscheinlichkeit: ", 10, 45);
		gc.fillText(prob + "", 150, 45);
		
		gc.fillText("Pi geraten: ", 10, 60);
		gc.fillText(pi + "", 150, 60);
		
		gc.fillText("Pi: ", 10, 75);
		gc.fillText(Math.PI + "", 150, 75);
		
		gc.fillText("Gleiche Stellen: ", 10, 90);
		gc.fillText(gleich(Math.PI, pi), 150, 90);
	}
	
	private String gleich(double z1, double z2) {
		String s1 = String.valueOf(z1);
		String s2 = String.valueOf(z2);
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < s1.length() && s1.charAt(i) == s2.charAt(i); i++) {
			sb.append(s1.charAt(i));
		}
		return sb.toString();
	}
	
	private int gcd(long a, long b) {
	    BigInteger b1 = BigInteger.valueOf(a);
	    BigInteger b2 = BigInteger.valueOf(b);
	    BigInteger gcd = b1.gcd(b2);
	    return gcd.intValue();
	}
}