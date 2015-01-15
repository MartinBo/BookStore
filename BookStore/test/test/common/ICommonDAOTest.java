package test.common;

import java.util.Date;

import net.fantesy84.common.dao.ICommonDAO;
import net.fantesy84.common.dao.impl.CommonDAOImpl;
import net.fantesy84.common.util.ConnectionUtils;
import net.fantesy84.common.util.PropertyUtils;
import net.fantesy84.common.util.SystemContents;
import net.fantesy84.domain.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ICommonDAOTest {
	private ICommonDAO<User> commonDao;

	@Before
	public void init() {
		commonDao = new CommonDAOImpl<User>();
	}

	@Test
	public void testSave() throws Exception {
		String sql = "INSERT INTO USER VALUES(?,?,?,?,?,?,?)";
		Object[] args = { null,"admin", "admin", new Date(), null, "葛俊杰", "fantesy84" };
		commonDao.update(sql, args);
	}
	
	@Test
	public void testQuery() throws Exception{
		String sql = "SELECT ID AS 'userId',U_NAME AS 'userName',U_PWD AS 'userPwd',C_TIME AS 'createTime',M_TIME AS 'modifyTime',R_NAME AS 'realName',N_NAME AS 'nickName' FROM USER AS u WHERE u.ID=?";
		Object[] args = {3};
		User user = commonDao.queryForObject(sql, User.class, args);
		Assert.assertNotNull(user);
	}

	@Test
	public void testConnection() {
		Assert.assertNotNull(ConnectionUtils.getConnection());
	}

	@Test
	public void testGetConnection() {
		String DRIVER_NAME = PropertyUtils.getInstance().getValue(
				SystemContents.JDBC_DRIVER);
		String CONN_URL = PropertyUtils.getInstance().getValue(
				SystemContents.JDBC_URL);
		String USER = PropertyUtils.getInstance().getValue(
				SystemContents.JDBC_USER);
		String PWD = PropertyUtils.getInstance().getValue(
				SystemContents.JDBC_PWD);
		System.out.println(DRIVER_NAME);
		System.out.println(CONN_URL);
		System.out.println(USER);
		System.out.println(PWD);
	}
}
