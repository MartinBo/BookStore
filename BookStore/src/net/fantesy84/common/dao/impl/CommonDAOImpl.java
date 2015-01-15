package net.fantesy84.common.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import net.fantesy84.common.dao.ICommonDAO;
import net.fantesy84.common.util.ConnectionUtils;
import net.fantesy84.common.util.ReflectionUtils;

public class CommonDAOImpl<T extends java.io.Serializable> implements
		ICommonDAO<T> {
	private PreparedStatement ps;
	private ResultSet rs;

	@Override
	public int update(String sql, Object... args) throws Exception {
		init(sql, args);
		int effectRows = ps.executeUpdate();
		ps.close();
		ConnectionUtils.close();
		return effectRows;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T queryForObject(String sql, Class<T> clazz, Object... args) throws Exception {
		init(sql, args);
		rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = 0;
		columnCount = rsmd.getColumnCount();
		Object instance = ReflectionUtils.createInstance(clazz);
		while (rs.next()) {
			if (columnCount > 0) {
				for (int i = 1; i <= columnCount; i++) {
					String columnName = rsmd.getColumnName(i);
					String columnClassName = rsmd.getColumnClassName(i);
					System.out.println(Class.forName(columnClassName));
					String columnLabel = rsmd.getColumnLabel(i);
					Object obj = null;
					if (columnName.equals(columnLabel)) {
						obj = rs.getObject(columnName);
						if (obj == null) {
							continue;
						}
						if (obj instanceof Timestamp) {
							java.util.Date date = new java.util.Date(((Timestamp) obj).getTime());
							ReflectionUtils.setter(instance, columnName, obj, date.getClass());
						} else {
							Class<?> paramsClassType = Class.forName(columnClassName);
							ReflectionUtils.setter(instance, columnName, obj, paramsClassType);
						}
					} else {
						obj = rs.getObject(columnLabel);
						if (obj == null) {
							continue;
						}
						if (obj instanceof Timestamp) {
							java.util.Date date = new java.util.Date(((Timestamp) obj).getTime());
							ReflectionUtils.setter(instance, columnLabel, obj, date.getClass());
						} else {
							Class<?> paramsClassType = Class.forName(columnClassName);
							ReflectionUtils.setter(instance, columnLabel, obj, paramsClassType);
						}
					}
				}
			}
		}
		rs.close();
		ps.close();
		ConnectionUtils.close();
		return (T) instance;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> queryForList(String sql, Class<T> clazz, Object... args) throws Exception {
		init(sql, args);
		rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = 0;
		columnCount = rsmd.getColumnCount();
		List<T> list = new ArrayList<T>(0);
		Object instance = ReflectionUtils.createInstance(clazz);
		while (rs.next()) {
			if (columnCount > 0) {
				for (int i = 1; i <= columnCount; i++) {
					String columnName = rsmd.getColumnName(i);
					String columnClassName = rsmd.getColumnClassName(i);
					String columnLabel = rsmd.getColumnLabel(i);
					Object obj = null;
					if (columnName.equals(columnLabel)) {
						obj = rs.getObject(columnName);
						if (obj == null) {
							continue;
						}
						if (obj instanceof Timestamp) {
							java.util.Date date = new java.util.Date(((Timestamp) obj).getTime());
							ReflectionUtils.setter(instance, columnName, obj, date.getClass());
						} else {
							Class<?> paramsClassType = Class.forName(columnClassName);
							ReflectionUtils.setter(instance, columnName, obj, paramsClassType);
						}
					} else {
						obj = rs.getObject(columnLabel);
						if (obj == null) {
							continue;
						}
						if (obj instanceof Timestamp) {
							java.util.Date date = new java.util.Date(((Timestamp) obj).getTime());
							ReflectionUtils.setter(instance, columnLabel, obj, date.getClass());
						} else {
							Class<?> paramsClassType = Class.forName(columnClassName);
							ReflectionUtils.setter(instance, columnLabel, obj, paramsClassType);
						}
					}
				}
			}
			list.add((T) instance);
		}
		rs.close();
		ps.close();
		ConnectionUtils.close();
		return list;
	}

	@Override
	public Integer queryForInteger(String sql, Object... args) throws Exception {
		Integer rows = 0;
		init(sql, args);
		rs = ps.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = 0;
		columnCount = rsmd.getColumnCount();
		while (rs.next()) {
			if (columnCount > 1) {
				throw new SQLException("统计函数查询语法不正确");
			} else {
				String columnName = rsmd.getColumnName(1);
				String columnLabel = rsmd.getColumnLabel(1);
				if (columnName.equals(columnLabel)) {
					rows = rs.getInt(columnName);
				} else {
					rows = rs.getInt(columnLabel);
				}
			}
		}
		rs.close();
		ps.close();
		ConnectionUtils.close();
		return rows;
	}

	private void init(String sql, Object...args) throws Exception{
		if (args == null || args.length == 0) {
			throw new IllegalArgumentException("必须传入占位符对应的参数数组");
		}
		Connection conn = ConnectionUtils.getConnection();
		ps = conn.prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			ps.setObject(i + 1, args[i]);
		}
	}
}
