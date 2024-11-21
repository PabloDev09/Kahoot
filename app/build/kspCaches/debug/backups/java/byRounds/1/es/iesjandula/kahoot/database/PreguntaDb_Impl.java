package es.iesjandula.kahoot.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PreguntaDb_Impl extends PreguntaDb {
  private volatile PreguntaDao _preguntaDao;

  private volatile RespuestaDao _respuestaDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `Pregunta` (`id_pregunta` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `tx_pregunta` TEXT NOT NULL, `respuesta1` TEXT NOT NULL, `respuesta2` TEXT NOT NULL, `respuesta3` TEXT NOT NULL, `respuesta4` TEXT NOT NULL, `referenciaRespuestaCorrecta` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `Respuesta` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `idPregunta` INTEGER NOT NULL, `respuestaSeleccionada` TEXT NOT NULL, `esCorrecta` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'fb8800909c3aa6e388c89cedd6d4c7c0')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `Pregunta`");
        db.execSQL("DROP TABLE IF EXISTS `Respuesta`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsPregunta = new HashMap<String, TableInfo.Column>(7);
        _columnsPregunta.put("id_pregunta", new TableInfo.Column("id_pregunta", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPregunta.put("tx_pregunta", new TableInfo.Column("tx_pregunta", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPregunta.put("respuesta1", new TableInfo.Column("respuesta1", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPregunta.put("respuesta2", new TableInfo.Column("respuesta2", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPregunta.put("respuesta3", new TableInfo.Column("respuesta3", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPregunta.put("respuesta4", new TableInfo.Column("respuesta4", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPregunta.put("referenciaRespuestaCorrecta", new TableInfo.Column("referenciaRespuestaCorrecta", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPregunta = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPregunta = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPregunta = new TableInfo("Pregunta", _columnsPregunta, _foreignKeysPregunta, _indicesPregunta);
        final TableInfo _existingPregunta = TableInfo.read(db, "Pregunta");
        if (!_infoPregunta.equals(_existingPregunta)) {
          return new RoomOpenHelper.ValidationResult(false, "Pregunta(es.iesjandula.kahoot.models.Pregunta).\n"
                  + " Expected:\n" + _infoPregunta + "\n"
                  + " Found:\n" + _existingPregunta);
        }
        final HashMap<String, TableInfo.Column> _columnsRespuesta = new HashMap<String, TableInfo.Column>(4);
        _columnsRespuesta.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRespuesta.put("idPregunta", new TableInfo.Column("idPregunta", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRespuesta.put("respuestaSeleccionada", new TableInfo.Column("respuestaSeleccionada", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRespuesta.put("esCorrecta", new TableInfo.Column("esCorrecta", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRespuesta = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRespuesta = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRespuesta = new TableInfo("Respuesta", _columnsRespuesta, _foreignKeysRespuesta, _indicesRespuesta);
        final TableInfo _existingRespuesta = TableInfo.read(db, "Respuesta");
        if (!_infoRespuesta.equals(_existingRespuesta)) {
          return new RoomOpenHelper.ValidationResult(false, "Respuesta(es.iesjandula.kahoot.models.Respuesta).\n"
                  + " Expected:\n" + _infoRespuesta + "\n"
                  + " Found:\n" + _existingRespuesta);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "fb8800909c3aa6e388c89cedd6d4c7c0", "bcb9898fa6945ef7aaec8c8df841f11b");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Pregunta","Respuesta");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Pregunta`");
      _db.execSQL("DELETE FROM `Respuesta`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(PreguntaDao.class, PreguntaDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RespuestaDao.class, RespuestaDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public PreguntaDao preguntaDao() {
    if (_preguntaDao != null) {
      return _preguntaDao;
    } else {
      synchronized(this) {
        if(_preguntaDao == null) {
          _preguntaDao = new PreguntaDao_Impl(this);
        }
        return _preguntaDao;
      }
    }
  }

  @Override
  public RespuestaDao respuestaDao() {
    if (_respuestaDao != null) {
      return _respuestaDao;
    } else {
      synchronized(this) {
        if(_respuestaDao == null) {
          _respuestaDao = new RespuestaDao_Impl(this);
        }
        return _respuestaDao;
      }
    }
  }
}
