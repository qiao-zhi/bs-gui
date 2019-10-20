package utils;

import javax.swing.JOptionPane;

/**
 * SWing消息框
 */
public class JMessageUtils {

	public static void showMessage(String message) {
		showMessage(message, null);
	}

	/**
	 * 消息框
	 * 
	 * @param message
	 *            消息
	 * @param title
	 *            标题
	 */
	public static void showMessage(String message, String title) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
	}

	public static boolean confirm(String message) {
		/**
		 * 确认框 0-是； 1-否； 2-取消
		 */
		int showConfirmDialog = JOptionPane.showConfirmDialog(null, message);
		return showConfirmDialog == 0;
	}
}
