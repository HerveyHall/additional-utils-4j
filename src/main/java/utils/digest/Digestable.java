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
package utils.digest;

import java.util.Map;

/**
 * Generate message digests with custom algorithms, you may use the default
 * algorithms in this library.<br>
 * 实现该接口可以通过自定义的算法获取消息签名，该库中默认提供的签名算法见{@link util.DigestConfigure}.
 * 
 * @see util.DigestConfigure
 * @author Hervey Hall <mail@herveyhall.cf>
 *
 */
public interface Digestable {
	/**
	 * Set the digest configuation to appoint the names of the message digests
	 * and how to get them.<br>
	 * 设置签名配置用以指定签名的名称以及获取方式
	 * 
	 * @param digestConfigure
	 */
	void setDigestConfigure(DigestConfigure digestConfigure);

	Map<String, String> getDigestMap();

	byte[] toBytes();
}
