package com.atguigu.kmp;

public class ViolenceMatch {
    public static void main(String[] args) {
        //暴力匹配算法
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        int index = violenceMatch(str1, str2);
        System.out.println(index);
    }

    //暴力匹配算法
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1Len = str1.length();
        int s2Len = str2.length();
        int i = 0; //s1的索引
        int j = 0; //s2的索引
        while (i < s1Len && j < s2Len) { //匹配时不越界
            if (s1[i] == s2[j]) {
                //匹配成功
                i++;
                j++;
            } else {
                //匹配不成功
                i = i - (j - 1);
                j = 0;
            }
        }
        //判断是否匹配成功
        if (j == s2Len) {
            return i - j;
        }else {
            return -1;
        }
    }
}
