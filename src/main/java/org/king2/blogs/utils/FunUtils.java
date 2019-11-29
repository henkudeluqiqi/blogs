package org.king2.blogs.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*================================================================
说明：功能函数类

作者          时间           		 注释
俞烨      2018.6.2     		 创建
==================================================================*/
public class FunUtils {
	
	static String[] units = {"","十","百","千","万","十万","百万","千万","亿","十亿","百亿","千亿","万亿" };  
    static char[] numArray = {'零','一','二','三','四','五','六','七','八','九'};

	/**----------------------------------------------------------------
	功能：判断字符串中是否包含非法字符
	
	参数:  str 			String   字符串
	return: Boolean  true:当前字符串包含非法字符,false:当前字符串不不包含非法字符
	-------------------------------------------------------------------*/
	public static Boolean illegalCharacter(String str) {
		//String regEx = "[~!/@#$%^&*()-_=+\\|[{}];:\'\",<.>/?]+";
		String regEx = "[`~!@#$%^&*()+=|{}':;'\\[\\]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’？]+";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}


	/**----------------------------------------------------------------
	 功能：判断手机号是否正确

	 参数:  str 			String   字符串
	 return: Boolean  true:当前字符串不符合,false:当前字符串符合
	 -------------------------------------------------------------------*/
	public static boolean checkPhone(String phone) {


		String REX = "/^1[34578]\\d{9}$/";
		Pattern p = Pattern.compile(REX);
		Matcher m = p.matcher(phone);
		return m.find();
	}


	/**-----------------------------------------------------------------
     * 功能：计算时间差
     * 
     * 参数：endDate 		Date	结尾日期
     * 		nowDate		Date	当前日期
     * 
     * 返回：String		返回：天小时分
     -------------------------------------------------------------------*/
	public static String getDatePoor(Date endDate, Date nowDate) {
		 
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    // long ns = 1000;
	    // 获得两个时间的毫秒时间差异
	    long diff = endDate.getTime() - nowDate.getTime();
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    // long sec = diff % nd % nh % nm / ns;
	    return day + "天" + hour + "小时" + min + "分钟";
	}
	
	/**-----------------------------------------------------------------
     * 功能：寻找某个字符在字符串中第n次出现的位置
     * 
     * 参数：ch 		char	要查找的字符
     * 		str		String	被查找的字符串
     * 		times	int		出现的次数
     * 
     * 返回：int		-1代表未找到
     -------------------------------------------------------------------*/
	public static int findCharIndex(char ch,String str,int times) {
		//返回索引
		int index=-1;
		//记数索引
		int number=0;
		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if(ch==charArray[i]) {
				number++;
			}
			if(number==times) {
				index=i;
				break;
			}
		}
		return index;
	}
	
	/**-----------------------------------------------------------------
     * 功能：对float进行取scale位小数
     * 
     * 参数：int 		scale	位数
     * 		f		float	处理的数据
     * 
     * 返回：截取之后的Float
     -------------------------------------------------------------------*/
	public static Float cutFloat(int scale,float f) {
		  BigDecimal   bd  =   new  BigDecimal((double)f);  
		  //对小数进行去尾
		  bd   =  bd.setScale(scale,BigDecimal.ROUND_DOWN);
		  return bd.floatValue();
	}
	
	/**-----------------------------------------------------------------
     * 功能：获取日期中的年月日(字符串形式)
     * 
     * 参数：Date 	date	日期
     * 
     * 返回：Integer[]		下标从0对应年开始依次递增
     -------------------------------------------------------------------*/
	public static String[] getNumberDate(Date date) {
		String[] strNow = new SimpleDateFormat("yyyy-MM-dd").format(date).toString().split("-");
		return strNow;
	}
	
	/**-----------------------------------------------------------------
     * 功能：获取某个月有多少天
     * 
     * 参数：String 	date	yyyy-MM-dd形式的日期
     * 
     * 返回：int		天数
     -------------------------------------------------------------------*/
	public static int getMaxDate(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(date));
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**-----------------------------------------------------------------
     * 功能：获取某一天是周几，按照美国星期天是周一计算
     * 
     * 参数：String 	date	yyyy-MM-dd形式的日期
     * 
     * 返回：int		周几
     -------------------------------------------------------------------*/
	public static int getWeekDay(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(date));
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	/**-----------------------------------------------------------------
     * 功能：将整数转化为中文数字
     * 
     * 参数：int 		num		整数
     * 
     * 返回：String	格式化之后的中文数字
     -------------------------------------------------------------------*/
    public static String formatInteger(int num) {  
        char[] val = String.valueOf(num).toCharArray();  
        int len = val.length;  
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < len; i++) {  
            String m = val[i] + "";  
            int n = Integer.valueOf(m);  
            boolean isZero = n == 0;  
            String unit = units[(len - 1) - i];  
            if (isZero) {  
                if ('0' == val[i - 1]) {  
                    continue;  
                } else {  
                    sb.append(numArray[n]);  
                }  
            } else {  
                sb.append(numArray[n]);  
                sb.append(unit);  
            }  
        }  
        return sb.toString();  
    }  
    
    /**-----------------------------------------------------------------
     * 功能：将小数转化为中文数字
     * 
     * 参数：decimal 		double		小数
     * 
     * 返回：String		转化之后的中文数字
     -------------------------------------------------------------------*/
    public static String formatDecimal(double decimal) {  
        String decimals = String.valueOf(decimal);  
        int decIndex = decimals.indexOf(".");  
        int integ = Integer.valueOf(decimals.substring(0, decIndex));  
        int dec = Integer.valueOf(decimals.substring(decIndex + 1));  
        String result = formatInteger(integ) + "." + formatFractionalPart(dec);  
        return result;  
    }  
      
    /**-----------------------------------------------------------------
     * 功能：格式化小数部分的数字
     * 
     * 参数：int 		decimal		小数部分数字
     * 
     * 返回：String	格式化之后的结果
     -------------------------------------------------------------------*/
    public static String formatFractionalPart(int decimal) {  
        char[] val = String.valueOf(decimal).toCharArray();  
        int len = val.length;  
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < len; i++) {  
            int n = Integer.valueOf(val[i] + "");  
            sb.append(numArray[n]);  
        }  
        return sb.toString();  
    }  
    
    /**-----------------------------------------------------------------
     * 功能：将string数组转化为Integer数组
     * 
     * 参数：String[] 	str		要转化的数组
     * 
     * 返回：Integer[]			转化之后的数组
     -------------------------------------------------------------------*/
    public static Integer[] StringToInteger(String [] str) {
    	Integer [] ints=new Integer [str.length];
    	for(int i=0;i<str.length;i++) {
    		ints[i]=Integer.parseInt(str[i]);
    	}
    	return ints;
    }
	
    /**-----------------------------------------------------------------
     * 功能：获取一段时间内的有多少个工作日
     * 
     * 参数：Date 		start		开始时间
     * 		Date		end			结束时间
     * 
     * 返回：Integer					工作日总数
     -------------------------------------------------------------------*/
    public static Integer getWorkDay(Date start,Date end) throws ParseException {
    	Calendar calendar=Calendar.getInstance();
    	calendar.setTime(end);
    	calendar.add(Calendar.DATE, 1);
    	end=calendar.getTime();
    	
    	Date date=start;
    	int day=0;
    	//设置初始时间
    	calendar.setTime(date);
    	
    	//遍历
    	while(!date.equals(end)) {
    		//不为周六周日
    		if(calendar.get(Calendar.DAY_OF_WEEK)!=Calendar.SATURDAY && calendar.get(Calendar.DAY_OF_WEEK)!= Calendar.SUNDAY ) {
    			day++;
    		}
    		calendar.setTime(date);
    		//日期加一天
    		calendar.add(Calendar.DATE, 1);
    		date=calendar.getTime();
    	}
    	return day;
    }
    
    /**-----------------------------------------------------------------
     * 功能：获取一段时间内的周一至周五某一个有多少天
     * 
     * 参数：Date 		start		开始日期
     * 		Date		end			结束日期
     * 		Integer		week		周几
     * 
     * 返回：Integer				    天总数
     -------------------------------------------------------------------*/
    public static Integer getWeekDays(Date start,Date end,Integer week) throws ParseException {
    	Calendar calendar=Calendar.getInstance();
    	calendar.setTime(end);
    	calendar.add(Calendar.DATE, 1);
    	end=calendar.getTime();
    	
    	Date date=start;
    	
    	int day=0;
    	//设置初始时间
    	calendar.setTime(date);
    	
    	//遍历
    	while(!date.equals(end)) {
    		//相同
    		if(calendar.get(Calendar.DAY_OF_WEEK)== (week+1)) {
    			day++;
    		}
    		calendar.setTime(date);
    		//日期加一天
    		calendar.add(Calendar.DATE, 1);
    		date=calendar.getTime();
    	}
    	return day;
    }
    
    /**-----------------------------------------------------------------
     * 功能：获取16位的uuid
     * 
     * 返回：String			 uuid
     -------------------------------------------------------------------*/
    public static String getUUID() {
    	String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(16);
    	return uuid;
    }
    
    /**-----------------------------------------------------------------
     * 功能：对list进行去重
     * 
     * 返回：List<T>			 去重之后的集合
     -------------------------------------------------------------------*/
    public static <T>List<T> removeDuplicate(List<T> list){  
        List<T> listTemp = new ArrayList<T>();  
        for(int i=0;i<list.size();i++){  
            if(!listTemp.contains(list.get(i))){  
                listTemp.add(list.get(i));  
            }  
        }  
        return listTemp;  
    }
}
