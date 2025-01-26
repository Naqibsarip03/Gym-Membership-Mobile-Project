package com.example.project.BMI_DATABASE;
import android.content.Context;
import androidx.room.Room;

public class DatabaseClient {
    private Context context;
    private static DatabaseClient instance;

    private BMIDatabase bmiDatabase;

    private DatabaseClient(Context context) {
        this.context = context;

        // Create the Room database
        bmiDatabase = Room.databaseBuilder(context, BMIDatabase.class, "bmi_database")
                .allowMainThreadQueries() // Avoid in production, use background threads instead
                .build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    public BMIDatabase getDatabase() {
        return bmiDatabase;
    }
}
