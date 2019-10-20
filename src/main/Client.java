package main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Client extends JFrame {

	public Client() {
		setTitle("Manager");
		//	EXIT_ON_CLOSE - 关闭窗口结束进程;DISPOSE_ON_CLOSE关闭窗口销毁窗口
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 全屏
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// 窗口居中
		setLocationRelativeTo(null);

		JButton button = new JButton("DriverManager");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DriverMainFrame().setVisible(true);
			}
		});
		button.setBounds(200, 100, 400, 30);
		contentPane.add(button);

		JButton catBtn = new JButton("CarManager");
		catBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CarMainFrame().setVisible(true);
			}
		});
		catBtn.setBounds(200, 200, 400, 30);
		contentPane.add(catBtn);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
