package net.fantesy84.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * 静态资源文件Properties读取工具类
 * 
 * @author junjie.ge
 *
 */
public class PropertyUtils {
	/**
	 * 自有静态实例
	 */
	private static PropertyUtils util;
	/**
	 * @see java.util.Properties
	 */
	private static Properties p;
	/**
	 * 静态块,预加载资源文件
	 */
	static {
		p = new Properties();
		InputStream in = PropertyUtils.class.getClassLoader()
				.getResourceAsStream(SystemContents.PROPERTY_PATH);
		try {
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 私有构造,保证单例模式的基础
	 */
	private PropertyUtils() {

	}

	/**
	 * 外部获取本工具类实例方法<br/>
	 * 
	 * <pre>
	 * 若本工具类实例已存在,则返回当前实例,若不存在,则新创建一个.此方式为懒加载模式
	 * </pre>
	 * 
	 * @return
	 */
	public static PropertyUtils getInstance() {
		if (util == null) {
			return new PropertyUtils();
		}
		return util;
	}

	/**
	 * 获取Properties文件中key对应的值
	 * 
	 * @param key
	 *            资源文件中的key
	 * @return 资源文件key对应的值
	 */
	public String getValue(String key) {
		return p.getProperty(key);
	}
}
