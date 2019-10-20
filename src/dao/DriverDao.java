package dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.Driver;
import utils.DataSourceUtils;

public class DriverDao {

	private static DriverDao driver = new DriverDao();

	public static DriverDao getInstance() {
		return driver;
	}

	private DriverDao() {

	}

	private QueryRunner queryRunner = DataSourceUtils.getQueryRunner();

	public void add(Driver driver) throws SQLException {
		// 没传ID
		if (driver.getId() != null && driver.getId() == 0) {
			String sql = "INSERT INTO `driver`(`id`,`firstname`,`lastname`,`birthdate`,`licencenumber`,`licencestate`) VALUES ( NULL, ?, ?, ?, ?, ?)";
			queryRunner.update(sql, driver.getFirstname(), driver.getLastname(), driver.getBirthdate(),
					driver.getLicencenumber(), driver.getLicencestate());
			return;
		}

		// 传ID
		String sql = "INSERT INTO `driver`(`id`,`firstname`,`lastname`,`birthdate`,`licencenumber`,`licencestate`) VALUES ( ?, ?, ?, ?, ?, ?)";
		queryRunner.update(sql, driver.getId(), driver.getFirstname(), driver.getLastname(), driver.getBirthdate(),
				driver.getLicencenumber(), driver.getLicencestate());
		return;
	}

	public void delete(Driver driver) throws SQLException {
		String sql = "delete from driver where id = " + driver.getId();
		queryRunner.update(sql);
	}

	public void update(Driver driver) throws SQLException {
		delete(driver);
		add(driver);

	}

	public List<Driver> list() {
		String sql = "select * from driver";
		List<Driver> result = null;
		try {
			result = queryRunner.query(sql, new BeanListHandler<Driver>(Driver.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
