package com.atguigu.prim;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.util.Arrays;

public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertex = data.length;
        int[][] weight = new int[][]{ //10000值很大,表示两点不连通
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}
        };
        MGraph mGraph = new MGraph(vertex);
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, vertex, data, weight);
        minTree.showGraph(mGraph);
        //测试prim算法
        minTree.prim(mGraph, 0);

    }

}

//创建最小生成树
class MinTree {
    /**
     * description
     *
     * @param graph  图对象
     * @param vertex 图的顶点个数
     * @param data   图的顶点的值
     * @param weight 图的邻接矩阵
     * @return void
     */
    public void createGraph(MGraph graph, int vertex, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < vertex; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < vertex; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    //显示图的邻接矩阵
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    //prim算法,得到最小生成树

    /**
     * description
     *
     * @param graph  图
     * @param vertex 从图的第几个顶点开始生成 'A'-0 'B'-1
     * @return void
     */
    public void prim(MGraph graph, int vertex) {
        //标记节点被访问过,放到这个数组
        int[] visted = new int[graph.vertex];
        //当前节点标记为以访问
        visted[vertex] = 1;
        //h1,h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        //将minWeight初始化为大的值,以后遍历时替换
        int minWeight = 10000;
        for (int k = 1; k < graph.vertex; k++) { //有vertex个顶点,就有v-1条边
            for (int i = 0; i < graph.vertex; i++) {//每个访问过的节点
                for (int j = 0; j < graph.vertex; j++) {//和访问过的节点邻接的没有被访问过的点
                    if (visted[i] == 1 && visted[j] == 0 && graph.weight[i][j] < minWeight) {
                        //寻找已经访问过的节点和未访问过的节点间的权值最小的边
                        minWeight = graph.weight[i][j]; //最小路径
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到了一条边是最小的
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + ">权值" + minWeight);
            //将当前h1 h2节点标记为已经访问
            visted[h2] = 1;
            //重置minweight为最大值
            minWeight = 10000;
        }
    }
}

class MGraph {
    int vertex; //节点个数
    char[] data; //节点数据
    int[][] weight; //邻接情况的矩阵

    public MGraph(int vertex) {
        this.vertex = vertex;
        data = new char[vertex];
        weight = new int[vertex][vertex];
    }
}
