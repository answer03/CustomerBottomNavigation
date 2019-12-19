package com.allenmeng.myapplication;

/**
 * Created by allen_meng on 2019/11/28.
 */
public class ResourceUtils {

    public static int getImageResourceId(String name) {
        R.drawable drawables = new R.drawable();
        //默认的id
        int resId = -1;
        try {
            //根据字符串字段名，取字段//根据资源的ID的变量名获得Field的对象,使用反射机制来实现的
            java.lang.reflect.Field field = R.drawable.class.getField(name);
            //取值
            resId = (Integer) field.get(drawables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resId;
    }
}
