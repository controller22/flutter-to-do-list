package ex01;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class MyRefApp {
    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
       Scanner sc = new Scanner(System.in);
       String path = sc.nextLine();
        // String path = "/joinForm";


        UserController uc = new UserController();

        Method[] methods = uc.getClass().getDeclaredMethods();
        for(int i=0; i<methods.length; i++){
            Method mt = methods[i];
            
            Annotation anno = mt.getDeclaredAnnotation(RequestMapping.class);
            RequestMapping rm = (RequestMapping) anno;

            if (rm.uri().equals(path)) {
                mt.invoke(uc);
            }
        }
    }
}