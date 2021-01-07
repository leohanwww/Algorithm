package com.atguigu.kruskal;

public class KruskalAlgorithm {

    private int edgeNum; //边的个数
    private char[] vertexs; //顶点数组
    private int[][] matrix; //邻接矩阵
    private static final int INF = Integer.MAX_VALUE; //表示两个顶点不能连通

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[][]{
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };
        //创建kruskal实例
        KruskalAlgorithm kruskal = new KruskalAlgorithm(vertexs, matrix);
        kruskal.print();
    }

    //构造器
    public KruskalAlgorithm(char[] vertexs, int[][] matrix) {
        //初始化顶点数和边的个数
        this.vertexs = new char[vertexs.length];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }
        //初始化边矩阵
        this.matrix = new int[vertexs.length][vertexs.length];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计有效边
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }

    }

    //打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵为: \n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%15d\t", matrix[i][j]);
            }
            System.out.println();

        }
    }
}
