package com.xiaofan0408.util;

public class Util {

    public static Double objectToDouble(Object x){
        if(x instanceof Number){
            Number n = (Number)x;
            return n.doubleValue();
        }
        if (x instanceof Boolean) {
            Boolean b = (Boolean)x;
            if (b) {
                return Double.valueOf(1);
            } else {
                return Double.valueOf(0);
            }
        }
        return null;
    }

}
