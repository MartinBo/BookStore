package net.fantesy84.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionUtils {
	private ReflectionUtils(){
		throw new RuntimeException("Please do not build private Constructor");
	}
	/**
	 * 通过类路径获取实例
	 * @param path	类路径
	 * @return	{@link Object}实例
	 */
	public static Object createInstance(String path) {
		try {
			Class<?> clazz = Class.forName(path);
			return clazz.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 重载createInstance方法
	 * @param clazz	Class对象
	 * @return	{@link Object}实例
	 */
	public static Object createInstance(Class<?> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 泛型方法返回实例
	 * @param clazz	Class对象
	 * @return	泛型对象
	 */
	public static <T> T createTargetInstance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object getter(Class<?> clazz, String filedName) {
		Object instance = createInstance(clazz);
		Class<?>[] paramsTypes = {};
		Object obj = null;
		try {
			Method reader = clazz.getMethod(StringUtils.getReadMethodName(filedName), paramsTypes);
			obj = reader.invoke(instance, new Object[]{});
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public static Object getter(Object instance, String filedName) {
		Class<?>[] paramsTypes = {};
		Object obj = null;
		try {
			Method reader = instance.getClass().getMethod(StringUtils.getReadMethodName(filedName), paramsTypes);
			obj = reader.invoke(instance, new Object[]{});
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public static void setter(Class<?> clazz, String filedName, Object val, Class<?> paramsClassType) {
		Object instance = createInstance(clazz);
		try {
			Method writer = clazz.getMethod(StringUtils.getWirteMethodName(filedName), paramsClassType);
			writer.invoke(instance, new Object[]{val});
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public static void setter(Object instance, String filedName, Object val, Class<?> paramsClassType) {
		try {
			Method writer = instance.getClass().getMethod(StringUtils.getWirteMethodName(filedName), paramsClassType);
			writer.invoke(instance, new Object[]{val});
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
