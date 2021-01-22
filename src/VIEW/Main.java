package VIEW;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 898, 506);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("H\u1EC7 th\u1ED1ng");
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("H\u1EC7 th\u1ED1ng");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Qu\u1EA3n l\u00FD");
		menuBar.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("Th\u1ED1ng k\u00EA");
		menuBar.add(mnNewMenu_2);
		
		JMenu mnNewMenu_3 = new JMenu("Tr\u1EE3 gi\u00FAp");
		menuBar.add(mnNewMenu_3);
		frame.getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBounds(732, 296, -663, -109);
		frame.getContentPane().add(table);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setToolTipText("sssssssssssss");
		toolBar.setBounds(0, 0, 882, 57);
		frame.getContentPane().add(toolBar);
		
		JButton btnNewButton_1 = new JButton("\u0110\u0103ng xu\u1EA5t");
		btnNewButton_1.setAutoscrolls(true);
		btnNewButton_1.setVerifyInputWhenFocusTarget(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		toolBar.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("K\u1EBFt th\u00FAc");
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Chuy\u00EAn \u0111\u1EC1");
		toolBar.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Ng\u01B0\u1EDDi h\u1ECDc");
		toolBar.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Kh\u00F3a h\u1ECDc");
		toolBar.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("H\u1ECDc vi\u00EAn");
		toolBar.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("K\u1EBFt th\u00FAc");
		toolBar.add(btnNewButton_6);
	}
}
