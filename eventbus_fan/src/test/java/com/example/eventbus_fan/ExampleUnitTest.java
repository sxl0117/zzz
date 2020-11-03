package com.example.eventbus_fan;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void text() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> aClass = Class.forName("com.example.eventbus_fan.DemoInfo");
        //System.out.println(aClass.hashCode());
        Constructor<?> constructor = aClass.getConstructor();
        DemoInfo o = (DemoInfo) constructor.newInstance();
        // o.add(3, 5);
        Method item = aClass.getMethod("item", String.class);
        item.invoke(o, "哈哈哈哈哈哈");
    }
}