package web.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;



public class IpUntil {
	public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }
	
	
	public static void main(String[] args) {
	   int a =0;
	   
	   for (int i = 0; i < 6; i++) {
		int b =1;
		a = a+b;
	}
	   System.err.println(a);
	}
}
