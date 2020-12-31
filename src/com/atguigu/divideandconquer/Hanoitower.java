package com.atguigu.divideandconquer;

public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(5,'A','B','C');
    }

    //移动的方法
    public static void hanoiTower(int num, char a, char b, char c) {
        //如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从" + a + "->" + c);
        } else {
            //如果大于等于2个盘,视作1大1小两个盘
            //先把上面的所有盘A->B,中间使用C
            hanoiTower(num - 1, a, c, b);
            //把最下面的盘A->C
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            //把b塔的所有盘移动到c
            hanoiTower(num-1,b,a,c);
        }
    }
}
