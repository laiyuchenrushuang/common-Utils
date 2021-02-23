package com.seatrend.utilsdk.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by ly on 2021/1/25 18:02
 */
public class MapUtils<T> {

    /**
     * map是否包含了key（本来不用泛型的 结果Object不得行）
     * @param key key值
     * @param map 集合
     * @return  true 包含了
     *
     * 示例：
     *  HashMap<Integer,String> map = new HashMap();
     *         showLog(" "+ MapUtils.isContain(1,map));
     *         map.put(1,"2");
     *         showLog(" "+ MapUtils.isContain(1,map));
     */
    public static <T, M> boolean  isContainKey(Object key, HashMap<T,M> map){
        return map.containsKey(key);
    }

    /**
     * 同理上
     * map是否包含了value（本来不用泛型的 结果Object不得行）
     * @param value value
     * @param map 集合
     * @return  true 包含了
     */
    public static <T, M> boolean  isContainValue(Object value, HashMap<T,M> map){
        return map.containsValue(value);
    }

    /**
     * 获取所有的key集合
     * @param map
     * @param <M>
     * @param <T>
     * @return
     */
    public static <M,T> Set<T> getKeySet(HashMap<T,M> map){
        return map.keySet();
    }
    /**
     * 获取所有的value集合
     * @param map
     * @param <M>
     * @param <V>
     * @return
     */
    public static <M,V> Collection<V> getValues(HashMap<V,M> map){
        return (Collection<V>) map.values();
    }

    /**
     * 打印Map中的数据
     * @param map
     */
    public static void printJsonMap(Map map){
        Set entrySet = map.entrySet();
        Iterator<Map.Entry<String, Object>> it = entrySet.iterator();
        //最外层提取
        while(it.hasNext()){
            Map.Entry<String, Object> e = it.next();
            System.out.println("Key 值："+e.getKey()+"     Value 值："+e.getValue());
        }
    }
}
