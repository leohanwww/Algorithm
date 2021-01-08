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
        graph.dsj(6);
    }
}

class Graph {
    private char[] vertex; //顶点数组
    private int[][] matrix; //邻接矩阵
    private VistedVertex vv; //已访问顶点

    public Graph(char[] vertexs, int[][] matrix) {
        this.vertex = vertexs;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    //dijkstra算法

    /**
     * description
     *
     * @param index 出发顶点的下标
     * @return void
     */
    public void dsj(int index) {
        vv = new VistedVertex(vertex.length, index);
        update(index); // 更新index顶点到周围顶点的距离和前驱顶点
    }

    //更新index下标顶点到周围顶点的距离和周围顶点的前端顶点,
    private void update(int index) {
        int len = 0;
        //遍历邻接矩阵的index的这一行
        for (int j = 0; j < matrix[index].length; j++) {
            //出发顶点到index的距离+index到j顶点
            len = vv.getDis(index) + matrix[index][j];
            if (!vv.in(j) && len < vv.getDis(j)) {
                //j顶点没有被访问过,len小于出发顶点到j顶点的距离,需要更新
                vv.updatePre(j, index); //更新j顶点的前驱为index
                vv.updateDis(j, len); //更新出发顶点到j顶点的距离
            }

        }
    }
}

// 已访问顶点集合
class VistedVertex {
    //记录顶点被访问我 1访问过, 0未访问
    public int[] already_arr;
    //每个顶点的前驱节点
    public int[] pre_visted;
    //记录出发点到其他所有顶点的距离,G点出发,就记录G点到其他点的距离
    public int[] dis;

    /**
     * description
     *
     * @param length 顶点的个数
     * @param index  出发顶点对应的下标
     * @return
     */
    public VistedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visted = new int[length];
        this.dis = new int[length];
        //初始dis数组
        Arrays.fill(dis, 65535);
        this.already_arr[index] = 1; //设置出发顶点被访问
        dis[index] = 0; //自己到自己距离是0
    }

    /**
     * 判断index顶点是否被访问过
     * description
     *
     * @param index 顶点在数组对应的下标
     * @return boolean 访问过返回true
     */
    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    /**
     * 更新出发顶点到index顶点的距离
     * description
     *
     * @param index
     * @param len
     * @return void
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新顶点pre的前驱为index的顶点
     * description
     *
     * @param pre
     * @param index
     * @return void
     */
    public void updatePre(int pre, int index) {
        pre_visted[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     * description
     *
     * @param index
     * @return int
     */
    public int getDis(int index) {
        return dis[index];
    }
}
