package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
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
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import bean.Driver;
import dao.DriverDao;
import utils.BeanUtils;
import utils.JMessageUtils;

public class DriverAddAndUpdateFrame {

	// 标记属于哪个主窗口
	private DriverMainFrame driverMainFrame;

	public DriverAddAndUpdateFrame(DriverMainFrame driverMainFrame) {
		super();
		this.driverMainFrame = driverMainFrame;
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

		private String frameTitle = "AddDriver";
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
			createJLabel("firstname", 40, 60, jLabelWidth, jLabelheight);
			createJLabel("lastname", 40, 90, jLabelWidth, jLabelheight);
			createJLabel("birthdate", 40, 120, jLabelWidth, jLabelheight);
			createJLabel("licencenumber", 40, 150, jLabelWidth, jLabelheight);
			createJLabel("licencestate", 40, 180, jLabelWidth, jLabelheight);

			createJText("firstname", 240, 60, jTextFieldWidth, jTextFieldHeight);
			createJText("lastname", 240, 90, jTextFieldWidth, jTextFieldHeight);
			createJText("birthdate", 240, 120, jTextFieldWidth, jTextFieldHeight);
			createJText("licencenumber", 240, 150, jTextFieldWidth, jTextFieldHeight);
			createJText("licencestate", 240, 180, jTextFieldWidth, jTextFieldHeight);

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

						if (ArrayUtils.contains(Driver.doubleKeys, key)) {
							properties.put(key, Double.valueOf(text));
						} else if (ArrayUtils.contains(Driver.dateKeys, key)) {
							if (StringUtils.isNotBlank(text)) {
								try {
									Date date = DateUtils.parseDate(text, "yyyy-MM-dd", "yyyy/MM/dd");
									properties.put(key, date);
								} catch (ParseException e) {
									e.printStackTrace();
									properties.put(key, new Date());
								}
							}
						} else {
							properties.put(key, text);
						}
					}

					try {
						Driver map2Bean = BeanUtils.map2Bean(properties, Driver.class);
						DriverDao.getInstance().add(map2Bean);
						JMessageUtils.showMessage("add success!");
						dispose();
						driverMainFrame.refresh();
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
		private Driver driver;

		private static final long serialVersionUID = 1L;

		private String frameTitle = "UpdateDriver";

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

			if (driver != null) {
				Object property = BeanUtils.getProperty(driver, name);
				if (property != null) {
					// 日期字段回显做处理
					if (property != null && property instanceof Date) {
						property = DateFormatUtils.format((Date) property, "yyyy-MM-dd");
					}

					textField.setText(property.toString());
				}

			}

			// 放到Map用于最后获取值
			textFields.put(name, textField);

			if (contentPane != null) {
				contentPane.add(textField);
			}
		}

		public UpdateFrame(final Driver driver) {
			this.driver = driver;

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
			createJLabel("firstname", 40, 60, jLabelWidth, jLabelheight);
			createJLabel("lastname", 40, 90, jLabelWidth, jLabelheight);
			createJLabel("birthdate", 40, 120, jLabelWidth, jLabelheight);
			createJLabel("licencenumber", 40, 150, jLabelWidth, jLabelheight);
			createJLabel("licencestate", 40, 180, jLabelWidth, jLabelheight);

			createJText("firstname", 240, 60, jTextFieldWidth, jTextFieldHeight);
			createJText("lastname", 240, 90, jTextFieldWidth, jTextFieldHeight);
			createJText("birthdate", 240, 120, jTextFieldWidth, jTextFieldHeight);
			createJText("licencenumber", 240, 150, jTextFieldWidth, jTextFieldHeight);
			createJText("licencestate", 240, 180, jTextFieldWidth, jTextFieldHeight);

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

						if (ArrayUtils.contains(Driver.doubleKeys, key)) {
							properties.put(key, Double.valueOf(text));
						} else if (ArrayUtils.contains(Driver.dateKeys, key)) {
							if (StringUtils.isNotBlank(text)) {
								try {
									Date date = DateUtils.parseDate(text, "yyyy-MM-dd", "yyyy/MM/dd");
									properties.put(key, date);
								} catch (ParseException e) {
									e.printStackTrace();
									properties.put(key, new Date());
								}
							}
						} else {
							properties.put(key, text);
						}
					}

					try {
						Driver map2Bean = BeanUtils.map2Bean(properties, Driver.class);
						map2Bean.setId(driver.getId());
						DriverDao.getInstance().update(map2Bean);
						JMessageUtils.showMessage("update success!");
						dispose();

						// 主窗口刷新
						driverMainFrame.refresh();
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