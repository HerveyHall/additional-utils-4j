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
package utils.entity;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import utils.digest.DigestConfigure;
import utils.digest.Digestable;

/**
 * 
 * 
 * @author Hervey Hall <mail@herveyhall.cf>
 *
 */
public final class ByteArray implements Digestable {

	private byte[] entity;

	private static DigestConfigure dc;

	public ByteArray() {
		entity = new byte[0];
	}

	public ByteArray(byte[] bytes) {
		entity = bytes;
	}

	/**
	 * Append 在原有的字节数组后连接字节数组并生成新的字节数组对象信息
	 * 
	 * @param bytes
	 *            需要连接的字节数组
	 * @return 新的数组对象信息
	 */
	public final ByteArray append(byte[] bytes) {
		byte[] byteArray = new byte[entity.length + bytes.length];
		for (int i = 0; i < byteArray.length; ++i) {
			byteArray[i] = i < toBytes().length ? entity[i] : bytes[i];
		}
		return new ByteArray(byteArray);
	}

	/**
	 * Generate a new byte array which prefixed another byte array.<br>
	 * 在原有的字节数组前连接字节数组并生成新的字节数组对象信息
	 * 
	 * @param bytes
	 * @return \
	 */
	public final ByteArray prefix(byte[] bytes) {
		byte[] byteArray = new byte[bytes.length + entity.length];
		for (int i = 0; i < byteArray.length; ++i) {
			byteArray[i] = i < bytes.length ? bytes[i] : entity[i];
		}
		return new ByteArray(byteArray);
	}

	/**
	 * Generate a new byte array which prefixed another byte array.<br>
	 * 在原有的字节数组前连接字节数组并生成新的字节数组对象信息
	 * 
	 * @param bytes
	 * @return
	 */
	public final ByteArray prefix(ByteArray bytes) {
		return prefix(bytes.toBytes());
	}

	/**
	 * Convert to inputStream.<br>
	 * 转换成输入流
	 * 
	 * @return
	 */
	public final InputStream toStream() {
		return new BufferedInputStream(new ByteArrayInputStream(entity));
	}

	@Override
	public final Map<String, String> getDigestMap() {
		if (null == dc) {
			dc = new DigestConfigure();
		}
		Map<String, String> result = new HashMap<>();
		Method[] digestMethods = dc.getClass().getMethods();
		for (Method dm : digestMethods) {
			if (!dm.getName().matches("^(digest)[A-Z][a-z|\\d]*$") || !dm.getReturnType().equals(String.class)) {
				continue;
			}
			try {
				result.put(dm.getName().substring("digest".length()).toUpperCase(), (String) dm.invoke(dc, toBytes()));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new RuntimeException("Something was wrong with invoking the digest methods.");
			}
		}
		return result;
	}

	@Override
	public final void setDigestConfigure(DigestConfigure digestConfigure) {
		dc = digestConfigure;
	}

	@Override
	public final byte[] toBytes() {
		return entity;
	}

	/**
	 * 将输入流转化成ByteArray对象，转化时的缓冲区长度为1024字节
	 * 
	 * @param inputStream
	 *            输入流
	 * @return
	 * @throws IOException
	 */
	public static final ByteArray parse(InputStream inputStream) throws IOException {
		return parse(inputStream, 1024);
	}

	/**
	 * 将输入流转化成ByteArray对象
	 * 
	 * @param inputStream
	 *            输入流
	 * @param bufferLength
	 *            转化时的缓冲区长度
	 * @return
	 * @throws IOException
	 */
	public static final ByteArray parse(InputStream inputStream, int bufferLength) throws IOException {
		int count = -1;
		byte[] buffer = new byte[bufferLength];
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		while ((count = inputStream.read(buffer, 0, bufferLength)) != -1) {
			outputStream.write(buffer, 0, count);
		}
		return new ByteArray(outputStream.toByteArray());
	}
}
