package org.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BuddyInfoTest{

    private BuddyInfo buddyInfo;

    @Before
    public void setUp(){

        buddyInfo = new BuddyInfo("Alice", "6132834");


    }

    @Test
    public void testConstructor() {
        assertEquals("Alice",buddyInfo.getName());
        assertEquals("6132345",buddyInfo.getPhoneNum());
    }

}