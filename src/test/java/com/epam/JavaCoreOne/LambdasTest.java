package com.epam.JavaCoreOne;

import static com.epam.lambdas.handlers.LambdasHandler.getListOfA;

import com.epam.lambdas.entities.A;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class LambdasTest
{

    private final static Logger logger = Logger.getLogger(LambdasTest.class);

    /**
     * Rigorous Test :-)
     */
    @Test
    public void checkCollector()
    {
        A firstA = new A();
        A secondA = new A();
        A thirdA = new A();
        A lastA = new A();
        firstA.stat1(4);
        secondA.stat2(501);
        thirdA.stat2(18);
        lastA.stat1(918);

        Collection<A> myCollections = new ArrayList<>();
        myCollections.add(firstA);
        myCollections.add(secondA);
        myCollections.add(thirdA);
        myCollections.add(lastA);

        List<A> listOfA = new ArrayList<>();
        listOfA.add(firstA);
        listOfA.add(thirdA);
        listOfA.add(secondA);
        listOfA.add(lastA);

        /** Листы равны */
        Assert.assertArrayEquals(listOfA.toArray(), getListOfA(myCollections).toArray());

        /** Удаляем два последних элемента */
        listOfA.remove(3);
        listOfA.remove(2);

        /** Добавляем в другом порядке */
        listOfA.add(lastA);
        listOfA.add(secondA);

        /** Листы не равны */
        Assert.assertNotEquals(listOfA.toArray(), getListOfA(myCollections).toArray());

        logger.info("Текущий массив: ");
        listOfA.forEach(logger::info);
    }
}
