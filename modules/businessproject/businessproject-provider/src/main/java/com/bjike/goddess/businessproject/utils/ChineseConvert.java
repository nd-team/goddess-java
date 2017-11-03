package com.bjike.goddess.businessproject.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


/**
 * @Author: [tanghaixiang]
 * @Date: [2017-03-21 11:29]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ChineseConvert {

    public static void main(String[] args) {
        String name = "湖光月色";
        int lenth = name.length();
        String py = getTargetNumber( name , lenth );
        System.out.println( py );
    }

    public static String getTargetNumber(String src, int length) {
        String number = "";
        try {
            for (int i = 0; i < length; i++) {//获取指定长度的字符穿首字母大写
                char text = src.charAt(i);
                String[] initial = PinyinHelper.toHanyuPinyinStringArray(text, new HanyuPinyinOutputFormat());
                if (initial != null && initial.length> 0 ) {
                    number += initial[0].charAt(0);
                } else {
                    number += text;
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
        return number.toUpperCase();
    }

}
