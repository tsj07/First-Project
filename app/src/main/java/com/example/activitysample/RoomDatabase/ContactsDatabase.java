package com.example.activitysample.RoomDatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ContactsData.class}, exportSchema = false, version = 1)
public abstract class ContactsDatabase extends RoomDatabase {

    public abstract ContactsDAO contactsDAO(Context context);

    private static volatile ContactsDatabase INSTANCE;
    private static final int noOfThreads = 1;
    public static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(noOfThreads);

    public static ContactsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ContactsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ContactsDatabase.class,
                                    "ContactsDatabase")
                            .addCallback(roomDatabaseCallback)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            databaseExecutor.execute(new Runnable() {
                @Override
                public void run() {
                }
            });
        }
    };

}
