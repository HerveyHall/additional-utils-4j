/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2017 Hervey Hall, mail@herveyhall.cf
 *
 * 
 * Additional-utils-4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Additional-utils-4j is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with QBlog.  If not, see <http://www.gnu.org/licenses/>.
*
* additional-utils-4j是一个自由软件，您可以自由分发、修改其中的源代码或者重新发布它，
* 新的任何修改后的重新发布版必须同样在遵守LGPL3或更后续的版本协议下发布.
* 关于LGPL协议的细则请参考COPYING、COPYING.LESSER文件，
* 您可以在additional-utils-4j的相关目录中获得LGPL协议的副本，
* 如果没有找到，请连接到 http://www.gnu.org/licenses/ 查看。
*
* - Author: Hervey Hall
* - Contact: mail@herveyhall.cf
* - License: GNU Lesser General Public License (LGPL)
* - Source code availability: http://https://github.com/HerveyHall/additional-utils-4j/
*/
package utils.checker;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

/**
 * This checker is used to check an object is "null" or "empty".<br>
 * 检查对象是否为null或为空的检查器.
 * 
 * @author Hervey Hall <mail@herveyhall.cf>
 *
 */
public class NullOrEmpty {

	/**
	 * Check null or empty for string. <br>
	 * 检查字符串是否为null或为空.
	 * 
	 * @param string
	 * @return
	 */
	public static final boolean check(String string) {
		return null == string || string.equals(null) || string.trim().isEmpty();
	}

	/**
	 * Check null or empty for array.<br>
	 * 检查数组是否为null或为空.
	 * 
	 * @param array
	 * @return
	 */
	public static final <T> boolean check(T[] array) {
		if (null == array || 0 == array.length) {
			return true;
		}
		for (Object obj : array) {
			if (!check(obj)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check null or empty for iterable object.<br>
	 * 检查可迭代对象是否为null或为空.
	 * 
	 * @param iterable
	 * @return
	 */
	public static final <E> boolean check(Iterable<E> iterable) {
		if (null == iterable || iterable.equals(null)) {
			return true;
		}
		Iterator<E> iterator = iterable.iterator();
		while (iterator.hasNext()) {
			if (!check(iterator.next())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check null or empty for map.<br>
	 * 检查映射对象是否为null或为空.
	 * 
	 * @param map
	 * @return
	 */
	public static final <K, V> boolean check(Map<K, V> map) {
		return null == map || map.equals(null) || map.isEmpty() || (map.keySet().isEmpty() || 0 == map.keySet().size())
				|| check(map.values());
	}

	/**
	 * Check null or empty for input-stream.<br>
	 * 检查输入流是否为null或为空.
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static final boolean check(InputStream inputStream) throws IOException {
		return null == inputStream || inputStream.available() == 0;
	}

	/**
	 * Check null or empty for object.<br>
	 * 检查对象是否为null或为空.
	 * 
	 * @param object
	 * @return
	 */
	public static final boolean check(Object object) {
		return null == object || object.equals(null) || NullOrEmpty.check(object.toString())
				|| null == object.getClass();
	}
}
