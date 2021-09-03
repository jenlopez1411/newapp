package com.sophia.jlo.newapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public static final String DB_NAME = "USER_DATABASE";
    //private static UserDatabase instance;
    private static UserDatabase object;
    //public abstract UserDao userDao();
    public abstract UserDao getUserDao();

    public UserDatabase UserDatabase(final Context context) {
        if(object == null){
            object = Room.databaseBuilder(context, UserDatabase.class, DB_NAME).build();

        }
        return object;
    }
//    //create db
//    public static synchronized UserDatabase getInstance(Context context){
//        if(instance == null){
//            instance = Room.databaseBuilder(context.getApplicationContext(),
//                    UserDatabase.class, "user_database")
//                    .fallbackToDestructiveMigration()
//                    .addCallback(roomCallback)
//                    .build();
//        }
//        return instance;
//    }
//
//    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//            new PopulateDbAsyncTask(instance).execute();
//        }
//    };

//    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
//       private UserDao userDao;
//
//       private PopulateDbAsyncTask(UserDatabase db){
//           userDao = db.getUserDao();
//       }
//        @Override
//        protected Void doInBackground(Void... voids){
//           userDao.insert(new User("csumb1", "otter1"));
//           userDao.insert(new User("csumb2", "otter2"));
//           userDao.insert(new User("csumb3", "otter3"));
//           userDao.insert(new User("csumb4", "otter4"));
//           userDao.insert(new User("csumb5", "otter5"));
//           userDao.insert(new User("csumb6", "otter6"));
//           userDao.insert(new User("csumb7", "otter7"));
//           userDao.insert(new User("csumb8", "otter8"));
//           userDao.insert(new User("csumb9", "otter9"));
//           userDao.insert(new User("csumb10", "otter10"));
//           return null;
//        }
//    }
}
