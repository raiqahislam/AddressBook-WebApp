package org.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressBookTest {

    private BuddyInfo buddy1;
    private BuddyInfo buddy2;
    private BuddyInfo buddy3;

    private AddressBook addressBook;

    @Before
    public void setUp(){

        buddy1 = new BuddyInfo("Alice", "6132834");
        buddy2 = new BuddyInfo("Bob", "613554");
        buddy3 = new BuddyInfo("Carl", "61312305");

        addressBook = new AddressBook();
        addressBook.addBuddy(buddy1);
        addressBook.addBuddy(buddy2);
        addressBook.addBuddy(buddy3);
    }

    @Test
    public void testAddBuddy(){
        assertTrue(addressBook.addressBookSize()==3);

    }

    @Test
    public void testRemoveBuddy(){
        addressBook.removeBuddy(buddy1);
        assertTrue(addressBook.addressBookSize()==2);
    }



}