package com.atguigu.binarysearchnorecursion;

public class BInarySearchNoRecu {
    public static void main(String[] args) {
        int[] arr = {1,3,8,10,11,67,100};
        int index = binarySearch(arr, 100);
        System.out.println("目标数的位置:"+index);
    }

    //二分查找非递归
    /**
     * description
     *
     * @param arr    带查找数组,默认升序排序
     * @param target 要查找的数
     * @return int 返回对应下标,-1表示没找到
     */
    public static int binarySearch(int arr[], int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) { //中间数大于目标数
                right = mid - 1;  //向左边查找
            } else { //中间数小于目标数
                left = mid + 1;  //向右边查找
            }
        }
        return -1;
    }
}
