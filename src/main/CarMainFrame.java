package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.dbutils.QueryRunner;

import bean.Car;
import dao.CarDao;
import main.CarAddAndUpdateFrame.AddFrame;
import main.CarAddAndUpdateFrame.UpdateFrame;
import utils.BeanUtils;
import utils.DataSourceUtils;
import utils.JMessageUtils;

public class CarMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	// 主窗口，用于小窗口用于刷新
	private final CarMainFrame carMainFrame;
	// 组件添加到此panel
	private JPanel contentPane;

	// 数据表格
	private JTable table;
	private String[] headers = { "id", "type", "make", "model", "seatingcapacity", "luggagecapacity", "mileage",
			"status", "price" };

	private List<Car> cars;

	/**
	 * Create the frame.
	 */
	public CarMainFrame() {
		carMainFrame = this;

		setTitle("CarManager");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		// 全屏
		// setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// 窗口居中
		setLocationRelativeTo(null);

		// 设置表格的滚动区域(决定数据区域的大小位置)
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 58, 900, 400);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton button = new JButton("List");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queryAll();
			}
		});
		button.setBounds(58, 22, 93, 23);
		contentPane.add(button);

		JButton button_1 = new JButton("Add");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarAddAndUpdateFrame carFrame = new CarAddAndUpdateFrame(carMainFrame);
				AddFrame addFrame = carFrame.new AddFrame();
				addFrame.setVisible(true);
			}
		});
		button_1.setBounds(205, 22, 93, 23);
		contentPane.add(button_1);

		JButton button_2 = new JButton("Update");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 没有选择行提示未选择数据
				Car selectedCar = getSelectedCar();
				if (selectedCar == null) {
					JMessageUtils.showMessage("no selected car!", "notice");
					return;
				}

				CarAddAndUpdateFrame carFrame = new CarAddAndUpdateFrame(carMainFrame);
				UpdateFrame addFrame = carFrame.new UpdateFrame(selectedCar);
				addFrame.setVisible(true);
				queryAll();
			}
		});
		button_2.setBounds(357, 22, 93, 23);
		contentPane.add(button_2);

		JButton button_3 = new JButton("Delete");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Car selectedCar = getSelectedCar();
				if (selectedCar == null) {
					JMessageUtils.showMessage("no selected car!", "notice");
					return;
				}

				boolean confirm = JMessageUtils.confirm("confirm delete,id: " + selectedCar.getId());
				if (confirm) {
					try {
						CarDao.getInstance().delete(selectedCar);
						queryAll();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		button_3.setBounds(539, 22, 93, 23);
		contentPane.add(button_3);

		// 显示数据列表
		queryAll();
	}

	// 查询
	public void queryAll() {
		try {
			QueryRunner queryRunner = DataSourceUtils.getQueryRunner();
			cars = CarDao.getInstance().list();
			table.setModel(new DefaultTableModel(BeanUtils.beans2TwoDimensionArray(cars, headers), headers));
		} catch (Exception e) {

		}
	}

	private Car getSelectedCar() {
		// 获取选择的行,-1表示没有选择
		int selectedRow = table.getSelectedRow();
		if (-1 == selectedRow) {
			return null;
		}

		return cars.get(selectedRow);
	}

	public void refresh() {
		queryAll();
	}

}