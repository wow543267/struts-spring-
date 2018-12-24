package com.tibame.domain;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

//規範資料維護功能
public interface IDao<T> {
//為何用Object?-->不確定參數有幾個和是否和查詢鍵值有關
public T queryForObject(String sql,Object...args) throws SQLException;
public List<T> queryForList(String sql,Object...args) throws SQLException;
//強迫注入依存關係的datasource(連接工廠)<--相當於setter
//子類別在實作這個介面時，同時將這個方法實做出來
public 	void setdataSource(DataSource dataSource);
public DataSource getDataSource();	
	
}
