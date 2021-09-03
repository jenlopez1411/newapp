package com.sophia.jlo.newapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        UserDao userDao = (UserDao) Room.databaseBuilder(appContext, UserDatabase.class, UserDatabase.DB_NAME)
                        .allowMainThreadQueries()
                        .build()
                        .getUserDao();

        User user =  new User("csumb", "otter");
        userDao.insert(user);
        User user2 = userDao.getUser(user.getUserName());
        assertEquals(user, user2);

        user2.setPassword("newOtter");

        userDao.update(user2);

        User user3 = userDao.getUser(user.getUserName());

        assertNotEquals(user, user3);
        assertEquals(user2, user3);
        assertEquals("com.sophia.jlo.newapp", appContext.getPackageName());
    }
}