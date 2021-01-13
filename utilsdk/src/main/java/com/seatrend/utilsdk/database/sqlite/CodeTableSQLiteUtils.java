//package com.seatrend.utilsdk.database.sqlite;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.text.TextUtils;
//import android.util.Log;
//
//
//import com.seatrend.utilsdk.entity.CodeEntity;
//import com.seatrend.utilsdk.entity.PhotoEntity;
//import com.seatrend.utilsdk.httpserver.common.Constants;
//import com.seatrend.utilsdk.utils.StringUtils;
//
//import org.greenrobot.greendao.DbUtils;
//import org.jetbrains.annotations.NotNull;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CodeTableSQLiteUtils {
//
//
//    //添加数据
//
//    /**
//     * "xtlb": "00",
//     * "dmlb": "1007",
//     * "dmz": "01",
//     * "mldh": "01",
//     * "dmsm1": "大型汽车",
//     * "mlmc": "大型汽车",
//     * "dmsm2": "黄底黑字",
//     * "dmsm3": null,
//     * "zt": "1",
//     * "dmsm4": null
//     *
//     * @param
//     */
//    public static void insert(List<CodeEntity.DataBean> data) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        for (CodeEntity.DataBean dataBean : data) {
//            cv.clear();
//            cv.put("xtlb", StringUtils.isNull(dataBean.getXtlb()));
//            cv.put("dmlb", StringUtils.isNull(dataBean.getDmlb()));
//            cv.put("dmz", StringUtils.isNull(dataBean.getDmz()));
//            cv.put("dmsm1", StringUtils.isNull(dataBean.getDmsm1()));
//            cv.put("mlmc", StringUtils.isNull(dataBean.getXtlb()));
//            cv.put("dmsm2", StringUtils.isNull(dataBean.getDmsm2()));
//            cv.put("dmsm3", StringUtils.isNull(dataBean.getDmsm3()));
//            cv.put("zt", StringUtils.isNull(dataBean.getZt()));
//            cv.put("dmsm4", StringUtils.isNull(dataBean.getDmsm4()));
//            db.insert(CodeTableSQLiteOpenHelper.CODE_TABLE_NAME, null, cv);
////            insertOrUpdate(CodeTableSQLiteOpenHelper.CODE_TABLE_NAME, cv, db);
//        }
//
//        db.close();
//    }
//
//    //是插入还是替换原来的值
//    private static void insertOrUpdate(String tableName, ContentValues cv, SQLiteDatabase db) {
//        if (queryDMZHas(tableName, db, cv)) {
//            db.update(tableName, cv, "xtlb=? and dmlb=? and dmz=? ", new String[]{cv.getAsString("xtlb"), cv.getAsString("dmlb"), cv.getAsString("dmz")});
//        } else {
//            db.insert(tableName, null, cv);
//        }
//    }
//
//    /**
//     * 查询代码值是否存在
//     *
//     * @param tableName
//     * @param db
//     * @param cv
//     * @return
//     */
//    private static boolean queryDMZHas(String tableName, SQLiteDatabase db, ContentValues cv) {
//        Cursor query = db.query(tableName, null, "dmlb=? and dmz=? and xtlb=?", new String[]{cv.getAsString("dmlb"), cv.getAsString("dmz"), cv.getAsString("xtlb")}, null, null, null, null);
//        while (query.moveToNext()) {
//            Log.d("lylog", " query dmz = " + query.getString(query.getColumnIndex("dmz")));
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * "xtlb": "00",
//     * "dmlb": "1007",
//     * "dmz": "01",
//     * "mldh": "01",
//     * "dmsm1": "大型汽车",
//     * "mlmc": "大型汽车",
//     * "dmsm2": "黄底黑字",
//     * "dmsm3": null,
//     * "zt": "1",
//     * "dmsm4": null
//     *
//     * @param
//     */
//    //查询数据
//    public static List<CodeEntity.DataBean> queryByDMLB(String DMLB) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        List<CodeEntity.DataBean> list = new ArrayList<>();
//
//        //db.insert(CodeTableSQLiteOpenHelper.CODE_TABLE_NAME,null,cv);//"dmlb="+DMLB
//        Cursor query = db.query(CodeTableSQLiteOpenHelper.CODE_TABLE_NAME, null, "dmlb=?", new String[]{DMLB}, null, null, null, null);
//        while (query.moveToNext()) {
//
//            String dmlb = query.getString(query.getColumnIndex("dmlb"));
//            String dmz = query.getString(query.getColumnIndex("dmz"));
//            String dmsm1 = query.getString(query.getColumnIndex("dmsm1"));
//            CodeEntity.DataBean dataBean = new CodeEntity.DataBean();
//            dataBean.setDmlb(dmlb);
//            dataBean.setDmz(dmz);
//            dataBean.setDmsm1(dmsm1);
//            list.add(dataBean);
//        }
//        db.close();
//        query.close();
//        return list;
//    }
//
//
//    //查询数据 DMLB 和 dmz 种类查询
//    public static List<CodeEntity.DataBean> queryByDmlbAndDmz(String DMLB, String Dmz) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        List<CodeEntity.DataBean> list = new ArrayList<>();
//
//        Cursor query = db.query(CodeTableSQLiteOpenHelper.CODE_TABLE_NAME, new String[]{}, "dmlb=? and dmz=?", new String[]{DMLB, Dmz}, null, null, null, null);
//        while (query.moveToNext()) {
//            String dmlb = query.getString(query.getColumnIndex("dmlb"));
//            String dmz = query.getString(query.getColumnIndex("dmz"));
//            String dmsm1 = query.getString(query.getColumnIndex("dmsm1"));
//            CodeEntity.DataBean dataBean = new CodeEntity.DataBean();
//            dataBean.setDmlb(dmlb);
//            dataBean.setDmz(dmz);
//            dataBean.setDmsm1(dmsm1);
//            list.add(dataBean);
//        }
//        db.close();
//        query.close();
//
//        return list;
//    }
//
//    //查询数据 DMLB 和 dmsm1 种类查询
//    public static String queryByDmlbAndDmsm(String DMLB, String dmsm1) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//
//        Cursor query = db.query(CodeTableSQLiteOpenHelper.CODE_TABLE_NAME, new String[]{}, "dmlb=? and dmsm1=?", new String[]{DMLB, dmsm1}, null, null, null, null);
//        while (query.moveToNext()) {
//            String dmz = query.getString(query.getColumnIndex("dmz"));
//            if (!TextUtils.isEmpty(dmz)) {
//                return dmz;
//            }
//        }
//        return "";
//    }
//
//    //查询数据 DMLB 和 dmz 种类查询具体值
//    public static String queryByDmlbAndDmzGetDmsm(String DMLB, String dmz) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//
//        Cursor query = db.query(CodeTableSQLiteOpenHelper.CODE_TABLE_NAME, new String[]{}, "dmlb=? and dmz=?", new String[]{DMLB, dmz}, null, null, null, null);
//        while (query.moveToNext()) {
//            String dmsm1 = query.getString(query.getColumnIndex("dmsm1"));
//            if (!TextUtils.isEmpty(dmsm1)) {
//                return dmsm1;
//            }
//        }
//        return "";
//    }
//
//    public static List<CodeEntity.DataBean> queryAllDMZ() {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        List<CodeEntity.DataBean> list = new ArrayList<>();
//
//        Cursor query = db.query(CodeTableSQLiteOpenHelper.CODE_TABLE_NAME, new String[]{}, null, null, null, null, null, null);
//        while (query.moveToNext()) {
//            String dmlb = query.getString(query.getColumnIndex("dmlb"));
//            String dmz = query.getString(query.getColumnIndex("dmz"));
//            String dmsm1 = query.getString(query.getColumnIndex("dmsm1"));
//            CodeEntity.DataBean dataBean = new CodeEntity.DataBean();
//            dataBean.setDmlb(dmlb);
//            dataBean.setDmz(dmz);
//            dataBean.setDmsm1(dmsm1);
//            list.add(dataBean);
//        }
//        db.close();
//        query.close();
//
//        return list;
//
//    }
//
//
//    //查询字符 是否存在
//    public static boolean isExist(Context context, String text) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        //db.insert(CodeTableSQLiteOpenHelper.CODE_TABLE_NAME,null,cv);
//        Cursor query = db.query(CodeTableSQLiteOpenHelper.CODE_TABLE_NAME, null, null, null, null, null, null, null);
//        if (query.moveToFirst()) {
//            String exist = query.getString(query.getColumnIndex("text"));
//            if (exist.equals(text)) {
//                db.close();
//                query.close();
//                return true;
//            }
//        }
//        db.close();
//        query.close();
//        return false;
//    }
//
//    public static void deleteAll(String tableName) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//        String sql = String.format("delete from %s", tableName);
//        db.execSQL(sql);
//
//        db.close();
//
//    }
//
//    //增加来历证明的数据存储
//    public static void insertLlzmToDB(List<? extends AllLizmBean.Data.OriginConfig> data) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        for (AllLizmBean.Data.OriginConfig dataBean : data) {
//            cv.clear();
//            cv.put("dmlb", StringUtils.isNull(Constants.Companion.getLLZM()));
//            cv.put("dmz", StringUtils.isNull(dataBean.getZplx()));
//            cv.put("dmsm1", StringUtils.isNull(dataBean.getZmmc()));
//            db.insert(CodeTableSQLiteOpenHelper.CODE_TABLE_NAME, null, cv);
//        }
//    }
//
//    //增加行政区划省级的存储，目的是增加dmlb进行本地查询
//    public static void insertQhToDB(List<CodeEntity.DataBean> data) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        for (CodeEntity.DataBean dataBean : data) {
//            cv.clear();
//            cv.put("dmlb", StringUtils.isNull(Constants.Companion.getMY_QH_SHENG_DMLB()));  //0219 自定义的类型  方便子区划的查询
//            cv.put("dmz", StringUtils.isNull(dataBean.getDmz()));
//            cv.put("dmsm1", StringUtils.isNull(dataBean.getDmsm1()));
////            cv.put("xtlb", StringUtils.isNull(dataBean.getXtlb()));   //自定义的 不用弄系统类别 防止数据错乱
//            db.insert(CodeTableSQLiteOpenHelper.CODE_TABLE_NAME, null, cv);
////            insertOrUpdate(CodeTableSQLiteOpenHelper.CODE_TABLE_NAME, cv, db);
//        }
//        db.close();
//    }
//
//    //查询代码表数据的条数
//    public static long queryCodeTableCount() {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        String sql = "select count(*) from " + CodeTableSQLiteOpenHelper.CODE_TABLE_NAME;
//        Cursor cursor = db.rawQuery(sql, null);
//        cursor.moveToFirst();
//        long count = cursor.getLong(0);
//        cursor.close();
//        db.close();
//        return count;
//    }
//
//
//
//    // ===============================service  图片的相关SQL ===========================================
//
//    public static void addPhoto(PhotoEntity entity) {
//        String lsh = entity.getLsh();
//        String zpzl = entity.getZpzl();
//        String sfz = entity.getSfz();
//        String zpPath = entity.getZpPath();
//
//
//        // 数据库已经存在 该车辆类型的照片 执行更新，不存在执行 插入
//        if (checkIsYgPicture(zpzl)) { // 员工备案的照片用sfz做标记
//            if (!TextUtils.isEmpty(sfz)) {
//                List<PhotoEntity> list = queryBySfzAndDmzRList(sfz, zpzl);
//                if (list.size() > 0) {
//                    updateBySfzAndDmz(entity);
//                } else {
//                    insert(entity);
//                }
//            }
//        } else {// 查验业务的照片用lsh做标记
//            if (!TextUtils.isEmpty(lsh)) {
//                List<PhotoEntity> list = queryByLshAndDmzRList(lsh, zpzl);
//                if (list.size() > 0) {
//                    updateByLshAndDmz(entity);
//                    Log.d("lylog","查验业务 updateByLshAndDmz ");
//                } else {
//                    insert(entity);
//                    Log.d("lylog","查验业务 insert ");
//                }
//            }
//        }
//    }
//
//    //根据照片类型判断是否是员工照片
//    private static Boolean checkIsYgPicture(String zplx) {
//        List<CodeEntity.DataBean> list = com.seatrend.jx.managersystem.database.CodeTableSQLiteUtils.getYGzpList();
//        for (CodeEntity.DataBean db : list) {
//            if (zplx != null && zplx.equals(db.getDmz())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    public static void insert(PhotoEntity entity) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(Constants.Companion.getS_LSH(), entity.getLsh());
//        cv.put(Constants.Companion.getS_XH(), entity.getXh());
//        cv.put(Constants.Companion.getS_ZPZL(), entity.getZpzl());
//        cv.put(Constants.Companion.getS_ZPDZ(), entity.getZpdz());
//        cv.put(Constants.Companion.getS_ZPPATH(), entity.getZpPath());
//        cv.put(Constants.Companion.getS_LRR(), entity.getLrr());
//        cv.put(Constants.Companion.getS_LRBM(), entity.getLrbm());
//        cv.put(Constants.Companion.getS_ZPSM(), entity.getZpsm());
//        cv.put(Constants.Companion.getS_CFFS(), entity.getCffs());
//        cv.put(Constants.Companion.getS_SFZ(), entity.getSfz());
//
//        cv.put(Constants.Companion.getZPLX(), entity.getZplx());
//        cv.put(Constants.Companion.getS_ZPBASE64(), entity.getZpBase64());
//        cv.put(Constants.Companion.getS_CLLX(), entity.getCllxmc());
//        cv.put(Constants.Companion.getS_HPHM(), entity.getHphm());
//        cv.put(Constants.Companion.getS_SCCS(), entity.getZp_num());
//        db.insert(CodeTableSQLiteOpenHelper.PHOTO_TABLE_NAME, null, cv);
//        db.close();
//    }
//
//
//    public static List<PhotoEntity> queryAll() {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        Cursor query = db.query(CodeTableSQLiteOpenHelper.PHOTO_TABLE_NAME, new String[]{}, null,
//                null, null, null, null, null);
//        List<PhotoEntity> list = new ArrayList<>();
//        while (query.moveToNext()) {
//            String lsh = query.getString(query.getColumnIndex(Constants.Companion.getS_LSH()));
//            String xh = query.getString(query.getColumnIndex(Constants.Companion.getS_XH()));
//            String zpzl = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPZL()));
//            String zpdz = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPDZ()));
//            String zpsm = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPSM()));
//            String cffs = query.getString(query.getColumnIndex(Constants.Companion.getS_CFFS()));
//            String lrr = query.getString(query.getColumnIndex(Constants.Companion.getS_LRR()));
//            String lrbm = query.getString(query.getColumnIndex(Constants.Companion.getS_LRBM()));
//            String zpPath = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPPATH()));
//            String sfz = query.getString(query.getColumnIndex(Constants.Companion.getS_SFZ()));
//            String zplx = query.getString(query.getColumnIndex(Constants.Companion.getZPLX()));
//
//            String cllx = query.getString(query.getColumnIndex(Constants.Companion.getS_CLLX()));
//            String hphm = query.getString(query.getColumnIndex(Constants.Companion.getS_HPHM()));
//
//            PhotoEntity photoEntity = new PhotoEntity();
//            photoEntity.setLsh(lsh);
//            photoEntity.setXh(xh);
//            photoEntity.setZpzl(zpzl);
//            photoEntity.setZpdz(zpdz);
//            photoEntity.setZpsm(zpsm);
//            photoEntity.setCffs(cffs);
//            photoEntity.setLrr(lrr);
//            photoEntity.setLrbm(lrbm);
//            photoEntity.setZpPath(zpPath);
//            photoEntity.setSfz(sfz);
//            photoEntity.setZplx(zplx);
//            photoEntity.setHphm(hphm);
//            photoEntity.setCllxmc(cllx);
//            list.add(photoEntity);
//        }
//        return list;
//    }
//
//    /**
//     * 获取数据库照片的信息
//     * @param hp
//     * @return
//     */
//    public static List<PhotoEntity> queryPhtotosByHPHM(String hp){
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        Cursor query = db.query(CodeTableSQLiteOpenHelper.PHOTO_TABLE_NAME, new String[]{}, null,
//                null, null, null, null, null);
//        List<PhotoEntity> list = new ArrayList<>();
//        while (query.moveToNext()) {
//            String hphm = query.getString(query.getColumnIndex(Constants.Companion.getS_HPHM()));
//
//            if (hphm.equals(hp) ) {
//                String zpsm = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPSM()));
//                String zppath = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPPATH()));
//                String cllx = query.getString(query.getColumnIndex(Constants.Companion.getS_CLLX()));
//                String sccs = query.getString(query.getColumnIndex(Constants.Companion.getS_SCCS()));
//
//                PhotoEntity photoEntity = new PhotoEntity();
//                photoEntity.setZpsm(zpsm);
//                photoEntity.setZpPath(zppath);
//                photoEntity.setCllxmc(cllx);
//                photoEntity.setHphm(hphm);
//                photoEntity.setZp_num(sccs);
//                list.add(photoEntity);
//            }
//        }
//        return list;
//    }
//    /**
//     * 用lsh，代码值 删除数据
//     *
//     * @param lsh
//     * @param zpzl
//     */
//    public static void deleteByLshAndDmz(String lsh, String zpzl) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        String sql = String.format("delete from %s where lsh='" + lsh + "' and " + "zpzl='" + zpzl + "'", CodeTableSQLiteOpenHelper.PHOTO_TABLE_NAME);
//        db.execSQL(sql);
//        db.close();
//    }
//
//    /**
//     * 用sfz，代码值 删除数据
//     *
//     * @param sfz
//     * @param zpzl
//     */
//    public static void deleteBySfzAndDmz(String sfz, String zpzl) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        String sql = String.format("delete from %s where sfzmhm='" + sfz + "' and " + "zpzl='" + zpzl + "'", CodeTableSQLiteOpenHelper.PHOTO_TABLE_NAME);
//        db.execSQL(sql);
//        db.close();
//    }
//
//    /**
//     * 增加元素 （查验业务）
//     *
//     * @param id   照片地址
//     * @param lsh  流水号
//     * @param zplx 照片类型
//     */
//    public static void updateByLshAndDmz(String lsh, String zplx, String id) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(Constants.Companion.getS_ZPDZ(), id);
//        db.update(CodeTableSQLiteOpenHelper.PHOTO_TABLE_NAME, cv, "lsh=? and zpzl=? ", new String[]{lsh, zplx});
//        db.close();
//    }
//
//    /**
//     * 增加元素 (员工备案)
//     *
//     * @param id   照片地址
//     * @param sfz  流水号
//     * @param zplx 照片类型
//     */
//    public static void updateBySfzAndDmz(String sfz, String zplx, String id) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(Constants.Companion.getS_ZPDZ(), id);
//        db.update(CodeTableSQLiteOpenHelper.PHOTO_TABLE_NAME, cv, "sfzmhm=? and zpzl=?", new String[]{sfz, zplx});
//        db.close();
//    }
//
//    /**
//     * 上传次数的增加 (员工备案)
//     *
//     * @param id   照片地址
//     * @param sfz  流水号
//     * @param zplx 照片类型
//     */
//    public static void updateBySfzAndDmzTOAddSCCS(String sfz, String zplx, String sccs) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(Constants.Companion.getS_SCCS(), sccs);
//        db.update(CodeTableSQLiteOpenHelper.PHOTO_TABLE_NAME, cv, "sfzmhm=? and zpzl=?", new String[]{sfz, zplx});
//        db.close();
//    }
//
//    /**
//     * 增加元素  查验和业务
//     *
//     * @param entity 实体
//     */
//    public static void updateByLshAndDmz(PhotoEntity entity) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(Constants.Companion.getS_LSH(), entity.getLsh());
//        cv.put(Constants.Companion.getS_XH(), entity.getXh());
//        cv.put(Constants.Companion.getS_ZPZL(), entity.getZpzl());
//        cv.put(Constants.Companion.getS_ZPDZ(), entity.getZpdz());
//        cv.put(Constants.Companion.getS_ZPPATH(), entity.getZpPath());
//        cv.put(Constants.Companion.getS_LRR(), entity.getLrr());
//        cv.put(Constants.Companion.getS_LRBM(), entity.getLrbm());
//        cv.put(Constants.Companion.getS_ZPSM(), entity.getZpsm());
//        cv.put(Constants.Companion.getS_CFFS(), entity.getCffs());
//
//        cv.put(Constants.Companion.getS_ZPBASE64(), entity.getZpBase64());
//        cv.put(Constants.Companion.getZPLX(), entity.getZplx());
//        cv.put(Constants.Companion.getS_CLLX(), entity.getCllxmc());
//        cv.put(Constants.Companion.getS_HPHM(), entity.getHphm());
//        cv.put(Constants.Companion.getS_SCCS(), entity.getZp_num());
//
//        db.update(CodeTableSQLiteOpenHelper.PHOTO_TABLE_NAME, cv, "lsh=? and zpzl=?", new String[]{entity.getLsh(), entity.getZpzl()});
//        db.close();
//    }
//
//    /**
//     * 增加元素  为员工备案而来
//     *
//     * @param entity 实体
//     */
//    public static void updateBySfzAndDmz(PhotoEntity entity) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(Constants.Companion.getS_SFZ(), entity.getSfz());
//        cv.put(Constants.Companion.getS_ZPZL(), entity.getZpzl());
//        cv.put(Constants.Companion.getS_ZPDZ(), entity.getZpdz());
//        cv.put(Constants.Companion.getS_ZPPATH(), entity.getZpPath());
//        cv.put(Constants.Companion.getS_LRR(), entity.getLrr());
//        cv.put(Constants.Companion.getS_LRBM(), entity.getLrbm());
//        cv.put(Constants.Companion.getS_ZPSM(), entity.getZpsm());
//        cv.put(Constants.Companion.getS_CFFS(), entity.getCffs());
//
//        cv.put(Constants.Companion.getS_ZPBASE64(), entity.getZpBase64());
//        cv.put(Constants.Companion.getS_CLLX(), entity.getCllxmc());
//        cv.put(Constants.Companion.getS_HPHM(), entity.getHphm());
//        cv.put(Constants.Companion.getS_SCCS(), entity.getZp_num());
//        cv.put(Constants.Companion.getS_ZPLX(), entity.getZplx());
//        db.update(CodeTableSQLiteOpenHelper.PHOTO_TABLE_NAME, cv, "sfzmhm=? and zpzl=?", new String[]{entity.getSfz(), entity.getZpzl()});
//        db.close();
//    }
//
//    /**
//     * 查询元素  流水号和照片类型
//     */
//    public static List<PhotoEntity> queryByLsh(String lsh, String zplx) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        Cursor query = db.query(CodeTableSQLiteOpenHelper.PHOTO_TABLE_NAME, new String[]{}, null,
//                null, null, null, null, null);
//        List<PhotoEntity> list = new ArrayList<>();
//
//        while (query.moveToNext()) {
//            String lshx = query.getString(query.getColumnIndex(Constants.Companion.getS_LSH()));
//            String zpzl = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPZL()));
//            String zpl = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPLX()));
//            if (lsh.equals(lshx) && zplx.equals(zpl)) {
//                String zpdz = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPDZ()));
//                String zpsm = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPSM()));
//                String cffs = query.getString(query.getColumnIndex(Constants.Companion.getS_CFFS()));
//                String lrr = query.getString(query.getColumnIndex(Constants.Companion.getS_LRR()));
//                String lrbm = query.getString(query.getColumnIndex(Constants.Companion.getS_LRBM()));
//                String zpPath = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPPATH()));
//                String xh = query.getString(query.getColumnIndex(Constants.Companion.getS_XH()));
//
//                PhotoEntity photoEntity = new PhotoEntity();
//                photoEntity.setLsh(lsh);
//                photoEntity.setXh(xh);
//                photoEntity.setZpzl(zpzl);
//                photoEntity.setZpdz(zpdz);
//                photoEntity.setZpsm(zpsm);
//                photoEntity.setCffs(cffs);
//                photoEntity.setLrr(lrr);
//                photoEntity.setLrbm(lrbm);
//                photoEntity.setZpPath(zpPath);
//                photoEntity.setZplx(zpl);
//                list.add( photoEntity);
//            }
//        }
//        return list;
//    }
//
//
//    /**
//     * 查询元素 根据流水号和zplx (查验和业务类的照片)
//     */
//    public static List<PhotoEntity> queryByLshAndDmzRList(String lsh, String zplx) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        Cursor query = db.query(CodeTableSQLiteOpenHelper.PHOTO_TABLE_NAME, new String[]{}, null,
//                null, null, null, null, null);
//        List<PhotoEntity> list = new ArrayList<>();
//        while (query.moveToNext()) {
//            String lshx = query.getString(query.getColumnIndex(Constants.Companion.getS_LSH()));
//            String zpzl = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPZL()));
//            String zppath = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPPATH()));
//
//            if (lsh.equals(lshx) && zplx.equals(zpzl)) {
//                String zpdz = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPDZ()));
//                String zpsm = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPSM()));
//                String cffs = query.getString(query.getColumnIndex(Constants.Companion.getS_CFFS()));
//                String lrr = query.getString(query.getColumnIndex(Constants.Companion.getS_LRR()));
//                String lrbm = query.getString(query.getColumnIndex(Constants.Companion.getS_LRBM()));
//                String xh = query.getString(query.getColumnIndex(Constants.Companion.getS_XH()));
//
//                PhotoEntity photoEntity = new PhotoEntity();
//                photoEntity.setLsh(lsh);
//                photoEntity.setXh(xh);
//                photoEntity.setZpzl(zpzl);
//                photoEntity.setZpdz(zpdz);
//                photoEntity.setZpsm(zpsm);
//                photoEntity.setCffs(cffs);
//                photoEntity.setLrr(lrr);
//                photoEntity.setLrbm(lrbm);
//                photoEntity.setZpPath(zppath);
//                list.add(photoEntity);
//            }
//        }
//        return list;
//    }
//
//    /**
//     * 查询元素 根据身份证和zplx (员工备案照片)
//     */
//    public static List<PhotoEntity> queryBySfzAndDmzRList(String sfz, String zplx) {
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        Cursor query = db.query(CodeTableSQLiteOpenHelper.PHOTO_TABLE_NAME, new String[]{}, null,
//                null, null, null, null, null);
//        List<PhotoEntity> list = new ArrayList<>();
//        while (query.moveToNext()) {
//            String lsfz = query.getString(query.getColumnIndex(Constants.Companion.getS_SFZ()));
//            String zpzl = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPZL()));
//            String zppath = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPPATH()));
//
//            if (sfz.equals(lsfz) && zplx.equals(zpzl)) {
//                String zpdz = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPDZ()));
//                String zpsm = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPSM()));
//                String cffs = query.getString(query.getColumnIndex(Constants.Companion.getS_CFFS()));
//                String lrr = query.getString(query.getColumnIndex(Constants.Companion.getS_LRR()));
//                String lrbm = query.getString(query.getColumnIndex(Constants.Companion.getS_LRBM()));
//
//                PhotoEntity photoEntity = new PhotoEntity();
//                photoEntity.setSfz(lsfz);
//                photoEntity.setZpzl(zpzl);
//                photoEntity.setZpdz(zpdz);
//                photoEntity.setZpsm(zpsm);
//                photoEntity.setCffs(cffs);
//                photoEntity.setLrr(lrr);
//                photoEntity.setLrbm(lrbm);
//                photoEntity.setZpPath(zppath);
//                list.add(photoEntity);
//            }
//        }
//        return list;
//    }
//
//    /**
//     * 返回数据库对应的照片路径(业务查验)
//     *
//     * @param lsh
//     * @param dmz
//     * @return
//     */
//    public static String queryLshAndDmz(String lsh, String dmz) {
//
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        Cursor query = db.query(CodeTableSQLiteOpenHelper.PHOTO_TABLE_NAME, new String[]{}, null,
//                null, null, null, null, null);
//        String zpPath = "";
//        while (query.moveToNext()) {
//            String lsfz = query.getString(query.getColumnIndex(Constants.Companion.getLSH()));
//            String zpzl = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPZL()));
//            if (lsh.equals(lsfz) && dmz.equals(zpzl)) {
//                zpPath = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPPATH()));
//            }
//        }
//        return zpPath;
//    }
//
//    /**
//     * 返回数据库对应的照片路径（员工）
//     *
//     * @param sfz
//     * @param dmz
//     * @return
//     */
//    public static String querySfzAndDmz(String sfz, String dmz) {
//
//        CodeTableSQLiteOpenHelper dbHelper = CodeTableSQLiteOpenHelper.getInstance();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        Cursor query = db.query(CodeTableSQLiteOpenHelper.PHOTO_TABLE_NAME, new String[]{}, null,
//                null, null, null, null, null);
//        String zpPath = "";
//        while (query.moveToNext()) {
//            String sfzz = query.getString(query.getColumnIndex(Constants.Companion.getS_SFZ()));
//            String zpzl = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPZL()));
//            if (sfz != null && dmz != null && sfz.equals(sfzz) && dmz.equals(zpzl)) {
//                zpPath = query.getString(query.getColumnIndex(Constants.Companion.getS_ZPPATH()));
//            }
//        }
//        return zpPath;
//    }
//
//
//    // ===============================service  图片的相关SQL ===========================================
//
//
//    //全新码表 insert
//    public static void insertCode(@NotNull List<? extends CodeEntity.DataBean> data) {
//
//        for (CodeEntity.DataBean db : data) {
//            DataBean entity = new DataBean();
//            entity.setDmz(db.getDmz());
//            entity.setXtlb(db.getXtlb());
//            entity.setDmlb(db.getDmlb());
//            entity.setDmsm1(db.getDmsm1());
//            entity.setDmsm2(db.getDmsm2());
//            DbUtils.getInstance(MyApplication.Companion.getMyApplicationContext()).insertOrReplaceCode(entity);
//        }
//    }
//
//    //全新码表 delete
//    public static void deleteCodeAll() {
//        DbUtils.getInstance(MyApplication.Companion.getMyApplicationContext()).deleteCodeAll();
//
//    }
//}
