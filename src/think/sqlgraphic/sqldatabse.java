package think.sqlgraphic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class sqldatabse {
	
	public static final String ID_Server="_idserver";
	public static final String NAME_Server="name";
	public static final String HOST_Server="host";
	public static final String PORT_Server="port";
	public static final String USERNAME_Server="username";
	public static final String PASSWORD_Server="password";
	public static final String DATABASE_Server="database";
	public static final String DESCRIPTION_Server="description";
	
	public static final String ID_Sentense="_idsentence";
	public static final String NAME_Sentense="name";
	public static final String IDSERVER_Sentense="idserver";
	public static final String QUERY_Sentense="query";
	public static final String DESCRIPTION_Sentense="description";
	
	private static final String N_DB="ServerINF";
	private static final String N_TABLAServer="DataServer";
	private static final String N_TABLASentence="Sentence";
	private static int VERSION_DB= 1;
	
	private BDHelper nHelper;
	private final Context ncontext;
	private SQLiteDatabase nDB;
	
	private static class BDHelper extends SQLiteOpenHelper{

		public BDHelper(Context context) {
			super(context, N_DB, null, VERSION_DB);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + N_TABLAServer +"(" +
				ID_Server + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				NAME_Server + " TEXT NOT NULL, " + 
				HOST_Server + " TEXT NOT NULL, " + 
				PORT_Server + " TEXT NOT NULL, " + 
				USERNAME_Server + " TEXT NOT NULL, " + 
				PASSWORD_Server + " TEXT , " + 
				DATABASE_Server + " TEXT, " + 
				DESCRIPTION_Server + " TEXT );"
			);
			
			db.execSQL("CREATE TABLE " + N_TABLASentence +"(" +
				ID_Sentense + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				NAME_Sentense + " TEXT NOT NULL, " + 
				IDSERVER_Sentense + " TEXT NOT NULL, " + 
				QUERY_Sentense + " TEXT NOT NULL, " +  
				DESCRIPTION_Sentense + " TEXT );"
			);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + N_TABLAServer);
			onCreate(db);
		}
		
	}
	
	public sqldatabse(Context c){
		ncontext=c;
	}

	public sqldatabse abrir(){
		nHelper=new BDHelper(ncontext);
		nDB= nHelper.getWritableDatabase();
		return this;
	}
}
