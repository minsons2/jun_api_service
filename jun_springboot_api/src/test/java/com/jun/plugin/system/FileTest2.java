package com.jun.plugin.system;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

public class FileTest2 {

	public static void main(String[] args) throws IOException {
//		printURL();
		printFilterURL2();
//		printDoubleURL();
	}
 
	public static void printFilterURL2() throws IOException {
		List<String> linesURL = FileUtils.readLines(new File("D:\\Documents\\Desktop\\111111_Full.txt"));
//		List<String> linesExists = FileUtils.readLines(new File("D:\\Documents\\Desktop\\linesExists.txt"));
//		List<String> linesExistsURL = FileUtils.readLines(new File("D:\\Documents\\Desktop\\linesExistsURL.txt"));
		List<String> linesURLS = Lists.newArrayList();
//		linesURL.removeAll(linesExistsURL);
		for (String str : linesURL) {
			if(str.contains(".mp4")) {
				linesURLS.add(str);
			}
		}
		System.err.println(linesURLS.size());
//		linesName.removeAll(linesExists);
		System.err.println(linesURLS.size());
		linesURL.stream().forEach(System.err::println);
	}

}
