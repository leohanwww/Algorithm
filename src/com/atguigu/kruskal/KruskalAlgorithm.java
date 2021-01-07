package com.atguigu.kruskal;

import java.util.Arrays;

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
        kruskal.kruskal();
//        EData[] edges = kruskal.getEdges();
//        System.out.println("所有边:" + Arrays.toString(edges));
//        kruskal.sortEdges(edges);
//        System.out.println("排序后:" + Arrays.toString(edges));

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
            for (int j = i + 1; j < vertexs.length; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }

    }

    public void kruskal() {
        int index = 0; //表示最后结果数组的索引
        int[] ends = new int[edgeNum]; //保持"已有最小生成树"中每个顶点在最小生成树中的终点
        //结果数组,保持最后的最小生成树
        EData[] res = new EData[edgeNum];
        //获取图中所有的边
        EData[] edges = getEdges();
        //排序所有边
        sortEdges(edges);
        //遍历边,判断是否回路,加入到最小生成树
        for (int i = 0; i < edgeNum; i++) {
            //获取第i条边的起点和终点
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);
            //获取p1这个顶点在已有最小生成树中的终点
            int m = getEnd(ends, p1);
            //p2顶点在已有最小生成树终点
            int n = getEnd(ends, p2);
            //是否构成回路
            if (m != n) {
                ends[m] = n; //设置m在已有最小生成树的终点
                res[index++] = edges[i]; //加入到res数组
            }
        }
        //输出res数组
        System.out.println("最小生成树为");
        for (int i = 0; i < index; i++) {
            System.out.println(res[i]);
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

    /**
     * description
     * 对边进行冒泡排序
     *
     * @param edges 边的集合
     * @return void
     */
    private void sortEdges(EData[] edges) {
        EData temp = null;
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    /**
     * description
     *
     * @param ch 顶点的值,比如'A','B'
     * @return int 返回ch顶点对应的下标,找不到返回-1
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * description
     * 获取图中的边,放到EData[]数组中
     * 如[['A','B',12],['B','F',7],......]
     *
     * @param
     * @return com.atguigu.kruskal.EData[]
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * description
     * 获取下标为i的顶点的终点,用以判断两个顶点的终点是否相同
     *
     * @param ends 数组记录了各个顶点对应的终点是哪个
     * @param i
     * @return int 下标为i的顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }
}

//类EData,对象实例表示一条边
class EData {
    char start; //边的起点
    char end; //边的终点
    int weight; //边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
