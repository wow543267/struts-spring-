package com.tibame.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component("dao3")
@Component("MyDao")//預設狀態為Singleton 隨著應用系統啟動spring bean factory產生
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)//設定工廠每次請求都產生一個獨立物件
public class CustomersDao2 implements IDao<CustomersBean> {
	@Autowired
	private DataSource dataSource;
	private int id;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// 自訂建構子
	public CustomersDao2() {
		id = (int) (Math.random() * 10000) + 1;
		System.out.println("CustomersDao2復活!!" + id);
	}

	@Override
	public CustomersBean queryForObject(String sql, Object... args) throws SQLException {
		CustomersBean bean = null;
		// 1.透過DataSource取得工廠
		if (this.dataSource == null) {

			throw new SQLException("資料來源物件未注入");

		}
		Connection conn = dataSource.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		// 設定參數
		int n = 1;
		for (Object param : args) {
			st.setObject(n, param);
			n++;

		}

		ResultSet rs = st.executeQuery();

		if (rs.next()) {
			bean = new CustomersBean();
			bean.setCustomerID(rs.getShort("customerid"));
			bean.setFirstName(rs.getString("firstname"));
			bean.setLastName(rs.getString("lastname"));
			bean.setEmail(rs.getString("email"));

		}
		conn.close();
		return bean;
	}

	@Override
	public List<CustomersBean> queryForList(String sql, Object... args) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setdataSource(DataSource dataSource) {
		this.dataSource = dataSource;

	}

	@Override
	public DataSource getDataSource() {
		// TODO Auto-generated method stub
		return dataSource;
	}

	public int number() {
        System.out.println("來自CustomersDao2!!"+id);
		return this.id;

	}

}
