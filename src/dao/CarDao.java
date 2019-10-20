package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.Car;
import utils.DataSourceUtils;

public class CarDao {

	private static CarDao carDao = new CarDao();

	public static CarDao getInstance() {
		return carDao;
	}

	private CarDao() {

	}

	private QueryRunner queryRunner = DataSourceUtils.getQueryRunner();

	public void add(Car car) throws SQLException {
		// 没传ID
		if (car.getId() != null && car.getId() == 0) {
			String sql = "INSERT INTO `car`(`id`,`luggagecapacity`,`make`,`mileage`,`model`,`price`,`seatingcapacity`,`status`,`type`) VALUES ( NULL, ?, ?, ?, ?, ?, ?, ?, ?)";
			queryRunner.update(sql, car.getLuggagecapacity(), car.getMake(), car.getMileage(), car.getModel(),
					car.getPrice(), car.getSeatingcapacity(), car.getStatus(), car.getType());
			return;
		}

		// 传ID
		String sql = "INSERT INTO `car`(`id`,`luggagecapacity`,`make`,`mileage`,`model`,`price`,`seatingcapacity`,`status`,`type`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		queryRunner.update(sql, car.getId(), car.getLuggagecapacity(), car.getMake(), car.getMileage(), car.getModel(),
				car.getPrice(), car.getSeatingcapacity(), car.getStatus(), car.getType());
		return;
	}

	public void delete(Car car) throws SQLException {
		String sql = "delete from car where id = " + car.getId();
		queryRunner.update(sql);
	}

	public void update(Car car) throws SQLException {
		delete(car);
		add(car);

	}

	public List<Car> list() {
		String sql = "select * from car";
		List<Car> result = null;
		try {
			result = queryRunner.query(sql, new BeanListHandler<Car>(Car.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
