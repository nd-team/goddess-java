package com.bjike.goddess.user.utils.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [操作cookie]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CookieOperate {

    public static void addCookie(Cookie cookie, HttpServletResponse response) {
        if (null != cookie) {
            response.addCookie(cookie);
        }
    }

    public static void removeCookieByName(String name, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    cookie.setMaxAge(0);// 立即销毁cookie
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }

    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }


}
