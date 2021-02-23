package com.seatrend.utilsdk.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import butterknife.internal.Utils;

/**
 * Created by ly on 2021/1/22 18:23
 */
public class ObjCompareObjUtils {


    /**
     * [注意]  不适合List里面的
     * @param class1 原实体类
     * @param class2 已变化实体类
     * @author ym
     * 描述：反射实体类赋值
     */
    public static boolean reflectionAttr(Object class1, Object class2) throws Exception {
        StringBuffer str = new StringBuffer();
        Class clazz1 = Class.forName(class1.getClass().getName());
        Class clazz2 = Class.forName(class2.getClass().getName());
//		获取两个实体类的所有属性
        Field[] fields1 = clazz1.getDeclaredFields();
        Field[] fields2 = clazz2.getDeclaredFields();

//		遍历class1Bean，获取逐个属性值，然后遍历class2Bean查找是否有相同的属性，如有相同则值进行比较
        for (Field f1 : fields1) {
            if (f1.getName().equals("id"))
                continue;
            if (f1.getType() == List.class) {
                continue;
            }
            Object value1 = invokeGetMethod(class1, f1.getName(), null);
            for (Field f2 : fields2) {
                if (f1.getName().equals(f2.getName())) {
                    Object value2 = invokeGetMethod(class2, f2.getName(), null);
                    if (!StringUtils.isNull(value1).equals(StringUtils.isNull(value2))) {
                        str.append(StringUtils.isNull(f1.getName())).append(";");
                        break;//有不一样的 直接跳出
                    }
                    //判断是否是List
//                    if (f1.getType().getName() == "java.util.List") {
//                        //返回List中的类型
//
//                        //判断是否是自定义类
//
//                        //将两个类传入，重新调用reflectionAttra()
//                        reflectionAttr(class1, class2);
//                        continue;
//                    } else {
//                        if (!value1.equals(value2)) {
//                            str += f1.getName().toString() + ";";
//                        }
//                    }


                }
            }
        }

        if (StringUtils.isNull_b(str.toString())){
            return true;
        }else return false;
    }

    /**
     * 执行某个Field的getField方法
     *
     * @param clazz     类
     * @param fieldName 类的属性名称
     * @param args      参数，默认为null
     * @return
     */
    private static Object invokeGetMethod(Object clazz, String fieldName, Object[] args) {
        String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method = null;
        try {
            method = Class.forName(clazz.getClass().getName()).getDeclaredMethod("get" + methodName);
            return method.invoke(clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 执行某个Field的setField方法
     *
     * @param clazz     类
     * @param fieldName 类的属性名称
     * @param args      参数，默认为null
     * @return
     */
    private Object invokeSetMethod(Object clazz, String fieldName, Object[] args) {
        String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method = null;
        try {
            Class[] parameterTypes = new Class[1];
            Class c = Class.forName(clazz.getClass().getName());
            Field field = c.getDeclaredField(fieldName);
            parameterTypes[0] = field.getType();
            method = c.getDeclaredMethod("set" + methodName, parameterTypes);
            return method.invoke(clazz, args);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
