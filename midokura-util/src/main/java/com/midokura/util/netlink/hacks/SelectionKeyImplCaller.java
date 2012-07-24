/*
* Copyright 2012 Midokura Europe SARL
*/
package com.midokura.util.netlink.hacks;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.nio.ch.SelectionKeyImpl;

/**
 * This class is a hack to allow using a custom socket implementation until jdk8
 * rolls over across the project.
 *
 * @deprecated since jdk8
 */
public class SelectionKeyImplCaller {

    private static final Logger log = LoggerFactory
        .getLogger(SelectionKeyImplCaller.class);

    static Class selectionKeyClass;
    static Method nioInterestOpsMethod;
    static Method nioReadyOps;
    static Method nioReadyOpsSetter;

    static {
        try {
            selectionKeyClass = Class.forName("sun.nio.ch.SelectionKeyImpl");

            nioInterestOpsMethod = selectionKeyClass.getDeclaredMethod("nioInterestOps");
            nioReadyOps = selectionKeyClass.getDeclaredMethod("nioReadyOps");
            nioReadyOpsSetter = selectionKeyClass.getDeclaredMethod("nioReadyOps", int.class);
        } catch (ClassNotFoundException e) {
            log.error("Error loading class: sun.nio.ch.SelectionKeyImpl", e);
        } catch (NoSuchMethodException e) {
            log.error(
                "Error loading a method reference for class: sun.nio.ch.SelectionKeyImpl",
                e);
        }
    }

    /**
     * This call should be replaced with sk.nioInterestOps().
     *
     * @deprecated since jdk8
     */
    public static int nioInterestOps(SelectionKeyImpl sk) {
        try {
            return (Integer)nioInterestOpsMethod.invoke(sk);
        } catch (Exception e) {
            log.error("Error invoking method: sun.nio.ch.SelectionKeyImpl#nioInterestOps", e);
        }

        return -1;
    }

    /**
     * This call should be replaced with sk.nioReadyOps().
     *
     * @deprecated since jdk8
     */
    public static int nioReadyOps(SelectionKeyImpl sk) {
        try {
            return (Integer)nioReadyOps.invoke(sk);
        } catch (Exception e) {
            log.error("Error invoking method: sun.nio.ch.SelectionKeyImpl#nioInterestOps", e);
        }

        return -1;
    }

    /**
     * This call should be replaced with sk.nioReadyOps(newOps).
     *
     * @deprecated since jdk8
     */
    public static void nioReadyOps(SelectionKeyImpl sk, int newOps) {
        try {
            nioReadyOpsSetter.invoke(sk, newOps);
        } catch (Exception e) {
            log.error("Error invoking method: sun.nio.ch.SelectionKeyImpl#nioInterestOps", e);
        }
    }

}
