package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import bean.Car;
import dao.CarDao;
import utils.BeanUtils;
import utils.JMessageUtils;

public class CarAddAndUpdateFrame {

	// 标记属于哪个主窗口
	private CarMainFrame carMainFrame;

	public CarAddAndUpdateFrame(CarMainFrame carMainFrame) {
		super();
		this.carMainFrame = carMainFrame;
	}

	private int jLabelWidth = 200;
	private int jLabelheight = 30;

	private int jTextFieldWidth = 200;
	private int jTextFieldHeight = 30;

	/**
	 * Add Frame(Inner Class)
	 */
	public class AddFrame extends JFrame {

		private static final long serialVersionUID = 1L;

		private String frameTitle = "AddCar";
		private JPanel contentPane;

		/**
		 * key-输入框的name(用于最后获取值)
		 */
		private Map<String, JTextField> textFields = new LinkedHashMap<>();

		public void createJLabel(String text, int x, int y, int width, int height) {
			JLabel label = new JLabel(text);
			label.setFont(new Font("宋体", Font.PLAIN, 17));
			label.setBounds(x, y, width, height);

			if (contentPane != null) {
				contentPane.add(label);
			}
		}

		public void createJText(String name, int x, int y, int width, int height) {
			JTextField textField = new JTextField();
			textField.setBounds(x, y, width, height);

			// 放到Map用于最后获取值
			textFields.put(name, textField);

			if (contentPane != null) {
				contentPane.add(textField);
			}
		}

		public AddFrame() {
			// 调用dispose方法关闭与设置界限
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 600, 400);
			setTitle(frameTitle);
			// 窗口居中
			setLocationRelativeTo(null);

			// 创建JPanel
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			createJLabel(frameTitle, 118, 20, jLabelWidth, 39);
			createJLabel("type", 40, 60, jLabelWidth, jLabelheight);
			createJLabel("make", 40, 90, jLabelWidth, jLabelheight);
			createJLabel("model", 40, 120, jLabelWidth, jLabelheight);
			createJLabel("seatingcapacity", 40, 150, jLabelWidth, jLabelheight);
			createJLabel("luggagecapacity", 40, 180, jLabelWidth, jLabelheight);
			createJLabel("mileage", 40, 210, jLabelWidth, jLabelheight);
			createJLabel("status", 40, 240, jLabelWidth, jLabelheight);
			createJLabel("price", 40, 270, jLabelWidth, jLabelheight);

			createJText("type", 240, 60, jTextFieldWidth, jTextFieldHeight);
			createJText("make", 240, 90, jTextFieldWidth, jTextFieldHeight);
			createJText("model", 240, 120, jTextFieldWidth, jTextFieldHeight);
			createJText("seatingcapacity", 240, 150, jTextFieldWidth, jTextFieldHeight);
			createJText("luggagecapacity", 240, 180, jTextFieldWidth, jTextFieldHeight);
			createJText("mileage", 240, 210, jTextFieldWidth, jTextFieldHeight);
			createJText("status", 240, 240, jTextFieldWidth, jTextFieldHeight);
			createJText("price", 240, 270, jTextFieldWidth, jTextFieldHeight);

			// 两个按钮
			JButton button = new JButton("doAdd");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					doAddCar();
				}

				// 执行存库操作
				private void doAddCar() {
					Map<String, Object> properties = new LinkedHashMap<>();

					Set<Entry<String, JTextField>> entrySet = textFields.entrySet();

					for (Entry<String, JTextField> entry : entrySet) {
						String key = entry.getKey();
						JTextField value = entry.getValue();
						String text = value.getText();
						if (StringUtils.isBlank(text)) {
							JMessageUtils.showMessage("has field not set!");
							return;
						}

						if (ArrayUtils.contains(Car.doubleKeys, key)) {
							properties.put(key, Double.valueOf(text));
						} else if (ArrayUtils.contains(Car.floatKeys, key)) {
							properties.put(key, Float.valueOf(text));
						} else if (ArrayUtils.contains(Car.intKeys, key)) {
							properties.put(key, Integer.valueOf(text));
						} else {
							properties.put(key, text);
						}
					}

					try {
						Car map2Bean = BeanUtils.map2Bean(properties, Car.class);
						CarDao.getInstance().add(map2Bean);
						JMessageUtils.showMessage("add success!");
						dispose();
						carMainFrame.refresh();
					} catch (Exception e) {
						JMessageUtils.showMessage("add failed!");
						e.printStackTrace();
					}

				}
			});
			button.setBounds(37, 325, 93, 23);
			contentPane.add(button);

			JButton button_1 = new JButton("doBack");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// 退出
					dispose();
				}
			});
			button_1.setBounds(169, 325, 93, 23);
			contentPane.add(button_1);
		}
	}

	/**
	 * Update Frame(InnerClass)
	 */
	public class UpdateFrame extends JFrame {

		// 被修改的car
		private Car car;

		private static final long serialVersionUID = 1L;

		private String frameTitle = "UpdateCar";

		private JPanel contentPane;

		/**
		 * key-输入框的name(用于最后获取值)
		 */
		private Map<String, JTextField> textFields = new LinkedHashMap<>();

		public void createJLabel(String text, int x, int y, int width, int height) {
			JLabel label = new JLabel(text);
			label.setFont(new Font("宋体", Font.PLAIN, 17));
			label.setBounds(x, y, width, height);

			if (contentPane != null) {
				contentPane.add(label);
			}
		}

		public void createJText(String name, int x, int y, int width, int height) {
			JTextField textField = new JTextField();
			textField.setBounds(x, y, width, height);

			if (car != null) {
				Object property = BeanUtils.getProperty(car, name);
				if (property != null) {
					textField.setText(property.toString());
				}
			}

			// 放到Map用于最后获取值
			textFields.put(name, textField);

			if (contentPane != null) {
				contentPane.add(textField);
			}
		}

		public UpdateFrame(final Car car) {
			this.car = car;

			// 调用dispose方法关闭与设置界限
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 600, 400);
			setTitle(frameTitle);
			// 窗口居中
			setLocationRelativeTo(null);

			// 创建JPanel
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);

			createJLabel(frameTitle, 118, 20, jLabelWidth, 39);
			createJLabel("type", 40, 60, jLabelWidth, jLabelheight);
			createJLabel("make", 40, 90, jLabelWidth, jLabelheight);
			createJLabel("model", 40, 120, jLabelWidth, jLabelheight);
			createJLabel("seatingcapacity", 40, 150, jLabelWidth, jLabelheight);
			createJLabel("luggagecapacity", 40, 180, jLabelWidth, jLabelheight);
			createJLabel("mileage", 40, 210, jLabelWidth, jLabelheight);
			createJLabel("status", 40, 240, jLabelWidth, jLabelheight);
			createJLabel("price", 40, 270, jLabelWidth, jLabelheight);

			createJText("type", 240, 60, jTextFieldWidth, jTextFieldHeight);
			createJText("make", 240, 90, jTextFieldWidth, jTextFieldHeight);
			createJText("model", 240, 120, jTextFieldWidth, jTextFieldHeight);
			createJText("seatingcapacity", 240, 150, jTextFieldWidth, jTextFieldHeight);
			createJText("luggagecapacity", 240, 180, jTextFieldWidth, jTextFieldHeight);
			createJText("mileage", 240, 210, jTextFieldWidth, jTextFieldHeight);
			createJText("status", 240, 240, jTextFieldWidth, jTextFieldHeight);
			createJText("price", 240, 270, jTextFieldWidth, jTextFieldHeight);

			// 两个按钮
			JButton button = new JButton("doUpdate");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					doUpdateCar();
				}

				// 执行存库操作
				private void doUpdateCar() {
					Map<String, Object> properties = new LinkedHashMap<>();

					Set<Entry<String, JTextField>> entrySet = textFields.entrySet();

					for (Entry<String, JTextField> entry : entrySet) {
						String key = entry.getKey();
						JTextField value = entry.getValue();
						String text = value.getText();
						if (StringUtils.isBlank(text)) {
							JMessageUtils.showMessage("has field not set!");
							return;
						}

						if (ArrayUtils.contains(Car.doubleKeys, key)) {
							properties.put(key, Double.valueOf(text));
						} else if (ArrayUtils.contains(Car.floatKeys, key)) {
							properties.put(key, Float.valueOf(text));
						} else if (ArrayUtils.contains(Car.intKeys, key)) {
							properties.put(key, Integer.valueOf(text));
						} else {
							properties.put(key, text);
						}
					}

					try {
						Car map2Bean = BeanUtils.map2Bean(properties, Car.class);
						map2Bean.setId(car.getId());
						CarDao.getInstance().update(map2Bean);
						JMessageUtils.showMessage("update success!");
						dispose();

						// 主窗口刷新
						carMainFrame.refresh();
					} catch (Exception e) {
						JMessageUtils.showMessage("update failed!");
						e.printStackTrace();
					}

				}
			});
			button.setBounds(37, 325, 93, 23);
			contentPane.add(button);

			JButton button_1 = new JButton("doBack");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// 退出
					dispose();
				}
			});
			button_1.setBounds(169, 325, 93, 23);
			contentPane.add(button_1);
		}
	}
}