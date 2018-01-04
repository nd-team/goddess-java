package com.bjike.goddess.customerplatform.utils;

import java.math.BigDecimal;

/**
 * AHP层次分析法计算权重
 *
 * @Author: [liguiqin]
 * @Date: [2017-05-18 10:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class AHPComputeWeight {

    //单例
    private static final AHPComputeWeight acw = new AHPComputeWeight();

    //平均随机一致性指针
    private double[] RI = {0.00, 0.00, 0.58, 0.96, 1.12, 1.24, 1.32, 1.41, 1.45, 1.49};

    //随机一致性比率
    private double CR = 0.0;

    //最大特征值
    private double lamta = 0.0;

    /**
     * 私有构造
     */
    private AHPComputeWeight() {

    }

    /**
     * 返回单例
     *
     * @return
     */
    public static AHPComputeWeight getInstance() {
        return acw;
    }

    /**
     * 计算权重
     *
     * @param a
     * @param weight
     * @param N
     */
    public void weight(double[][] a, double[] weight, int N) {
        //初始向量 Wk
        double[] w0 = new double[N];
        for (int i = 0; i < N; i++) {
            w0[i] = 1.0 / N;
        }

        //一般向量 W(k+1)
        double[] w1 = new double[N];

        //W(k+1)的归一化向量
        double[] w2 = new double[N];

        double sum = 1.0;

        double d = 1.0;

        //误差
        double delt = 0.00001;

        while (d > delt) {
            d = 0.0;
            sum = 0;

            //获取向量
            int index = 0;
            for (int j = 0; j < N; j++) {
                double t = 0.0;
                for (int l = 0; l < N; l++){
                    t += a[j][l] * w0[l];
                }
                w1[j] = t;
                sum += w1[j];
            }

            //向量归一化
            for (int k = 0; k < N; k++) {
                w2[k] = w1[k] / sum;

                //最大差值
                d = Math.max(Math.abs(w2[k] - w0[k]), d);

                //用于下次迭代使用
                w0[k] = w2[k];
            }
        }

        //计算矩阵最大特征值 lamta,CI,RI
        lamta = 0.0;


        for (int k = 0; k < N; k++) {
            lamta += w1[k] / (N * w0[k]);
        }


        double CI = (lamta - N) / (N - 1);

        if (RI[N - 1] != 0) {
            CR = CI / RI[N - 1];
        }

        //四舍五入处理
        lamta = round(lamta, 3);
        CI = Math.abs(round(CI, 3));
        CR = Math.abs(round(CR, 3));


        for (int i = 0; i < N; i++) {
            w0[i] = round(w0[i], 4);
            w1[i] = round(w1[i], 4);
            w2[i] = round(w2[i], 4);
        }

        //控制台打印
//        System.out.println("最大特征值lamta=" + lamta);
//        System.out.println("一致性检验指标CI=" + CI);
//        System.out.println("随机一致性比率CR=" + CR);


        //打印台打印权重
//        System.out.println("初始向量w0=");
//        for (int i = 0; i < N; i++) {
//            System.out.println(w0[i] + " ");
//        }
//        System.out.println("");
//
//
//        System.out.println("一般向量w1[]=");
//        for (int i = 0; i < N; i++) {
//            System.out.println(w1[i] + "");
//        }
//        System.out.println("");
//
//
//        System.out.println("归一化向量w2[]=");
        for (int i = 0; i < N; i++) {
            weight[i] = w2[i];
//            System.out.println(w2[i] + "");
        }
//        System.out.println("");
    }


    /**
     * 四舍五入
     *
     * @param v
     * @param scale
     * @return
     */
    public double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive or zero");

        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 返回随机一致性比率
     *
     * @return
     */
    public double getCR() {
        return CR;
    }

//    public static void main(String[] args) {
//        /** a 为 N*N 矩阵 */
//        double[][]a = {{1,1/2d,3,6},{2,1,7,8},{1/3d,1/7d,1,5},{1/6d,1/8d,1/5d,1}};
//        double[][] a = {{1, 3, 5}, {2, 3, 1}, {4, 7, 3}};
//        int N = a[0].length;
//        double[] weight = new double[N];
//        AHPComputeWeight instance = AHPComputeWeight.getInstance();
//        instance.weight(a, weight, N);
//        System.out.println("权重:" + Arrays.toString(weight));
//    }
}
