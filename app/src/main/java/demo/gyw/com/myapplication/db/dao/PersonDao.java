package demo.gyw.com.myapplication.db.dao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;

import demo.gyw.com.myapplication.db.model.Card;

import demo.gyw.com.myapplication.db.model.Person;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PERSON".
*/
public class PersonDao extends AbstractDao<Person, Long> {

    public static final String TABLENAME = "PERSON";

    /**
     * Properties of entity Person.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Age = new Property(2, Integer.class, "age", false, "AGE");
        public final static Property Height = new Property(3, Double.class, "height", false, "HEIGHT");
        public final static Property Weight = new Property(4, Double.class, "weight", false, "WEIGHT");
        public final static Property CardId = new Property(5, Long.class, "cardId", false, "CARD_ID");
    };

    private DaoSession daoSession;


    public PersonDao(DaoConfig config) {
        super(config);
    }
    
    public PersonDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PERSON\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"AGE\" INTEGER," + // 2: age
                "\"HEIGHT\" REAL," + // 3: height
                "\"WEIGHT\" REAL," + // 4: weight
                "\"CARD_ID\" INTEGER);"); // 5: cardId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PERSON\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Person entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        Integer age = entity.getAge();
        if (age != null) {
            stmt.bindLong(3, age);
        }
 
        Double height = entity.getHeight();
        if (height != null) {
            stmt.bindDouble(4, height);
        }
 
        Double weight = entity.getWeight();
        if (weight != null) {
            stmt.bindDouble(5, weight);
        }
 
        Long cardId = entity.getCardId();
        if (cardId != null) {
            stmt.bindLong(6, cardId);
        }
    }

    @Override
    protected void attachEntity(Person entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Person readEntity(Cursor cursor, int offset) {
        Person entity = new Person( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // age
            cursor.isNull(offset + 3) ? null : cursor.getDouble(offset + 3), // height
            cursor.isNull(offset + 4) ? null : cursor.getDouble(offset + 4), // weight
            cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5) // cardId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Person entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAge(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setHeight(cursor.isNull(offset + 3) ? null : cursor.getDouble(offset + 3));
        entity.setWeight(cursor.isNull(offset + 4) ? null : cursor.getDouble(offset + 4));
        entity.setCardId(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Person entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Person entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getCardDao().getAllColumns());
            builder.append(" FROM PERSON T");
            builder.append(" LEFT JOIN CARD T0 ON T.\"CARD_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Person loadCurrentDeep(Cursor cursor, boolean lock) {
        Person entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Card card = loadCurrentOther(daoSession.getCardDao(), cursor, offset);
        entity.setCard(card);

        return entity;    
    }

    public Person loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Person> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Person> list = new ArrayList<Person>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Person> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Person> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
