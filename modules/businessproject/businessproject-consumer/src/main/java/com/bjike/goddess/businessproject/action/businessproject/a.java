package com.bjike.goddess.businessproject.action.businessproject;

import java.util.Scanner;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-11-21 17:11]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class a {

    public static void main(String[] args) {
        Object oo = new Object();
        System.gc();

        oo = new Object();
        oo = new Object();
        System.gc();
        byte[] bb = new byte[1024*1024];
        System.gc();
    }
}
