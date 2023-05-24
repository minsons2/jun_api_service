package com.jun.plugin.system;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

public class FileTest {

	public static void main(String[] args) throws IOException {
		printURL();
//		printFilterURL2();
//		printDoubleURL();
	}

	public static void printURL() throws IOException {
//		String filePath = "‪D:\\202207\\s1.txt";
		String filePath[] = { "D:\\Documents\\Desktop\\111111.txt"
//				怡红院.txt
//				进吧4002.txt
//				哔哩哔哩.txt
//				U082801.txt
		};
		List<String> lines = Lists.newArrayList();
		for (String path : filePath) {
			lines.addAll(FileUtils.readLines(new File(path)));
		}
		List<String> linesNew = Lists.newArrayList();
		List<String> linesDelStr = Lists.newArrayList();
		for (int i = 0; i < lines.size(); i++) {
			String str = lines.get(i);
			String oldStr = str;
			if (!oldStr.contains("addWalter") && oldStr.contains("upload")) {
				str = str.substring(0, str.lastIndexOf(".") + 1) + "mp4";
				str = str.substring(0, str.lastIndexOf("/") + 1) + "addWalter"
						+ str.substring(str.lastIndexOf("/") + 1, str.length());
				str = str.replace("f2d.jieshundb.com", "ferddown.meilifangyy.cn");
				str = str.replace("jm.jieshundb.com", "ferddown.meilifangyy.cn");
				str = str.trim();
				// System.out.println(oldStr);
//				System.out.println();
//				System.out.println(str);
				if (!linesNew.contains(str)) {
					linesNew.add(str);
				}
			} else if (oldStr.contains("addWalter")) {
				str = str.substring(0, str.lastIndexOf(".") + 1) + "mp4";
				// str = str.substring(0,
				// str.lastIndexOf("/")+1)+""+str.substring(str.lastIndexOf("/")+1,str.length());
				str = str.replace("f2d.jieshundb.com", "ferddown.meilifangyy.cn");
				str = str.replace("jm.jieshundb.com", "ferddown.meilifangyy.cn");
				// System.out.println(oldStr);
//				System.err.println();
//				System.err.println(str);
				str = str.trim();
				if (!linesNew.contains(str)) {
					linesNew.add(str);
				}
			} else {
				linesDelStr.add(oldStr);
			}
		}
		linesNew.stream().forEach(System.err::println);
		System.err.println(linesNew.size());
		System.err.println(JSON.toJSONString(filePath));
//		linesDelStr.stream().forEach(System.out::println);
//		System.err.println(linesDelStr.size());
	}

	public static void printDoubleURL() throws IOException {
		List<String> linesURL = FileUtils.readLines(new File("D:\\Documents\\Desktop\\6666666666.txt"));
		Set<String> urls = new HashSet<>(linesURL);
		urls.stream().forEach(System.err::println);
		System.err.println(urls.size());
	}

	public static void printFilterURL2() throws IOException {
		List<String> linesURL = FileUtils.readLines(new File("D:\\Documents\\Desktop\\666666.txt"));
		List<String> linesExists = FileUtils.readLines(new File("D:\\Documents\\Desktop\\linesExists.txt"));
		List<String> linesExistsURL = FileUtils.readLines(new File("D:\\Documents\\Desktop\\linesExistsURL.txt"));
		List<String> linesName = Lists.newArrayList();
		linesURL.removeAll(linesExistsURL);
		for (String str : linesURL) {
			str = str.substring(str.lastIndexOf("/") + 1, str.length());
			linesName.add(str);
		}
		System.err.println(linesName.size());
		linesName.removeAll(linesExists);
		System.err.println(linesName.size());
		linesURL.stream().filter(str -> {
			if (linesName.contains(str.substring(str.lastIndexOf("/") + 1, str.length()))) {
				return true;
			}
			return false;
		}).forEach(System.err::println);
	}

}
