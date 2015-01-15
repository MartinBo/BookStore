package net.fantesy84.common.dao;

import java.util.List;
/**
 * 通用DAO接口
 * @author junjie.ge
 *
 * @param <T>	泛型对象
 */
public interface ICommonDAO<T extends java.io.Serializable> {
	/**
	 * 通用更新
	 * @param sql	SQL语句
	 * @param args	参数数组
	 * @return	受影响的行数
	 * @throws Exception
	 */
	public int update(String sql, Object... args) throws Exception;
	/**
	 * 通用对象查询(用于单对象查询,一般用于ID查询)
	 * @param sql	SQL语句
	 * @param clazz TODO
	 * @param args	参数数组
	 * @return	泛型对象
	 * @throws Exception
	 */
	public T queryForObject(String sql, Class<T> clazz, Object... args) throws Exception;
	/**
	 * 通用集合查询
	 * @param sql	SQL语句
	 * @param clazz TODO
	 * @param args	参数数组
	 * @return	泛型对象集合
	 * @throws Exception
	 */
	public List<T> queryForList(String sql, Class<T> clazz, Object... args) throws Exception;
	/**
	 * 查询记录数
	 * @param sql	SQL语句
	 * @param args	参数数组
	 * @return	分页/不分页时的总记录数
	 * @throws Exception
	 */
	public Integer queryForInteger(String sql, Object... args) throws Exception;
}
