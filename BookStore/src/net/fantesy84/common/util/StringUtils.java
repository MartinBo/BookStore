package net.fantesy84.common.util;

public class StringUtils {

	private StringUtils() {
	}
	
	public static String getReadMethodName(String filedName) {
		if (filedName == null || filedName.length() == 0) {
			throw new IllegalArgumentException("属性名错误");
		}
		char[] chars = filedName.toCharArray();
		if (!(Character.isUpperCase(chars[0]))) {
			chars[0] = Character.toUpperCase(chars[0]);
		}
		StringBuilder sb = new StringBuilder("get");
		return sb.append(chars).toString();
	}
	
	public static String getWirteMethodName(String filedName) {
		if (filedName == null || filedName.length() == 0) {
			throw new IllegalArgumentException("属性名错误");
		}
		char[] chars = filedName.toCharArray();
		if (!(Character.isUpperCase(chars[0]))) {
			chars[0] = Character.toUpperCase(chars[0]);
		}
		StringBuilder sb = new StringBuilder("set");
		return sb.append(chars).toString();
	}
}
