package com.epam.memory.overflow;

import com.epam.JavaCoreOne.transport.service.TransportService;
import com.epam.memory.overflow.entity.EntityFirst;
import javassist.ClassPool;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Overflow {

    private static final Logger logger = Logger.getLogger(Overflow.class);
    private static TransportService transport = new TransportService();
    static ClassPool cp = ClassPool.getDefault();

    public static void main(String[] args) throws Exception {
        outMemoryFirst();
//        outMemorySecond();
//        callStackOverflowFirst("str");
//        callStackOverflowSecond();
    }

    private static void outMemoryFirst() {
        logger.info("Java heap space throws");
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 1_000_000_000; i++) {
            list.add(i*1000);
        }
    }

    private static void outMemorySecond() throws Exception {
        logger.info("Java heap space throws");

        for (int i = 0; i < 1_000_000_000; i++) {
            Class c = cp.makeClass("com.epam.memory.overflow" + i).toClass();
        }
    }

    //с рекурсией
    private static void callStackOverflowFirst(String s) {
        callStackOverflowFirst(s);
    }

    //без рекурсии
    private static void callStackOverflowSecond() {
        EntityFirst obj = new EntityFirst();
    }
}
