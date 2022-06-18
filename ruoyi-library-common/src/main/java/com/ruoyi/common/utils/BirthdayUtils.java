package com.ruoyi.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 生日工具类
 * @author weibocy
 */
public class BirthdayUtils {

    public enum Constellation {
        Capricorn(1, "摩羯座"), Aquarius(2, "水瓶座"), Pisces(3, "双鱼座"), Aries(4, "白羊座"), Taurus(5, "金牛座"), Gemini(6, "双子座"),
        Cancer(7, "巨蟹座"), Leo(8, "狮子座"), Virgo(9, "处女座"), Libra(10, "天秤座"), Scorpio(11, "天蝎座"), Sagittarius(12, "射手座");

        private Constellation(int code, String chineseName) {
            this.code = code;
            this.chineseName = chineseName;
        }

        private int code;
        private String chineseName;

        public int getCode() {
            return this.code;
        }

        public String getChineseName() {
            return this.chineseName;
        }
    }

    public static final Constellation[] constellationArr = { Constellation.Aquarius, Constellation.Pisces,
        Constellation.Aries, Constellation.Taurus, Constellation.Gemini, Constellation.Cancer, Constellation.Leo,
        Constellation.Virgo, Constellation.Libra, Constellation.Scorpio, Constellation.Sagittarius,
        Constellation.Capricorn };

    public static final int[] constellationEdgeDay = { 21, 20, 21, 21, 22, 22, 23, 24, 24, 24, 23, 22 };


    /**
     * 计算星座
     * @param birthday 生日字符串
     * @return
     */
    public static String calculateConstellation(String birthday) {
        if (birthday == null || birthday.trim().length() == 0) {
            throw new IllegalArgumentException("the birthday can not be null");
        }
        String[] birthdayElements = birthday.split("-");
        if (birthdayElements.length != 3) {
            throw new IllegalArgumentException("the birthday form is not invalid");
        }
        int month = Integer.parseInt(birthdayElements[1]);
        int day = Integer.parseInt(birthdayElements[2]);
        if (month == 0 || day == 0 || month > 12) {
            return "";
        }
        month = day < constellationEdgeDay[month - 1] ? month - 1 : month;
        return month > 0 ? constellationArr[month - 1].getChineseName() : constellationArr[11].getChineseName();
    }



    /**
     * 计算年龄
     * @param birthday
     * @return
     */
    public static int getAgeByBirth(Date birthday) {
        // Calendar：日历
        /* 从Calendar对象中或得一个Date对象 */
        Calendar cal = Calendar.getInstance();
        /* 把出生日期放入Calendar类型的bir对象中，进行Calendar和Date类型进行转换 */
        Calendar bir = Calendar.getInstance();
        bir.setTime(birthday);
        /* 如果生日大于当前日期，则抛出异常：出生日期不能大于当前日期 */
        if (cal.before(birthday)) {
            throw new IllegalArgumentException("The birthday is before Now,It's unbelievable");
        }
        /* 取出当前年月日 */
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayNow = cal.get(Calendar.DAY_OF_MONTH);
        /* 取出出生年月日 */
        int yearBirth = bir.get(Calendar.YEAR);
        int monthBirth = bir.get(Calendar.MONTH);
        int dayBirth = bir.get(Calendar.DAY_OF_MONTH);
        /* 大概年龄是当前年减去出生年 */
        int age = yearNow - yearBirth;
        /* 如果出当前月小与出生月，或者当前月等于出生月但是当前日小于出生日，那么年龄age就减一岁 */
        if (monthNow < monthBirth || (monthNow == monthBirth && dayNow < dayBirth)) {
            age--;
        }
        return age;
    }

}
