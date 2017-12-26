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
package utils.scanner;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.HashSet;

/**
 * The class scanner is used to scan classes in a variety of ways.<br>
 * 类扫描器.用于通过多种方式扫描到类.
 * 
 * @author Hervey Hall <mail@herveyhall.cf>
 *
 */
public class ClassScanner {

	/**
	 * Scans all the classes under the specified path according to the URI and
	 * returns these classes. 根据URI扫描指定路径下的所有类并返回这些类.
	 * 
	 * @param uri
	 *            The URI used to locate the resource path that needs to be
	 *            scanned.<br>
	 *            用于定位需要扫描的资源路径的URI.
	 * @param isRecursive
	 *            Whether to recursively scan the file.<br>
	 *            是否递归扫描文件.
	 * @return Classes scanned on criteria.<br>
	 *         根据条件扫描到的所有类.
	 */
	public static java.util.Set<Class<?>> scanFromUri(URI uri, boolean isRecursive) {
		return scanFromUri(uri, isRecursive, null);
	}

	/**
	 * Scan all classes containing the specified annotation under the specified path
	 * according to the URI and return these classes. 根据URI扫描指定路径下的包含指定注解的所有类并返回这些类.
	 * 
	 * @param uri
	 *            The URI used to locate the resource path that needs to be
	 *            scanned.<br>
	 *            用于定位需要扫描的资源路径的URI.
	 * @param isRecursive
	 *            Whether to recursively scan the file.<br>
	 *            是否递归扫描文件.
	 * @param annotations
	 *            A collection of all the annotations that need to be included in
	 *            the scanned class. This parameter needs to be set to null if it is
	 *            desired to return all classes.<br>
	 *            扫描的类中需要包含的所有Annotation.如果期望返回所有类需要将此参数设置为null.
	 * @return Classes scanned on criteria.<br>
	 *         根据条件扫描到的所有类.
	 */
	public static java.util.Set<Class<?>> scanFromUri(URI uri, boolean isRecursive,
			java.util.Collection<Class<? extends Annotation>> annotations) {
		File[] binFiles = new File(uri).listFiles();
		java.util.Set<Class<?>> classes = new HashSet<>();
		for (File file : binFiles) {
			if (file.exists() && file.isFile() && file.getName().endsWith(".class")) {
				Class<?> clazz = null;
				try {
					clazz = Class
							.forName(
									file.toURI().toString()
											.replaceFirst("(^)(?:" + Thread.currentThread().getContextClassLoader()
													.getResource(".").toString() + ")", "$1")
											.replaceAll("\\.class($)", "$1").replaceAll("/", "."),
									false, ClassLoader.getSystemClassLoader());
				} catch (ClassNotFoundException e) {
					throw new RuntimeException(e.getCause());
				}
				if (null == annotations) {
					classes.add(clazz);
					continue;
				}
				annotationLoop: for (Annotation clazzAnnotation : clazz.getAnnotations()) {
					for (Class<?> conditionAnnotation : annotations) {
						if (!clazzAnnotation.annotationType().equals(conditionAnnotation)) {
							continue annotationLoop;
						}
					}
					classes.add(clazz);
				}
			} else if (isRecursive && file.isDirectory()) {
				classes.addAll(scanFromUri(file.toURI(), isRecursive, annotations));
			}
		}
		return classes;
	}
}
