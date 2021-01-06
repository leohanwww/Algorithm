package com.atguigu.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台,放入到map
        HashMap<String, HashSet<String>> brodcasts = new HashMap<>();
        //放入电台
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        brodcasts.put("k1", hashSet1);
        brodcasts.put("k2", hashSet2);
        brodcasts.put("k3", hashSet3);
        brodcasts.put("k4", hashSet4);
        brodcasts.put("k5", hashSet5);
        System.out.println(brodcasts);
        //所有地区集合
        HashSet<String> allAreas = new HashSet<>();
        for (Map.Entry<String, HashSet<String>> entry : brodcasts.entrySet()) {
            HashSet<String> strings = entry.getValue();
            for (String string : strings) {
                allAreas.add(string);
            }
        }
        System.out.println(allAreas);
        //存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();
        //临时变量,存放遍历电台过程中,覆盖的地区和没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();
        //定义maxKey,指向当前遍历过程中最大覆盖的key:如k1,k2....
        String maxKey = null;
        while (allAreas.size() != 0) { //allAreas不为0,则表示还没有覆盖到所有地区
            //maxkey置空
            maxKey = null;
            //遍历brodcasts,取出对应的key
            for (String key : brodcasts.keySet()) {
                tempSet.clear();
                //当前key能覆盖的地区
                HashSet<String> areas = brodcasts.get(key);
                tempSet.addAll(areas);
                //求tempset和allareas的交集,交集赋值给tempset
                tempSet.retainAll(allAreas);
                //当前集合包含的未覆盖地区的数量比maxkey指向的集合未覆盖地区还要多,
                //需要重置maxkey
                //tempSet.size() > brodcasts.get(maxKey).size())体现出贪心算法的特点
                if (tempSet.size() > 0 &&
                        (maxKey == null || tempSet.size() > brodcasts.get(maxKey).size())) {
                    maxKey = key;
                }

            }
            //将maxkey加入到selects
            if (maxKey != null) {
                selects.add(maxKey);
                //将maxkey指向的广播覆盖地区从allareas清除
                allAreas.removeAll(brodcasts.get(maxKey));
            }
        }
        System.out.println("得到的选择结果:" + selects);

    }


}
