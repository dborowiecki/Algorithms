package com.dmn;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

public class Test {

    public static void runTestForAllMethods(String pattern, String text){
        for(Method m: PatternFinder.class.getDeclaredMethods()){
            if(Modifier.isPublic(m.getModifiers()))
                testMethod(m.getName(), pattern, text);
        }
    }

    public static void printResault(String method, List indexes, long time){
        System.out.println("Method: "+method+
                "\nIndexes: "+indexes+
                "\nDuration time: " +time+"\n");
    }

    public static void testMethod(String methodName, String pattern, String text){
        try {
            Method method = PatternFinder.class.getMethod(methodName, String.class,String.class);
            long start = System.nanoTime();
            List indexes =  (List)method.invoke("", pattern, text);
            long elapse = System.nanoTime() - start;
            printResault(method.getName(), indexes, elapse);
        }
        catch (Exception e){
            System.out.println("Error "+e);
        }
    }

}
