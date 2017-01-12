package com.bjike.goddess.common.jpa.utils;

/**
 * @Author: [liguiqin]
 * @Date: [2016-12-27 15:13]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CharacterUtil {

    /**
     * 首字母小写
     * @param val
     * @return
     */
    public static String lowerCaseFirst(String val){
        if(!Character.isLowerCase(val.charAt(0))){
            char[] cs=val.toCharArray();
            cs[0]+=32;
            return String.valueOf(cs);
        }
       return val;
    }
    /**
     * 首字母大写
     * @param val
     * @return
     */
    public static String upperCaseFirst(String val){
        if(!Character.isUpperCase(val.charAt(0))){
            char[] cs=val.toCharArray();
            cs[0]-=32;
            return String.valueOf(cs);
        }
       return  val;
    }

    public static void main(String[] args) {
        System.out.println(CharacterUtil.upperCaseFirst("aaa"));
        System.out.println(CharacterUtil.lowerCaseFirst("AAA"));
        System.out.println( Character.isLowerCase("Aa".charAt(0)));
    }

}
