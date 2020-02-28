package sudokuSolver.gui;
import java.awt.EventQueue;
import javax.swing.*;

public class Popup_1 extends JFrame {
	
	public Popup_1() {
		initUI(); 
	}

	private void initUI() {
		setTitle("Simple Example");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame ex = new Popup_1(); 
			ex.setVisible(true);
		});
	}
}
