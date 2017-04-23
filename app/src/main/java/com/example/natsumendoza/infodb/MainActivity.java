package com.example.natsumendoza.infodb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.natsumendoza.infodb.database.InfoContract;
import com.example.natsumendoza.infodb.database.InfoDbHelper;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    private EditText etName;
    private EditText etAge;
    private EditText etEmail;
    private EditText etMobile;

    private Button btnSave;

    private ListView lvInfos;
    private InfoDbHelper infoDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etMobile = (EditText) findViewById(R.id.etMobile);

        btnSave = (Button) findViewById(R.id.btnSave);

        lvInfos = (ListView) findViewById(R.id.lvInfos);

        infoDb = new InfoDbHelper(getBaseContext());

        btnSave.setOnClickListener(new ButtonSaveClickListener());



    }

    private Cursor readInfos(SQLiteDatabase db) {
        db = infoDb.getReadableDatabase();

        String[] projection = {
                InfoContract.InfoEntry._ID,
                InfoContract.InfoEntry.COLUMN_NAME_NAME,
                InfoContract.InfoEntry.COLUMN_NAME_AGE,
                InfoContract.InfoEntry.COLUMN_NAME_EMAIL,
                InfoContract.InfoEntry.COLUMN_NAME_MOBILE
        };

        Cursor cursor = db.query(
                InfoContract.InfoEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        return cursor;
    }

    class ButtonSaveClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            String etNameStr = etName.getText().toString();
            String etAgeStr = etAge.getText().toString();
            String etEmailStr = etEmail.getText().toString();
            String etMobileStr = etMobile.getText().toString();

            // Gets the data repository in write mode
            SQLiteDatabase db = infoDb.getWritableDatabase();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(InfoContract.InfoEntry.COLUMN_NAME_NAME, etNameStr);
            values.put(InfoContract.InfoEntry.COLUMN_NAME_AGE, etAgeStr);
            values.put(InfoContract.InfoEntry.COLUMN_NAME_EMAIL, etEmailStr);
            values.put(InfoContract.InfoEntry.COLUMN_NAME_MOBILE, etMobileStr);

            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(InfoContract.InfoEntry.TABLE_NAME, null, values);

            Log.d(TAG, "id of the new inserted ID: " + newRowId);

        }
    }

}
