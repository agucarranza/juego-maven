package com.model;

import junit.framework.TestCase;

// Test de integracion

public class RobotTest extends TestCase {

    Robot robot;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        robot = new Robot();
        robot.recibirCarta(new Carta(1,1));
        robot.recibirCarta(new Carta(1,2));
    }

    public void testElegirCartaTirar() {
        assertEquals(1,robot.elegirCartaTirar(1,1));
    }
}