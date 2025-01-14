package com.example.project.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import androidx.core.graphics.ColorUtils;

import java.util.ArrayList;
import java.util.List;

public class DBSql extends SQLiteOpenHelper {
    public static final String dbName = "dbMembership";
    public static final int dbVersion = 1;
    public static final String tblUser = "tblUser";
    public static final String tblMemberType = "tblMembershipType";
    public static final String colUserId = "userId";
    public static final String colName = "name";
    public static final String colPass = "password";
    public static final String colPhone = "phoneNo";
    public static final String colEmail = "email";
    public static final String colMemberId = "membershipId";
    public static final String colStatus = "status";
    public static final String colMemberTypeId = "membershipTypeId";
    public static final String colPrice = "price";
    public static final String colDuration = "duration";

    public static final String createTableMemberType = "CREATE TABLE " + tblMemberType + "(" + colMemberTypeId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + colName + " TEXT, " + colPrice + " TEXT, " + colDuration + " TEXT)";
    public static final String createTableUser = "CREATE TABLE " + tblUser + "(" + colUserId + " INTEGER PRIMARY KEY AUTOINCREMENT, " + colName + " TEXT, " + colPass + " TEXT, " + colEmail + " TEXT, " + colPhone + " TEXT, " + colMemberId + " INTEGER, " + colStatus + " TEXT, FOREIGN KEY (" + colMemberId + ") REFERENCES " + tblMemberType + "(" + colMemberTypeId + "))";
    public static final String dropTableUser = "DROP TABLE IF EXISTS " + tblUser;
    public static final String dropTableMember = "DROP TABLE IF EXISTS " + tblMemberType;
    public DBSql(Context context){
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableMemberType);
        db.execSQL(createTableUser);
        insertDefaultMemberTypes(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dropTableUser);
        db.execSQL(dropTableMember);
        onCreate(db);
    }

    // Function to insert default member types
    private void insertDefaultMemberTypes(SQLiteDatabase db) {
        insertMemberType(db, "Bronze", "10.00", "1 Month");
        insertMemberType(db, "Silver", "20.00", "3 Months");
        insertMemberType(db, "Gold", "30.00", "6 Months");
    }
    // Function to insert a single member type
    public void insertMemberType(SQLiteDatabase db, String name, String price, String duration) {
        ContentValues values = new ContentValues();
        values.put(colName, name);
        values.put(colPrice, price);
        values.put(colDuration, duration);
        db.insert(tblMemberType, null, values);
    }

    public int insertUser(String name, String password, String email, String phoneNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(colName, name);
        contentValues.put(colPass, password);
        contentValues.put(colEmail, email);
        contentValues.put(colPhone, phoneNo);
        return (int) db.insert(tblUser, null, contentValues);
    }

    public UserModel getUserAuthentication(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + tblUser + " WHERE " + colName + " = ? AND " + colPass + " = ?",
                new String[]{username, password}
        );

        if (cursor != null && cursor.moveToFirst()) {
            // Retrieve data from the cursor
            int userId = cursor.getInt(cursor.getColumnIndexOrThrow("userId"));
            String userName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String userPassword = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            String phoneNo = cursor.getString(cursor.getColumnIndexOrThrow("phoneNo"));
            String membershipId = cursor.getString(cursor.getColumnIndexOrThrow("membershipId"));
            String status = cursor.getString(cursor.getColumnIndexOrThrow("status"));

            // Close cursor to avoid memory leaks
            cursor.close();

            // Return the populated TempData object
            return new UserModel(userId, userName, userPassword, email, phoneNo, membershipId, status);
        }

        if (cursor != null) {
            cursor.close();
        }

        // Return null if no matching user is found
        return null;
    }

    public UserModel getUserDetails(int userId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + tblUser + " WHERE " + colUserId + " = ?",
                new String[]{String.valueOf(userId)}
        );
        if (cursor != null && cursor.moveToFirst()){
            String userName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String userPassword = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            String phoneNo = cursor.getString(cursor.getColumnIndexOrThrow("phoneNo"));
            String membershipId = cursor.getString(cursor.getColumnIndexOrThrow("membershipId"));
            String status = cursor.getString(cursor.getColumnIndexOrThrow("status"));
            cursor.close();
            return new UserModel(userId, userName, userPassword, email, phoneNo, membershipId, status);
        }
        if (cursor != null){
            cursor.close();
        }
        return null;
    }

    public List<Membership> getAllMemberships() {
        List<Membership> memberships = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + tblMemberType, null);

//        if (cursor.moveToFirst()) {
//            // Define a list of colors to cycle through
//            int[] colors = {
//                    Color.parseColor("#CD7F32"), // Bronze
//                    Color.parseColor("#C0C0C0"), // Silver
//                    Color.parseColor("#FFD700"), // Gold
//            };
//
//            int colorIndex = 0; // Start with the first color
//
//            do {
//                int id = cursor.getInt(cursor.getColumnIndexOrThrow("membershipTypeId")); // Replace with your actual column name for the ID
//                String name = cursor.getString(cursor.getColumnIndexOrThrow("name")); // Replace with your actual column name
//                String price = cursor.getString(cursor.getColumnIndexOrThrow("price")); // Replace with your actual column name
//                String duration = cursor.getString(cursor.getColumnIndexOrThrow("duration")); // Replace with your actual column name
//
//                // Assign a color from the array based on the colorIndex
//                int color = colors[colorIndex % colors.length];
//
//                memberships.add(new Membership(id, name, price, duration, color));
//
//                // Increment the colorIndex to move to the next color
//                colorIndex++;
//
//            } while (cursor.moveToNext());
//        }
        if (cursor.moveToFirst()) {
            // Define base colors for each membership type
            int[] baseColors = {
                    Color.parseColor("#CD7F32"), // Bronze
                    Color.parseColor("#C0C0C0"), // Silver
                    Color.parseColor("#FFD700"), // Gold
            };

            int colorIndex = 0; // Start with the first color

            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("membershipTypeId")); // Replace with your actual column name for the ID
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name")); // Replace with your actual column name
                String price = cursor.getString(cursor.getColumnIndexOrThrow("price")); // Replace with your actual column name
                String duration = cursor.getString(cursor.getColumnIndexOrThrow("duration")); // Replace with your actual column name

                // Get the base color for the current membership
                int baseColor = baseColors[colorIndex % baseColors.length];

                // Generate a gradient color (lighter and darker shades of the base color)
                int lighterColor = adjustColorBrightness(baseColor, 1.5f); // Adjust brightness (lighter)
                int darkerColor = adjustColorBrightness(baseColor, 0.7f); // Adjust brightness (darker)

                // Create a gradient drawable
                GradientDrawable gradientDrawable = new GradientDrawable(
                        GradientDrawable.Orientation.LEFT_RIGHT, // Gradient direction
                        new int[]{darkerColor, baseColor, lighterColor} // Gradient colors
                );

                // Assign the gradient drawable to your membership
                memberships.add(new Membership(id, name, price, duration, gradientDrawable));

                // Increment the colorIndex to move to the next color
                colorIndex++;

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return memberships;
    }
    // Method to adjust brightness of a color
    private int adjustColorBrightness(int color, float factor) {
        int r = (int) Math.min(255, Color.red(color) * factor);
        int g = (int) Math.min(255, Color.green(color) * factor);
        int b = (int) Math.min(255, Color.blue(color) * factor);
        return Color.rgb(r, g, b);
    }

}
