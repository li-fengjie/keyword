package test;

import org.python.util.PythonInterpreter;

import java.io.*;

public class Test2 {
    public static void main(String args[]) throws IOException {
        PythonInterpreter interpreter = new PythonInterpreter();
        InputStream filepy = new FileInputStream("D:\\pdf\\__init__.py");
        interpreter.execfile(filepy);
        filepy.close();
    }//main
}