package com.xiaofan0408;

import com.xiaofan0408.repl.Repl;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        Repl repl = new Repl();
        repl.run();
    }
}
