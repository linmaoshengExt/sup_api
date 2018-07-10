package web.utils;

import java.util.UUID;

public class UuidUntil {

	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
