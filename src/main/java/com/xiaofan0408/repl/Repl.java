package com.xiaofan0408.repl;

import com.xiaofan0408.eval.Env;
import com.xiaofan0408.eval.Eval;
import com.xiaofan0408.lex.Lexer;
import com.xiaofan0408.lex.Token;
import com.xiaofan0408.parser.Node;
import com.xiaofan0408.parser.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author xuzefan  2020/7/30 10:54
 */
public class Repl {

    public void run() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Lexer lexer = new Lexer();
        Parser parser = new Parser();
        while (true){
            System.out.printf(">");
            String line = bufferedReader.readLine();
            if (".exit".equals(line)) {
                System.exit(0);
            }
            List<Token> tokens = lexer.lex(line);
            try {
                Node node = parser.parse(tokens);
                Eval eval = new Eval();
                Object result = eval.eval(node, Env.STANDARD_ENV);
                if (result != null) {
                    System.out.printf(result.toString());
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println("解析错误:" + e.getMessage());
            }

        }
    }

}
