package Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dean on 17/10/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.deanduff.temptip/databases/";
    private static String DB_NAME = "temptipdb";

    private SQLiteDatabase database;

    private final Context myContext;

    private DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    private Date d;

    public DatabaseHelper(Context context)
    {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    public void createDatabase() throws IOException
    {
        boolean dbExists = checkDatabase();

        if (dbExists)
        {

        } else
        {
            this.getReadableDatabase();

            try
            {
                copyDataBase();
            } catch (IOException e)
            {
                throw new Error("Error Copying Database");
            }
        }
    }

    private boolean checkDatabase()
    {
        SQLiteDatabase checkDB = null;

        try
        {
            String myPath = DB_PATH + DB_NAME;

            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException e)
        {

        }

        if (checkDB != null)
        {
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException
    {

        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0)
        {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDatabase() throws SQLException
    {
        String myPath = DB_PATH + DB_NAME;
        database = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close()
    {

        if (database != null)
            database.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    public void storeTempValues(double x, double y)    {

        ContentValues values = new ContentValues();

        values.put("x", x);
        values.put("y", y);

        database.insert("Person", null, values);
    }

}
