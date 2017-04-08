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

/**
 * This checker is used to check if an object is in the GC Roots Set. <br>
 * 检查对象是否为GC Roots的检查器.
 * 
 * @author Hervey Hall <mail@herveyhall.cf>
 *
 */
public class GCRoot {

	/**
	 * Check whether an object is in the GC Roots Set.<br>
	 * 检查一个对象是否在GC Roots Set中.<br>
	 * 
	 * <pre>
	 * According to Zhou Zhiming's book "Understand the JVM (Second Edition)" Section 3.2.2 "Reachability Analysis Algorithm" described in the following:
	 * "In the Java language, the objects that can be used as GC Roots include the following:
	 * <li>The objects referenced in the virtual machine stack (local variable table in the stack frame).
	 * <li>The objects referenced in a static attribute which is in a method area.
	 * <li>The objects referenced in a constant which is in a method area.
	 * <li>The objects referenced in JNI (that is, the Native method) which is in local method stack."
	 * </pre>
	 * 
	 * <pre>
	 * 根据周志明老师的著作《深入理解Java虚拟机(第二版)》第3.2.2节“可达性分析算法”中的描述如下：<br>
	 * “在Java语言中，可作为GC Roots的对象包括下面几种：
	 * <li>虚拟机栈（栈帧中的本地变量表）中引用的对象。
	 * <li>方法区中类静态属性引用的对象。
	 * <li>方法区中常量引用的对象。
	 * <li>本地方法栈中JNI（即一般说的Native方法）引用的对象。”
	 * </pre>
	 * 
	 * @param object
	 * @return
	 */
	public static final boolean check(Object object) {
		throw new UnsupportedOperationException("This function has not yet been implemented.");
	}
}
