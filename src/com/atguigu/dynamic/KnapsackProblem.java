package com.atguigu.dynamic;

public class KnapsackProblem {
    public static void main(String[] args) {
        //物品重量
        int[] w = {1, 4, 3};
        //物品价格
        int[] val = {1500, 3000, 2000};
        int m = 4; //背包容量
        int n = val.length; //物品个数


        //创建二位数组
        //v[i][j]表示在前i个物品中能装入容量为j的背包的最大价值
        int[][] v = new int[n + 1][m + 1];
        //记录放入商品的情况
        int[][] path = new int[n + 1][m + 1];

        //初始化第一行和第一列为0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0; //处理第一列
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0; //处理第一行
        }

        //根据公式动态规划
        for (int i = 1; i < v.length; i++) { //不处理第1行
            for (int j = 1; j < v[0].length; j++) { //不处理第1列
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else { //j>=w[i-1]
//                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][(j - w[i - 1])]);
                    //为了记录商品存放到包里的情况
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][(j - w[i - 1])]) {
                        v[i][j] = val[i - 1] + v[i - 1][(j - w[i - 1])];
                        //记录当前情况到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.printf(v[i][j] + " ");
            }
            System.out.println();
        }
        //最后放入的是哪个商品
//        for (int i = 0; i < path.length; i++) {
//            for (int j = 0; j <path[i].length ; j++) {
//                if (path[i][j]==1){
//                    System.out.printf("第%d个商品放入到背包\n",i);
//                }
//            }
//        }
        int i = path.length - 1; // 行的最大下标
        int j = path[0].length - 1; //列的最大下标
        //从path最后开始找
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1];
            }
            i--;
        }
    }


}
