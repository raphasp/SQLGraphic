// ------------------------------------ DBADapter.java ---------------------------------------------

// TODO: Change the package to match your project.
package think.sqlgraphic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


// TO USE:
// Change the package (at top) to match your project.
// Search for "TODO", and make the appropriate changes.
public class DBAdapter {

	/////////////////////////////////////////////////////////////////////
	//	Constants & Data
	/////////////////////////////////////////////////////////////////////
	// For logging:
	private static final String TAG = "DBAdapter";
	
	// DB Fields
	public static final String KEY_ROWID = "_id";
	public static final int COL_ROWID = 0;
	/*
	 * CHANGE 1:
	 */
	// TODO: Setup your fields here:
	public static final String KEY_NAME = "name";
	public static final String KEY_HOST = "host";
	public static final String KEY_PORT = "port";
	public static final String KEY_USERNAME = "username";
	public static final String KEY_PASSWORD = "password";
	public static final String KEY_DATABASE = "database";
	public static final String KEY_DESCRIPTION = "description";
	
	// TODO: Setup your query here:
	public static final String KEY_ID="_idserver";
	
	public static final int COL_ROWIDSERVER = 0;
	
	public static final String KEY_QUERY="query";
	
	public static final int COL_NAMES = 1;
	public static final int COL_IDSERVER = 2;
	public static final int COL_QUERYS = 3;
	public static final int COL_DESCRIPTIONS = 4;
	
	// TODO: Setup your field numbers here (0 = KEY_ROWID, 1=...)
	public static final int COL_NAME = 1;
	public static final int COL_HOST = 2;
	public static final int COL_PORT = 3;
	public static final int COL_USERNAME = 4;
	public static final int COL_PASSWORD = 5;
	public static final int COL_DATABASE = 6;
	public static final int COL_DESCRIPTION = 7;
	
	

	
	public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_NAME, KEY_HOST, KEY_PORT, KEY_USERNAME, KEY_PASSWORD, KEY_DATABASE, KEY_DESCRIPTION };
	public static final String[] ALL_KEYSAVE= new String[] {KEY_ROWID, KEY_NAME, KEY_ID, KEY_QUERY, KEY_DESCRIPTION};
	
	// DB info: it's name, and the table we are using (just one).
	public static final String DATABASE_NAME = "ServerINF";
	public static final String DATABASE_TABLE = "dataServerTable";
	public static final String DATABASE_TABLE2 = "SentenceTable";
	// Track DB version if a new version of your app changes the format.
	public static final int DATABASE_VERSION = 3;	
	
	
	
	private static final String DATABASE_CREATE_SQL = 
			"create table " + DATABASE_TABLE 
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "
			+ KEY_NAME + " string not null, "
			+ KEY_HOST + " string not null, "
			+ KEY_PORT + " integer not null, "
			+ KEY_USERNAME + " string not null, "
			+ KEY_PASSWORD + " string, "
			+ KEY_DATABASE + " string, "
			+ KEY_DESCRIPTION + " string"
			// Rest  of creation:
			+ ");";
	
	private static final String DATABASE_CREATE_SQLSEnCE = 
			"create table " + DATABASE_TABLE2 
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "
			+ KEY_NAME + " string not null, "
			+ KEY_ID + " integer not null, "
			+ KEY_QUERY + " string not null, "
			+ KEY_DESCRIPTION + " string"
			// Rest  of creation:
			+ ");";
	
	// Context of application who uses us.
	private final Context context;
	
	private DatabaseHelper myDBHelper;
	private SQLiteDatabase db;

	/////////////////////////////////////////////////////////////////////
	//	Public methods:
	/////////////////////////////////////////////////////////////////////
	
	public DBAdapter(Context ctx) {
		this.context = ctx;
		myDBHelper = new DatabaseHelper(context);
	}
	
	// Open the database connection.
	public DBAdapter open() {
		db = myDBHelper.getWritableDatabase();
		return this;
	}
	
	// Close the database connection.
	public void close() {
		myDBHelper.close();
	}
	
	// Add a new set of values to the database.
	public long insertRowServer(String name, int idx, String query, String description) {
		/*
		 * CHANGE 3:
		 */		
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_ID, idx);
		initialValues.put(KEY_QUERY, query);
		initialValues.put(KEY_DESCRIPTION, description);
		
		// Insert it into the database.
		return db.insert(DATABASE_TABLE2, null, initialValues);
	}
	
	// Add a new set of values to the database.
		public long insertRow(String name, String host, int port, String username, String password, String database, String description) {
			/*
			 * CHANGE 3:
			 */		
			// TODO: Update data in the row with new fields.
			// TODO: Also change the function's arguments to be what you need!
			// Create row's data:
			ContentValues initialValues = new ContentValues();
			initialValues.put(KEY_NAME, name);
			initialValues.put(KEY_HOST, host);
			initialValues.put(KEY_PORT, port);
			initialValues.put(KEY_USERNAME, username);
			initialValues.put(KEY_PASSWORD, password);
			initialValues.put(KEY_DATABASE,	database);
			initialValues.put(KEY_DESCRIPTION, description);
			
			// Insert it into the database.
			return db.insert(DATABASE_TABLE, null, initialValues);
		}
	
	// Delete a row from the database, by rowId (primary key)
	public boolean deleteRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		return db.delete(DATABASE_TABLE, where, null) != 0;
	}
	
	public boolean deleteRowServer(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		return db.delete(DATABASE_TABLE2, where, null) != 0;
	}
	
	public void deleteAll() {
		Cursor c = getAllRows();
		long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
		if (c.moveToFirst()) {
			do {
				deleteRow(c.getLong((int) rowId));				
			} while (c.moveToNext());
		}
		c.close();
	}
	
	// Return all data in the database.
	public Cursor getAllRows() {
		String where = null;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
							where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Get a specific row (by rowId)
	public Cursor getRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	public Cursor getRowServer(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		Cursor c = 	db.query(true, DATABASE_TABLE2, ALL_KEYSAVE, 
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	public Cursor getAllRowServer(long rowId) {
		String where = KEY_ID + "=" + rowId;
		Cursor c = 	db.query(true, DATABASE_TABLE2, ALL_KEYSAVE, 
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}
	
	public boolean searchName(String nameserver) {
		Cursor mcursor=db.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE name=?", new String[]{nameserver});
		if (mcursor !=null) {
			if (mcursor.getCount()<1) {
				return true;
			}
			return false;
		}
		return false;
		
	}
	
	public boolean countServis() {
		String query="Select * From "+ DATABASE_TABLE;
		Cursor mcursor=db.rawQuery(query,null);
		if (mcursor !=null) {
			if (mcursor.getCount()<1) {
				return true;
			}
			return false;
		}
		return false;
		
	}
	
	
	// Change an existing row to be equal to new data.
	public boolean updateRow(long rowId, String name, String host, int port, String username, String password, String database, String description) {
		String where = KEY_ROWID + "=" + rowId;

		/*
		 * CHANGE 4:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_NAME, name);
		newValues.put(KEY_HOST, host);
		newValues.put(KEY_PORT, port);
		newValues.put(KEY_USERNAME, username);
		newValues.put(KEY_PASSWORD,	password);
		newValues.put(KEY_DATABASE,	database);
		newValues.put(KEY_DESCRIPTION, description);
		
		// Insert it into the database.
		return db.update(DATABASE_TABLE, newValues, where, null) != 0;
	}
	
	
	
	/////////////////////////////////////////////////////////////////////
	//	Private Helper Classes:
	/////////////////////////////////////////////////////////////////////
	
	/**
	 * Private class which handles database creation and upgrading.
	 * Used to handle low-level database access.
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase _db) {
			_db.execSQL(DATABASE_CREATE_SQL);
			_db.execSQL(DATABASE_CREATE_SQLSEnCE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading application's database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data!");
			
			// Destroy old database:
			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			
			// Recreate new database:
			onCreate(_db);
		}
	}
}
