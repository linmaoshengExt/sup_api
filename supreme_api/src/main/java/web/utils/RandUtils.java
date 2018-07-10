package web.utils;

import java.util.Random;

public class RandUtils {

	public static String rand() {

		String sources = "0123456789abcdefghijklmnopqrstuvwxyz"; // 加上一些字母，就可以生成pc站的验证码了
		Random rand = new Random();
		StringBuffer flag = new StringBuffer();
		for (int j = 0; j < 6; j++) {
			flag.append(sources.charAt(rand.nextInt(36)) + "");
		}

		return flag.toString();
	}
}
