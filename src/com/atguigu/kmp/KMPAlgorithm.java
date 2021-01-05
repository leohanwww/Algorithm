package com.atguigu.kmp;

import java.util.Arrays;

public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext(str2);
        int index = kmpSearch(str1, str2, next);
        System.out.println("index=" + index); //15
    }

    //搜索算法

    /**
     * description
     *
     * @param str1 长字符串
     * @param str2 要找的串
     * @param next 子串对应的部分匹配表
     * @return int  在原串找到的子串开始的位置,没有就是-1
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        //遍历str1
        for (int i = 0, j = 0; i < str1.length(); i++) {
            //处理str1.charAt(i) != str2.charAt(j)的情况,调整j的大小
            //kmp算法核心
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1]; //让j往部分匹配表next的继续找,直到找到相等
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                //找到匹配
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取一个子串部分匹配表
    public static int[] kmpNext(String dest) {
        //保持部分匹配值
        int[] next = new int[dest.length()];
        //如果dest长度为1 next只有一个值为0
        next[0] = 0;
        //扫描dest的长度
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //dest.charAt(i) != dest.charAt(j)
            //需要一直找到dest.charAt(i) == dest.charAt(j)的情况
            //kmp算法核心
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                //满足条件,部分匹配值+1
                j++;
            }
            next[i] = j; //第i个字符匹配了j次
        }
        return next;
    }
}
