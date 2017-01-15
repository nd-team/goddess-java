package com.bjike.goddess.user.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: 验证码生产工具]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AuthCodeGenerate {
    public static final int WIDTH = 150;
    public static final int HEIGHT = 40;

    /**
     *
     * @return 生成的图片流及随机验证码数字
     */
    public static Map<String, BufferedImage> build() {
        // 1.在内存中创建一张图片
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        // 2.虚拟画笔得到图片
        Graphics g = image.getGraphics();
        // 3.设置图片的背影色
        setBackGround(g);
        // 4.设置图片的边框
        setBorder(g);
        // 5.在图片上画干扰线
        drawRandomLine(g);
        // 6.写在图片上随机数
        String code = drawRandomNum((Graphics2D) g);// 生成数字和字母组合的验证码图片
        Map<String, BufferedImage> authCodeMaps = new HashMap<>(0);
        authCodeMaps.put(code, image);
        return authCodeMaps;
    }

    /**
     * 设置图片的背景色
     *
     * @param g
     */
    private static void setBackGround(Graphics g) {
        // 设置颜色
        g.setColor(Color.WHITE);
        // 填充区域
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    /**
     * 设置图片的边框
     *
     * @param g
     */
    private static void setBorder(Graphics g) {
        // 设置边框颜色
        g.setColor(Color.WHITE);
        // 边框区域
        g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
    }

    /**
     * 在图片上画随机线条
     *
     * @param g
     */
    private static void drawRandomLine(Graphics g) {
        // 设置颜色
//		g.setColor(Color.GREEN);
        Random r = new Random();
        // 设置线条个数并画线
        for (int i = 0; i < 10; i++) {
            int x1 = r.nextInt(WIDTH);
            int y1 = r.nextInt(HEIGHT);
            int x2 = r.nextInt(WIDTH);
            int y2 = r.nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
            //设置干扰线颜色
            Color color = new Color(20 + r.nextInt(210), 20 + r.nextInt(210), 20 + r.nextInt(210));
            g.setColor(color);
        }

    }

    /**
     * 画随机字符
     *
     * @param g
     */
    private static String drawRandomNum(Graphics2D g) {
        // 设置颜色
        g.setColor(Color.RED);
        // 设置字体
        g.setFont(new Font("宋体", Font.BOLD, 30));

        // 数字和字母的组合
        String baseNumLetter = "Aa0Bb1CcDd3EeFf5Gg6HhJjKkLl7MmN9nOoPp8QqRrSs2TtUuVv4WwXxYyZz";
        return createRandomChar(g, baseNumLetter);
    }

    /**
     * 创建随机字符
     *
     * @param g
     * @param baseChar
     * @return 随机字符
     */
    private static String createRandomChar(Graphics2D g, String baseChar) {
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        int x = 5;
        String ch = "";
        // 控制字数
        for (int i = 0; i < 4; i++) {
            // 设置字体旋转角度
            int degree = r.nextInt() % 30;
            ch = baseChar.charAt(new Random().nextInt(baseChar.length())) + "";
            //设置随机数的颜色
            Color color = new Color(20 + r.nextInt(210), 20 + r.nextInt(210), 20 + r.nextInt(210));
            g.setColor(color);
            sb.append(ch);
            // 正向角度
            g.rotate(degree * Math.PI / 180, x, 10);
            g.drawString(ch, x, 30);
            // 反向角度
            g.rotate(-degree * Math.PI / 180, x, 10);
            x += 30;
        }
        return sb.toString();
    }

}
