package com.example.natsumendoza.infodb.database;

import android.provider.BaseColumns;

/**
 * Created by natsumendoza on 4/23/2017.
 */

public class InfoContract {

    private InfoContract () {}

    public static class InfoEntry implements BaseColumns {

        public static final String TABLE_NAME = "info";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_MOBILE = "mobile";

    }

}
