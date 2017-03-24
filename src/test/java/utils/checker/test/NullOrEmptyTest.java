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
package utils.checker.test;

import static org.junit.Assert.*;

import static utils.checker.NullOrEmpty.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;

import org.junit.Test;

/**
 * Test of NullOrEmpty class.<br>
 * NullOrEmpty类的测试.
 * 
 * @author Hervey Hall <mail@herveyhall.cf>
 *
 */
public class NullOrEmptyTest {

	@Test
	public void testCheckingObject() {
		assertEquals(false, check(new Object()));
		assertEquals(false, check(3));
	}

	@Test
	public void testCheckingString() {
		assertEquals(false, check("HelloWorld"));
		StringBuilder sb = new StringBuilder("abc");
		sb.setLength(0);
		assertEquals(true, check(sb));
		assertEquals(true, check("   "));
		assertEquals(true, check(new String()));
	}

	/**
	 * May have different result in later implements.<br>
	 * 在以后的实现中可能有不同的结果.
	 */
	@Test
	public void testCheckingArray() {
		assertEquals(false, check(new byte[0]));
		assertEquals(true, check(new int[0][]));
		assertEquals(false, check(new int[1][0]));
		assertEquals(true, check(new String[3][]));
	}

	@Test
	public void testCheckingIterableAndMap() {
		assertEquals(true, check(new Hashtable<>()));
		assertEquals(true, check(new HashMap<>()));
	}

	@Test
	public void testCheckingInputStream() {
		try {
			assertEquals(true, check(System.in));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
