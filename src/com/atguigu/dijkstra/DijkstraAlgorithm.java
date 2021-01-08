package com.atguigu.dijkstra;

import java.util.Arrays;

public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 65535; //表示不可连接
        int[][] matrix = {
                {N, 5, 7, N, N, N, 2},
                {5, N, N, 9, N, N, 3},
                {7, N, N, N, 8, N, N},
                {N, 9, N, N, N, 4, N},
                {N, N, 8, N, N, 5, 4},
                {N, N, N, 4, 5, N, 6},
                {2, 3, N, N, 4, 6, N}
        };
        //创建图
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
    }
}

class Graph {
    private char[] vertex; //顶点数组
    private int[][] matrix; //邻接矩阵

    public Graph(char[] vertexs, int[][] matrix) {
        this.vertex = vertexs;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }
}
