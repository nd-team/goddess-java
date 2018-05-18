package com.bjike.goddess.user.utils;

import java.util.UUID;

public class ShareCodeUtil {

    /** 自定义进制（选择你想要的进制数，不能重复且最好不要0、1这些容易混淆的字符） */
    private static final char[] r=new char[]{'q', 'w', 'e', '8', 's', '2', 'd', 'z', 'x', '9', 'c', '7', 'p', '5', 'k', '3', 'm', 'j', 'u', 'f', 'r', '4', 'v', 'y', 't', 'n', '6', 'b', 'g', 'h'};

    /** 定义一个字符用来补全邀请码长度（该字符前面是计算出来的邀请码，后面是用来补全用的） */
    private static final char b='a';

    /** 进制长度 */
    private static final int binLen=r.length;

    /** 邀请码长度 */
    private static final int s=6;

//    /**
//     * 根据ID生成随机码
//     * @param id ID
//     * @return 随机码
//     */
//    public static String toSerialCode(long id) {
//        char[] buf=new char[32];
//        int charPos=32;
//
//        while((id / binLen) > 0) {
//            int ind=(int)(id % binLen);
//            buf[--charPos]=r[ind];
//            id /= binLen;
//        }
//        buf[--charPos]=r[(int)(id % binLen)];
//        String str=new String(buf, charPos, (32 - charPos));
//        // 不够长度的自动随机补全
//        if(str.length() < s) {
//            StringBuilder sb=new StringBuilder();
//            sb.append(b);
//            Random rnd=new Random();
//            for(int i=1; i < s - str.length(); i++) {
//                sb.append(r[rnd.nextInt(binLen)]);
//            }
//            str+=sb.toString();
//        }
//        return str;
//    }

    /** 补位字符串 */
    private static final String e="atgsghj";

    /**
     * 根据ID生成六位随机码
     * @param id ID
     * @return 随机码
     */
    public static String toSerialCode(long id) {
        char[] buf=new char[32];
        int charPos=32;

        while((id / binLen) > 0) {
            int ind=(int)(id % binLen);
            buf[--charPos]=r[ind];
            id /= binLen;
        }
        buf[--charPos]=r[(int)(id % binLen)];
        String str=new String(buf, charPos, (32 - charPos));
        // 不够长度的自动补全
        if(str.length() < s) {
            StringBuilder sb=new StringBuilder();
            sb.append(e.subSequence(0, s-str.length()));
            str+=sb.toString();
        }
        return str;
    }

    /**
     * 根据随机码生成ID
     * @param
     * @return ID
     */
    public static long codeToId(String code) {
        char chs[]=code.toCharArray();
        long res=0L;
        for(int i=0; i < chs.length; i++) {
            int ind=0;
            for(int j=0; j < binLen; j++) {
                if(chs[i] == r[j]) {
                    ind=j;
                    break;
                }
            }
            if(chs[i] == b) {
                break;
            }
            if(i > 0) {
                res=res * binLen + ind;
            } else {
                res=ind;
            }
        }
        return res;
    }

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };


    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }
}
