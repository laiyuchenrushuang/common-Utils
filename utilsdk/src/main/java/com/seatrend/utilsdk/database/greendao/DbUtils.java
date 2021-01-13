package com.seatrend.utilsdk.database.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.greendao.gen.DaoMaster;
import com.greendao.gen.DaoSession;
import com.greendao.gen.DataBeanDao;
import com.greendao.gen.OnePictureIndexEntityDao;
import com.seatrend.utilsdk.database.greendao.table.DataBean;
import com.seatrend.utilsdk.database.greendao.table.OnePictureIndexEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by ly on 2020/7/7 14:03
 */
public class DbUtils {


    //区分数据库存储的TAG
    enum TABLE {
        PICTURETABLE("PictureTable"),
        JUDGETABLE("JudgeTable"),
        PLANTABLE("PlanTable"),
        PHOTOONLINEDATABEAN("PHOTOONLINEDATABEAN")//计划列表(在线)
        ;

        private String name;

        TABLE(String name) {

            this.name = name;
        }

        public String getName() {
            return name;
        }

    }

    /**
     * 数据库名字
     */
    private String DB_NAME = "plan.db";  //数据库名字
    /**
     * Helper
     */
    private DaoMaster.DevOpenHelper mHelper;//获取Helper对象
    /**
     * 数据库
     */
    private SQLiteDatabase db;
    /**
     * DaoMaster
     */
    private DaoMaster mDaoMaster;
    /**
     * DaoSession
     */
    private DaoSession mDaoSession;
    /**
     * 上下文
     */
    private Context context;

    private DataBeanDao dataBeanDao;  //code表

    private OnePictureIndexEntityDao onePictureIndexEntityDao;


    private static DbUtils mDbController;

    public DbUtils(Context context) {
        this.context = context;
        mHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        mDaoMaster = new DaoMaster(getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();

        dataBeanDao = mDaoSession.getDataBeanDao();
        onePictureIndexEntityDao = mDaoSession.getOnePictureIndexEntityDao();
    }

    /**
     * 获取可写数据库
     *
     * @return
     */
    private SQLiteDatabase getWritableDatabase() {
        if (mHelper == null) {
            mHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        }
        db = mHelper.getWritableDatabase();
        return db;
    }


    /**
     * 获取单例（context 最好用application的context  防止内存泄漏）
     */
    public static DbUtils getInstance(Context context) {
        if (mDbController == null) {
            synchronized (DbUtils.class) {
                if (mDbController == null) {
                    mDbController = new DbUtils(context);
                }
            }
        }
        return mDbController;
    }

    //增加码表值
    public void insertOrReplaceCode(DataBean bean) {
        dataBeanDao.insertOrReplace(bean);
    }

    //一级照片列表增加数据
    public void insertOrReplacePicture(OnePictureIndexEntity bean) {
        onePictureIndexEntityDao.insertOrReplace(bean);
    }

    //一级照片列表查询数据所有
    public List<OnePictureIndexEntity> querryPictureAll() {
       return onePictureIndexEntityDao.queryBuilder().build().list();
    }

    //一级照片列表delete数据一项
    public void deletePictureAll(String lsh) {
         onePictureIndexEntityDao.queryBuilder().where(OnePictureIndexEntityDao.Properties.Lsh.eq(lsh)).buildDelete().executeDeleteWithoutDetachingEntities();
    }


    //一级照片列表查询数据一项
    public OnePictureIndexEntity queryPictureByLsh(String lsh) {
       return onePictureIndexEntityDao.queryBuilder().where(OnePictureIndexEntityDao.Properties.Lsh.eq(lsh)).build().unique();
    }


    //一级照片列表查询数据一项
    public List<OnePictureIndexEntity> queryPictureByHpHm(String hphm) {
        return onePictureIndexEntityDao.queryBuilder().where(OnePictureIndexEntityDao.Properties.Hphm.eq(hphm)).build().list();
    }
    //删除照片列表所有
    public void deletePictureAll() {
        onePictureIndexEntityDao.deleteAll();
    }

    //增加码表值
    public void insertCode(DataBean bean) {
        dataBeanDao.insert(bean);
    }

    //删除码表
    public void deleteCodeAll() {
        dataBeanDao.deleteAll();
    }

    @NotNull
    public long getCodeTableCount() {
        return dataBeanDao.count();
    }

    public boolean getLockCodeTable() {
        return dataBeanDao.getDatabase().isDbLockedByCurrentThread();
    }

    //根据系统类别和代码类别查询代码列表
    public List<DataBean> selectCodeByXtlbAndDmlb(String xtlb, String dmlb) {
        List<DataBean> dataBeanList = dataBeanDao.queryBuilder().where(DataBeanDao.Properties.Xtlb.eq(xtlb), DataBeanDao.Properties.Dmlb.eq(dmlb)).build().list();
//        Collections.sort(dataBeanList);
        return dataBeanList;
    }

    //查询小类
    public List<DataBean> selectCodeByXtlbAndDmlbCllx(String xtlb, String dmlb, String likeDmz) {
        List<DataBean> dataBeanList = dataBeanDao.queryBuilder().where(DataBeanDao.Properties.Xtlb.eq(xtlb), DataBeanDao.Properties.Dmlb.eq(dmlb)).build().list();
        List<DataBean> mList = new ArrayList<>();

        if(dataBeanList !=null && dataBeanList.size()>0){
            for(DataBean db :dataBeanList){
                if(likeDmz.equals(db.getDmz().substring(0,1))){
                    mList.add(db);
                }
            }
        }
//        Collections.sort(mList);
        return mList;
    }

    public String searchCodeDMSMByDMLBAndDmz(String xtlb, String dmz){
        DataBean databean = dataBeanDao.queryBuilder().where(DataBeanDao.Properties.Xtlb.eq(xtlb),DataBeanDao.Properties.Dmz.eq(dmz)).build().unique();

        if(databean != null){
            return  databean.getDmsm1();
        }
        return "";
    }
}
