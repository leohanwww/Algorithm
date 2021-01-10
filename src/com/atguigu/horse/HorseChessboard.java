package com.atguigu.horse;

import java.awt.*;
import java.util.ArrayList;

//马踏棋盘问题
public class HorseChessboard {

    private static int X; //棋盘的列数
    private static int Y; //棋盘的行数
    //创建数组,记录棋盘的各个位置是否被访问过
    private static boolean visited[];
    //标记棋盘的所有点都被访问过
    private static boolean finished; //为true表示成功

    public static void main(String[] args) {
        System.out.println("骑士周游算法开始运行!");
        //测试骑士周游算法
        X = 8;
        Y = 8;
        int row = 1; //马儿初始位置
        int column = 1;
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y]; //初始值都是false
        long t1 = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long t2 = System.currentTimeMillis();
        System.out.println("使用的时间是:" + (t2 - t1) + "毫秒");
        //输出棋盘的最后情况
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    /**
     * description
     * 骑士周游问题算法
     *
     * @param chessboard 棋盘
     * @param row        马儿当前位置的行,从0开始
     * @param column     马尔当前位置列
     * @param step       是第几步,初始位置就是第1步
     * @return void
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        //标记是第几步,开始是1
        chessboard[row][column] = step;
        //从图上看 row(4)*X(8)+col(4) = 36,标记当前位置36访问过
        visited[row * X + column] = true;
        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));
        //遍历ps
        while (!ps.isEmpty()) {
            Point p = ps.remove(0); //取出一个下一个可以走的位置
            //判断该点是否已经访问过
            if (!visited[p.y * X + p.x]) {//没有被访问过
                //回溯
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }
        //判断是否完成任务
        if (step < X * Y && !finished) {
            //没有达到步数,表示没有完成任务,将整个棋盘置0
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    /**
     * description
     * 计算马儿能走哪些位置,最多8个位置
     *
     * @param curPoint 当前位置
     * @return java.util.ArrayList<java.awt.Point>
     */
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point(); //马儿将要走到的位置
        //表示马儿可以走'5'这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //表示马儿可以走'6'这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //表示马儿可以走'7'这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //表示马儿可以走'0'这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //表示马儿可以走'1'这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //表示马儿可以走'2'这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //表示马儿可以走'3'这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //表示马儿可以走'4'这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }
}
