package com.sophia.jlo.newapp;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class User_test {
    @Test
    public void getNameTest(){
        User user = new User("sophia", "password");
        assertEquals("sophia", user.getUserName());
    }

    @Test
    public void getPasswordTest(){
        User userName = new User("test1", "password1");
        assertEquals("password1", userName.getPassword());
    }

}
