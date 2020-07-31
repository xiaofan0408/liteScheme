package com.xiaofan0408.eval;

/**
 * @author xuzefan  2020/7/31 17:25
 */
public interface Fn {
    Object apply(Object ... args) throws Exception;
}
