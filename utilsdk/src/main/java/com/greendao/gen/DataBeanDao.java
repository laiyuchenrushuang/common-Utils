package com.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.seatrend.utilsdk.database.greendao.table.DataBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DATA_BEAN".
*/
public class DataBeanDao extends AbstractDao<DataBean, Long> {

    public static final String TABLENAME = "DATA_BEAN";

    /**
     * Properties of entity DataBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Xtlb = new Property(1, String.class, "xtlb", false, "XTLB");
        public final static Property Dmlb = new Property(2, String.class, "dmlb", false, "DMLB");
        public final static Property Dmz = new Property(3, String.class, "dmz", false, "DMZ");
        public final static Property Dmsm1 = new Property(4, String.class, "dmsm1", false, "DMSM1");
        public final static Property Dmsm2 = new Property(5, String.class, "dmsm2", false, "DMSM2");
        public final static Property Dmsm3 = new Property(6, String.class, "dmsm3", false, "DMSM3");
        public final static Property Dmsm4 = new Property(7, String.class, "dmsm4", false, "DMSM4");
        public final static Property Zt = new Property(8, String.class, "zt", false, "ZT");
    }


    public DataBeanDao(DaoConfig config) {
        super(config);
    }
    
    public DataBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DATA_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"XTLB\" TEXT," + // 1: xtlb
                "\"DMLB\" TEXT," + // 2: dmlb
                "\"DMZ\" TEXT," + // 3: dmz
                "\"DMSM1\" TEXT," + // 4: dmsm1
                "\"DMSM2\" TEXT," + // 5: dmsm2
                "\"DMSM3\" TEXT," + // 6: dmsm3
                "\"DMSM4\" TEXT," + // 7: dmsm4
                "\"ZT\" TEXT);"); // 8: zt
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DATA_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DataBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String xtlb = entity.getXtlb();
        if (xtlb != null) {
            stmt.bindString(2, xtlb);
        }
 
        String dmlb = entity.getDmlb();
        if (dmlb != null) {
            stmt.bindString(3, dmlb);
        }
 
        String dmz = entity.getDmz();
        if (dmz != null) {
            stmt.bindString(4, dmz);
        }
 
        String dmsm1 = entity.getDmsm1();
        if (dmsm1 != null) {
            stmt.bindString(5, dmsm1);
        }
 
        String dmsm2 = entity.getDmsm2();
        if (dmsm2 != null) {
            stmt.bindString(6, dmsm2);
        }
 
        String dmsm3 = entity.getDmsm3();
        if (dmsm3 != null) {
            stmt.bindString(7, dmsm3);
        }
 
        String dmsm4 = entity.getDmsm4();
        if (dmsm4 != null) {
            stmt.bindString(8, dmsm4);
        }
 
        String zt = entity.getZt();
        if (zt != null) {
            stmt.bindString(9, zt);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DataBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String xtlb = entity.getXtlb();
        if (xtlb != null) {
            stmt.bindString(2, xtlb);
        }
 
        String dmlb = entity.getDmlb();
        if (dmlb != null) {
            stmt.bindString(3, dmlb);
        }
 
        String dmz = entity.getDmz();
        if (dmz != null) {
            stmt.bindString(4, dmz);
        }
 
        String dmsm1 = entity.getDmsm1();
        if (dmsm1 != null) {
            stmt.bindString(5, dmsm1);
        }
 
        String dmsm2 = entity.getDmsm2();
        if (dmsm2 != null) {
            stmt.bindString(6, dmsm2);
        }
 
        String dmsm3 = entity.getDmsm3();
        if (dmsm3 != null) {
            stmt.bindString(7, dmsm3);
        }
 
        String dmsm4 = entity.getDmsm4();
        if (dmsm4 != null) {
            stmt.bindString(8, dmsm4);
        }
 
        String zt = entity.getZt();
        if (zt != null) {
            stmt.bindString(9, zt);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DataBean readEntity(Cursor cursor, int offset) {
        DataBean entity = new DataBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // xtlb
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // dmlb
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // dmz
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // dmsm1
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // dmsm2
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // dmsm3
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // dmsm4
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // zt
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DataBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setXtlb(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDmlb(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDmz(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDmsm1(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setDmsm2(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setDmsm3(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setDmsm4(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setZt(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DataBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DataBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DataBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
