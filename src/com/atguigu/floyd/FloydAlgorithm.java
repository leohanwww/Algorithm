package com.atguigu.floyd;

import java.util.Arrays;

public class FloydAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 65535;
        int[][] matrix = {
                {0, 5, 7, N, N, N, 2},
                {5, 0, N, 9, N, N, 3},
                {7, N, 0, N, 8, N, N},
                {N, 9, N, 0, N, 4, N},
                {N, N, 8, N, 0, 5, 4},
                {N, N, N, 4, 5, 0, 6},
                {2, 3, N, N, 4, 6, 0}
        };
        Graph graph = new Graph(vertex.length, matrix, vertex);
        graph.show();
    }
}

class Graph {
    char[] vertex;//顶点数组
    int[][] dis; //邻接距离表
    int[][] pre; //前驱表

    /**
     * description
     *
     * @param length 顶点个数
     * @param martix 邻接关系
     * @param vertex 顶点数组
     * @return
     */
    public Graph(int length, int[][] martix, char[] vertex) {
        this.vertex = vertex;
        this.dis = martix;
        this.pre = new int[length][length];
        //对pre数组初始化,存放的是前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    //显示dis和pre
    public void show() {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (int k = 0; k < dis.length; k++) {
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pre[k][i]] + " ");
            }
            System.out.println();
            //显示dis数组
            for (int i = 0; i < dis.length; i++) {
                System.out.print("(" + vertex[k] + "到" + vertex[i] + "的最短路径是" + dis[k][i] + ") ");
            }
            System.out.println();
            System.out.println();
        }


    }
}
