package com.bjike.goddess.abilitydisplay.enums;

/**
 * @Author: [xiazhili]
 * @Date: [2018-03-20 16:57]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class Father {
    static {
        System.out.println("a");
    }

    Father(){
        System.out.println("b");
    }

    {
        System.out.println("c");
    }

}
