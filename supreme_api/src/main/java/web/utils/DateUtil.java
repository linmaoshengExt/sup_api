package web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tuandai.crypt.util.CryptUtils;

public class DateUtil {
	public final static SimpleDateFormat day_sdf = new SimpleDateFormat("yyyy-MM-dd");
	public final static SimpleDateFormat time_sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public final static SimpleDateFormat time_stamp_sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	public final static SimpleDateFormat day_hour_sdf= new SimpleDateFormat("MM-dd HH:mm");


	public static String getTimeFormat(Date date) {
		return time_sdf.format(date);
	}

	public static String getDayFormat(Date date) {
		return day_sdf.format(date);
	}

	public static String getTimeStampFormat(Date date) {
		return time_stamp_sdf.format(date);
	}

	
	public static String getDayHourFormat(Date date) {
		return day_hour_sdf.format(date);
	}
	
	
	
	public static Date getBeforedays(int beforeDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, beforeDays);// 瀵版鍩岄崜宄冩径锟�
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		Date date = calendar.getTime();
		return date;
	}

	public static Date getToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		// calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		Date date = calendar.getTime();
		return date;
	}

	/**
	 * 閼惧嘲褰�8娴ｅ秳绗夐柌宥咁槻闂呭繑婧�閻緤绱欓崣鏍х秼閸撳秵妞傞梻瀛樺煈鏉烆剙瀵叉稉鍝勫磩閸忣叀绻橀崚璁圭礆
	 * 
	 * @return
	 */
	public static String getHexUniqueId(Date date) {
		long time = date.getTime();
		return Integer.toHexString((int) time);
	}

	/**
	 * 濮ｆ棁绶濇稉銈勯嚋閺冨爼妫块惄绋挎▕婢舵艾鐨粔锟�
	 * 
	 */
	public static long diffSecond(Date date1, Date date2) {
		return (date1.getTime() - date2.getTime()) / 1000;
	}

	/**
	 * 濮ｆ棁绶濇稉銈勯嚋閺冨爼妫块惄绋挎▕婢舵艾鐨粔锟�
	 * 
	 */
	public static long diffMillSecond(Date date1, Date date2) {
		return (date1.getTime() - date2.getTime());
	}

	/**
	 * 閺嶈宓侀弮銉︽埂閼惧嘲绶遍弰鐔告埂
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeekOfDate(Date date) {
		// String[] weekDaysName = { "閺勭喐婀￠弮锟�", "閺勭喐婀℃稉锟�", "閺勭喐婀℃禍锟�", "閺勭喐婀℃稉锟�", "閺勭喐婀￠崶锟�",
		// "閺勭喐婀℃禍锟�","閺勭喐婀￠崗锟�" };
		String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDaysCode[intWeek];
	}

	/**
	 * 閺嶈宓侀弮銉︽埂閼惧嘲绶遍弰鐔告埂
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeekNameOfDate(Date date) {
		String[] weekDaysName = { "閺勭喐婀￠弮锟�", "閺勭喐婀℃稉锟�", "閺勭喐婀℃禍锟�", "閺勭喐婀℃稉锟�", "閺勭喐婀￠崶锟�", "閺勭喐婀℃禍锟�", "閺勭喐婀￠崗锟�" };
		// String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDaysName[intWeek];
	}

	/**
	 * 閼惧嘲绶遍幐鍥х暰閺冦儲婀￠崜锟�/閸氬骸鎳嗛弮銉︽埂
	 * 
	 * @param date
	 * @return
	 */
	public static Date getSpecialDay(Date date, int days) {

		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);// -7:閸撳秳绔撮崨銊ｏ拷锟�7:閸氬簼绔撮崨锟�
		return calendar.getTime();

	}

	/**
	 * 閼惧嘲绶遍崨銊ょ閻ㄥ嫭妫╅張锟�
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonday(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);

		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		return calendar.getTime();

	}

	/**
	 * 閼惧嘲绶遍崨銊ょ瑏閻ㄥ嫭妫╅張锟�
	 * 
	 * @param date
	 * @return
	 */
	public static Date getWednesday(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);

		calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);

		return calendar.getTime();

	}

	/**
	 * 閼惧嘲绶遍崨銊ょ安閻ㄥ嫭妫╅張锟�
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFriday(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);

		calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);

		return calendar.getTime();
	}

	/**
	 * 閼惧嘲绶遍崨銊︽）閻ㄥ嫭妫╅張锟�
	 * 
	 * @param date
	 * @return
	 */
	public static Date getSunday(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(date);

		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

		return cal.getTime();
	}

	/**
	 * 瑜版挸澧犻弮銉︽埂閸撳秴鍤戞径鈺傚灗閼板懎鎮楅崙鐘层亯閻ㄥ嫭妫╅張锟�
	 * 
	 * @param n
	 * @return
	 */
	public static Date afterNDay(int n) {

		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(new Date());

		cal.add(Calendar.DATE, n);

		return cal.getTime();

	}

	/**
	 * 閸掋倖鏌囨稉銈勯嚋閺冦儲婀￠弰顖氭儊閸︺劌鎮撴稉锟介崨锟�
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setFirstDayOfWeek(Calendar.MONDAY);
		cal2.setFirstDayOfWeek(Calendar.MONDAY);
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			// 婵″倹鐏�12閺堝牏娈戦張锟介崥搴濈閸涖劍铆鐠恒劍娼甸獮瀵割儑娑擄拷閸涖劎娈戠拠婵嗗灟閺堬拷閸氬簼绔撮崨銊ュ祮缁犳浠涢弶銉ュ嬀閻ㄥ嫮顑囨稉锟介崨锟�
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}

	// 閸欐牕绶遍弮銉︽埂閺勵垱鐓囬獮瀵告畱缁楊剙鍤戦崨锟�
	public static int getWeekOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(date);
		int week_of_year = cal.get(Calendar.WEEK_OF_YEAR);
		return week_of_year;
	}

	// 閸欐牕绶遍弻鎰嚋閺堝牊婀佹径姘毌婢讹拷
	public static int getDaysOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		int days_of_month = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return days_of_month;
	}

	// 閸欐牕绶辨稉銈勯嚋閺冦儲婀℃稊瀣？閻ㄥ嫮娴夊顔碱樋鐏忔垵銇�
	public static long getDaysBetween(Date date0, Date date1) {
		long daysBetween = (date0.getTime() - date1.getTime()) / 86400000;// 86400000=3600*24*1000
																			// 閻€劎鐝涢崡铏殶閿涘苯鍣虹亸鎴滅濞夋洝顓哥粻妤冩畱瀵拷闁匡拷
		return daysBetween;
	}

	public static Date getZeroClock(Date date) {
		try {
			String zeroClock = DateUtil.day_sdf.format(date);
			return DateUtil.day_sdf.parse(zeroClock);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int getDayOfMonth(Date date) {
		int intWeek = 1;
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			calendar.setTime(date);
			intWeek = calendar.get(Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return intWeek;
	}

	public static Date getFirstDayOfMonth(Date date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-");
			return day_sdf.parse(sdf.format(date) + "01");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date getLastDayOfMonth(Date date) {
		try {
			return getSpecialDay(getFirstDayOfNextMonth(date), -1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int getLengthOfMonth(Date date) {
		try {
			return getDayOfMonth(getLastDayOfMonth(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static Date getFirstDayOfNextMonth(Date date) {
		try {
			SimpleDateFormat sdf_t = new SimpleDateFormat("yyyy-M-dd");
			SimpleDateFormat sdf_y = new SimpleDateFormat("yyyy");
			SimpleDateFormat sdf_m = new SimpleDateFormat("MM");
			int year = new Integer(sdf_y.format(date));
			int next_month = new Integer(sdf_m.format(date)) + 1;
			if (next_month > 12) {
				year++;
				next_month = 1;
			}
			return sdf_t.parse(year + "-" + next_month + "-01");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 閼惧嘲褰囬幐鍥х暰閺冦儲婀�(dateStr)娑斿澧犻惃鍒foreDays婢垛晝娈戦弮銉︽埂閸掓銆�
	 * 
	 * @param dateStr
	 * @param beforeDays
	 * @return
	 */
	public static List<String> getDayListBefore(String dateStr,
			int beforeDays) {
		List<String> list = new ArrayList<String>();

		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		for (int i = beforeDays; i > 0; i--) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int day = cal.get(Calendar.DATE);
			cal.set(Calendar.DATE, day - i);
			String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(cal
					.getTime());
			list.add(dayBefore);
		}
		return list;
	}
	public static List<String> getDayListBetween(Date start,Date end) {
		List<String> list = new ArrayList<String>();
		int beforeDays = (int) getDaysBetween(start, end);
		
		for (int i = -beforeDays; i >= 0; i--) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(end);
			int day = cal.get(Calendar.DATE);
			cal.set(Calendar.DATE, day - i);
			String dayBefore = day_sdf.format(cal.getTime());
			list.add(dayBefore);
		}
		return list;
	}

	
	  /*
    閼惧嘲褰囬弮鍫曟？瀹革拷
     */
    public static String getTimesToNow(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = format.format(new Date());
        String returnText = null;
        try {
            long from = format.parse(date).getTime();
            long to = format.parse(now).getTime();
            int days = (int) ((to - from)/(1000 * 60 * 60 * 24));
            if(days == 0){//娑擄拷婢垛晙浜掗崘鍜冪礉娴犮儱鍨庨柦鐔稿灗閼板懎鐨弮鑸垫▔缁�锟�
                int hours = (int) ((to - from)/(1000 * 60 * 60));
                if(hours == 0){
                    int minutes = (int) ((to - from)/(1000 * 60));
                    if(minutes == 0){
                        returnText = "閸掓艾鍨�";
                    }else{
                        returnText = minutes + "閸掑棝鎸撻崜锟�...";
                    }
                }else{
                    returnText = hours + "鐏忓繑妞傞崜锟�...";
                }
            } else if(days == 1){
                returnText = "閺勩劌銇�";
            }else{
                returnText = days + "婢垛晛澧�...";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnText;
    }
	
	
	public static void main(String[] args) throws Exception {
/*	String a ="61E416E7-F8A9-4210-9639-50A5B5E1FBC1";
	
	String aa= "61E416E7-F8A9-4210-9639-50A5B5E1FBC1";
		System.err.println(a.toLowerCase());
		System.err.println(aa.toUpperCase());*/
		Map<String ,Object> data = new HashMap<String ,Object>();
		data.put("projectId", "3573E056-C495-4ECE-8832-FFD09545E122");
		data.put("period", 1);
		data.put("guaranteeAmount", 3369.5);
		data.put("orgAmount", 6);
		data.put("agencyAmount", 126);
		data.put("arbitrationAmount", 6);
		/*String a = CryptUtils.toJsonString("td_zhph_xfjr", "MJNdwerlobvURRJHitgYTEYKopI", data);
		System.err.println(a);*/
	}
	
	

}
