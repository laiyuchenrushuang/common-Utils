package com.seatrend.utilsdk.database.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class CodeTableSQLiteOpenHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "codeTable.db";
    public static String CODE_TABLE_NAME = "CODE_TABLE";
    public static String PHOTO_TABLE_NAME = "PHOTO_TABLE";

    private static int VERSION = 4; // 202001229 数据库更新
    /**
     * "xtlb": "00",
     * "dmlb": "1007",
     * "dmz": "01",
     * "mldh": "01",
     * "dmsm1": "大型汽车",
     * "mlmc": "大型汽车",
     * "dmsm2": "黄底黑字",
     * "dmsm3": null,
     * "zt": "1",
     * "dmsm4": null
     */
    private static String CREATE_TABLE_SQL = "create table %s(id integer primary key autoincrement,xtlb varchar(10),dmlb varchar(10),dmz varchar(10)" +
            ",dmsm1 varchar(100),mlmc varchar(100),dmsm2 varchar(100),dmsm3 varchar(100),zt varchar(10),dmsm4 varchar(100))";
    private static String CAREATE_TABLE = String.format(CREATE_TABLE_SQL, CODE_TABLE_NAME);

    private static String CREATE_PHOTO_TABLE_SQL = "create table %s(id integer primary key autoincrement,lsh varchar(100),xh varchar(100),zpzl varchar(100),zpdz varchar(100),zpsm varchar(100),cffs varchar(100),lrr varchar(10),lrbm varchar(10),zpPath varchar(100),sfzmhm varchar(100),zplx varchar(100),hphm varchar(100),cllx varchar(100),zpbase64 varchar(100),zpcs varchar(100))";

    private static String CAREATE_PHOTO = String.format(CREATE_PHOTO_TABLE_SQL, PHOTO_TABLE_NAME);


    private static CodeTableSQLiteOpenHelper helper = null;

    public CodeTableSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);

    }

    public static CodeTableSQLiteOpenHelper getInstance(Context context) {
        if (helper == null) {
//            helper = new CodeTableSQLiteOpenHelper(new DatabaseContext(MyApplication.Companion.getMyApplicationContext())); //测试用 在sdcard/scexam目录下看db文件
            helper = new CodeTableSQLiteOpenHelper(context);
        }

        return helper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CAREATE_TABLE);
        db.execSQL(CAREATE_PHOTO);
        //Log.i("kkkkk","数据库已创建");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            Log.d("lylog", " SQL升级 删除 PHOTO_TABLE_NAME");
            deleAllTable(db);
            onCreate(db);
        }
    }

    private void deleAllTable(SQLiteDatabase db) {
        db.execSQL("drop table " + " IF EXISTS " + PHOTO_TABLE_NAME);
        db.execSQL("drop table " + " IF EXISTS " + CODE_TABLE_NAME);
    }

    /**
     * 方法1：检查某表列是否存在
     *
     * @param db
     * @param tableName  表名
     * @param columnName 列名
     * @return
     */
    private boolean checkColumnExist1(SQLiteDatabase db, String tableName
            , String columnName) {
        boolean result = false;
        Cursor cursor = null;
        try {
            //查询一行
            cursor = db.rawQuery("SELECT * FROM " + tableName + " LIMIT 0"
                    , null);
            result = cursor != null && cursor.getColumnIndex(columnName) != -1;
        } catch (Exception e) {
            Log.e("lylog", "CodeTableSQLiteOpenHelper..." + e.getMessage());
        } finally {
            if (null != cursor && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return result;
    }

    /**
     * 查询是否存在表
     *
     * @param db
     * @param tableName
     * @return
     */

    public boolean checkColumnExist1(SQLiteDatabase db, String tableName) {
        boolean result = false;
        if (tableName == null) {
            return false;
        }
        Cursor cursor = null;
        try {
            String sql = "select count(*) as c from Sqlite_master  where type ='table' and name ='" + tableName.trim() + "' ";
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    result = true;
                }
            }

        } catch (Exception e) {
            Log.e("lylog", "CodeTableSQLiteOpenHelper..." + e.getMessage());
        }
        return result;
    }
}
