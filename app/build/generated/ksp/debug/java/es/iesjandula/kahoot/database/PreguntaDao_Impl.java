package es.iesjandula.kahoot.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import es.iesjandula.kahoot.models.Pregunta;
import es.iesjandula.kahoot.models.Respuesta;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PreguntaDao_Impl implements PreguntaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Pregunta> __insertionAdapterOfPregunta;

  private final EntityInsertionAdapter<Respuesta> __insertionAdapterOfRespuesta;

  private final EntityDeletionOrUpdateAdapter<Pregunta> __deletionAdapterOfPregunta;

  private final EntityDeletionOrUpdateAdapter<Pregunta> __updateAdapterOfPregunta;

  public PreguntaDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPregunta = new EntityInsertionAdapter<Pregunta>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `Pregunta` (`id_pregunta`,`tx_pregunta`,`respuesta1`,`respuesta2`,`respuesta3`,`respuesta4`,`referenciaRespuestaCorrecta`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Pregunta entity) {
        statement.bindLong(1, entity.getIdPregunta());
        statement.bindString(2, entity.getTxPregunta());
        statement.bindString(3, entity.getRespuesta1());
        statement.bindString(4, entity.getRespuesta2());
        statement.bindString(5, entity.getRespuesta3());
        statement.bindString(6, entity.getRespuesta4());
        statement.bindLong(7, entity.getReferenciaRespuestaCorrecta());
      }
    };
    this.__insertionAdapterOfRespuesta = new EntityInsertionAdapter<Respuesta>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `Respuesta` (`id`,`idPregunta`,`respuestaSeleccionada`,`esCorrecta`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Respuesta entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getIdPregunta());
        statement.bindString(3, entity.getRespuestaSeleccionada());
        final int _tmp = entity.getEsCorrecta() ? 1 : 0;
        statement.bindLong(4, _tmp);
      }
    };
    this.__deletionAdapterOfPregunta = new EntityDeletionOrUpdateAdapter<Pregunta>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `Pregunta` WHERE `id_pregunta` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Pregunta entity) {
        statement.bindLong(1, entity.getIdPregunta());
      }
    };
    this.__updateAdapterOfPregunta = new EntityDeletionOrUpdateAdapter<Pregunta>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `Pregunta` SET `id_pregunta` = ?,`tx_pregunta` = ?,`respuesta1` = ?,`respuesta2` = ?,`respuesta3` = ?,`respuesta4` = ?,`referenciaRespuestaCorrecta` = ? WHERE `id_pregunta` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Pregunta entity) {
        statement.bindLong(1, entity.getIdPregunta());
        statement.bindString(2, entity.getTxPregunta());
        statement.bindString(3, entity.getRespuesta1());
        statement.bindString(4, entity.getRespuesta2());
        statement.bindString(5, entity.getRespuesta3());
        statement.bindString(6, entity.getRespuesta4());
        statement.bindLong(7, entity.getReferenciaRespuestaCorrecta());
        statement.bindLong(8, entity.getIdPregunta());
      }
    };
  }

  @Override
  public Object insert(final Pregunta pregunta, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPregunta.insert(pregunta);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public void insertPregunta(final Pregunta pregunta) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPregunta.insert(pregunta);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Object insertRespuesta(final Respuesta respuesta,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRespuesta.insert(respuesta);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final Pregunta pregunta, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPregunta.handle(pregunta);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final Pregunta pregunta, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPregunta.handle(pregunta);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public void updatePregunta(final Pregunta pregunta) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfPregunta.handle(pregunta);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Pregunta>> getAllLive() {
    final String _sql = "Select * From Pregunta";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"Pregunta"}, false, new Callable<List<Pregunta>>() {
      @Override
      @Nullable
      public List<Pregunta> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfIdPregunta = CursorUtil.getColumnIndexOrThrow(_cursor, "id_pregunta");
          final int _cursorIndexOfTxPregunta = CursorUtil.getColumnIndexOrThrow(_cursor, "tx_pregunta");
          final int _cursorIndexOfRespuesta1 = CursorUtil.getColumnIndexOrThrow(_cursor, "respuesta1");
          final int _cursorIndexOfRespuesta2 = CursorUtil.getColumnIndexOrThrow(_cursor, "respuesta2");
          final int _cursorIndexOfRespuesta3 = CursorUtil.getColumnIndexOrThrow(_cursor, "respuesta3");
          final int _cursorIndexOfRespuesta4 = CursorUtil.getColumnIndexOrThrow(_cursor, "respuesta4");
          final int _cursorIndexOfReferenciaRespuestaCorrecta = CursorUtil.getColumnIndexOrThrow(_cursor, "referenciaRespuestaCorrecta");
          final List<Pregunta> _result = new ArrayList<Pregunta>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Pregunta _item;
            final int _tmpIdPregunta;
            _tmpIdPregunta = _cursor.getInt(_cursorIndexOfIdPregunta);
            final String _tmpTxPregunta;
            _tmpTxPregunta = _cursor.getString(_cursorIndexOfTxPregunta);
            final String _tmpRespuesta1;
            _tmpRespuesta1 = _cursor.getString(_cursorIndexOfRespuesta1);
            final String _tmpRespuesta2;
            _tmpRespuesta2 = _cursor.getString(_cursorIndexOfRespuesta2);
            final String _tmpRespuesta3;
            _tmpRespuesta3 = _cursor.getString(_cursorIndexOfRespuesta3);
            final String _tmpRespuesta4;
            _tmpRespuesta4 = _cursor.getString(_cursorIndexOfRespuesta4);
            final int _tmpReferenciaRespuestaCorrecta;
            _tmpReferenciaRespuestaCorrecta = _cursor.getInt(_cursorIndexOfReferenciaRespuestaCorrecta);
            _item = new Pregunta(_tmpIdPregunta,_tmpTxPregunta,_tmpRespuesta1,_tmpRespuesta2,_tmpRespuesta3,_tmpRespuesta4,_tmpReferenciaRespuestaCorrecta);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getAll(final Continuation<? super List<Pregunta>> $completion) {
    final String _sql = "Select * From Pregunta";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Pregunta>>() {
      @Override
      @NonNull
      public List<Pregunta> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfIdPregunta = CursorUtil.getColumnIndexOrThrow(_cursor, "id_pregunta");
          final int _cursorIndexOfTxPregunta = CursorUtil.getColumnIndexOrThrow(_cursor, "tx_pregunta");
          final int _cursorIndexOfRespuesta1 = CursorUtil.getColumnIndexOrThrow(_cursor, "respuesta1");
          final int _cursorIndexOfRespuesta2 = CursorUtil.getColumnIndexOrThrow(_cursor, "respuesta2");
          final int _cursorIndexOfRespuesta3 = CursorUtil.getColumnIndexOrThrow(_cursor, "respuesta3");
          final int _cursorIndexOfRespuesta4 = CursorUtil.getColumnIndexOrThrow(_cursor, "respuesta4");
          final int _cursorIndexOfReferenciaRespuestaCorrecta = CursorUtil.getColumnIndexOrThrow(_cursor, "referenciaRespuestaCorrecta");
          final List<Pregunta> _result = new ArrayList<Pregunta>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Pregunta _item;
            final int _tmpIdPregunta;
            _tmpIdPregunta = _cursor.getInt(_cursorIndexOfIdPregunta);
            final String _tmpTxPregunta;
            _tmpTxPregunta = _cursor.getString(_cursorIndexOfTxPregunta);
            final String _tmpRespuesta1;
            _tmpRespuesta1 = _cursor.getString(_cursorIndexOfRespuesta1);
            final String _tmpRespuesta2;
            _tmpRespuesta2 = _cursor.getString(_cursorIndexOfRespuesta2);
            final String _tmpRespuesta3;
            _tmpRespuesta3 = _cursor.getString(_cursorIndexOfRespuesta3);
            final String _tmpRespuesta4;
            _tmpRespuesta4 = _cursor.getString(_cursorIndexOfRespuesta4);
            final int _tmpReferenciaRespuestaCorrecta;
            _tmpReferenciaRespuestaCorrecta = _cursor.getInt(_cursorIndexOfReferenciaRespuestaCorrecta);
            _item = new Pregunta(_tmpIdPregunta,_tmpTxPregunta,_tmpRespuesta1,_tmpRespuesta2,_tmpRespuesta3,_tmpRespuesta4,_tmpReferenciaRespuestaCorrecta);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getById(final int idPregunta, final Continuation<? super Pregunta> $completion) {
    final String _sql = "Select * From Pregunta Where id_pregunta = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, idPregunta);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Pregunta>() {
      @Override
      @NonNull
      public Pregunta call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfIdPregunta = CursorUtil.getColumnIndexOrThrow(_cursor, "id_pregunta");
          final int _cursorIndexOfTxPregunta = CursorUtil.getColumnIndexOrThrow(_cursor, "tx_pregunta");
          final int _cursorIndexOfRespuesta1 = CursorUtil.getColumnIndexOrThrow(_cursor, "respuesta1");
          final int _cursorIndexOfRespuesta2 = CursorUtil.getColumnIndexOrThrow(_cursor, "respuesta2");
          final int _cursorIndexOfRespuesta3 = CursorUtil.getColumnIndexOrThrow(_cursor, "respuesta3");
          final int _cursorIndexOfRespuesta4 = CursorUtil.getColumnIndexOrThrow(_cursor, "respuesta4");
          final int _cursorIndexOfReferenciaRespuestaCorrecta = CursorUtil.getColumnIndexOrThrow(_cursor, "referenciaRespuestaCorrecta");
          final Pregunta _result;
          if (_cursor.moveToFirst()) {
            final int _tmpIdPregunta;
            _tmpIdPregunta = _cursor.getInt(_cursorIndexOfIdPregunta);
            final String _tmpTxPregunta;
            _tmpTxPregunta = _cursor.getString(_cursorIndexOfTxPregunta);
            final String _tmpRespuesta1;
            _tmpRespuesta1 = _cursor.getString(_cursorIndexOfRespuesta1);
            final String _tmpRespuesta2;
            _tmpRespuesta2 = _cursor.getString(_cursorIndexOfRespuesta2);
            final String _tmpRespuesta3;
            _tmpRespuesta3 = _cursor.getString(_cursorIndexOfRespuesta3);
            final String _tmpRespuesta4;
            _tmpRespuesta4 = _cursor.getString(_cursorIndexOfRespuesta4);
            final int _tmpReferenciaRespuestaCorrecta;
            _tmpReferenciaRespuestaCorrecta = _cursor.getInt(_cursorIndexOfReferenciaRespuestaCorrecta);
            _result = new Pregunta(_tmpIdPregunta,_tmpTxPregunta,_tmpRespuesta1,_tmpRespuesta2,_tmpRespuesta3,_tmpRespuesta4,_tmpReferenciaRespuestaCorrecta);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public List<Pregunta> getAllPreguntas() {
    final String _sql = "SELECT * FROM Pregunta";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIdPregunta = CursorUtil.getColumnIndexOrThrow(_cursor, "id_pregunta");
      final int _cursorIndexOfTxPregunta = CursorUtil.getColumnIndexOrThrow(_cursor, "tx_pregunta");
      final int _cursorIndexOfRespuesta1 = CursorUtil.getColumnIndexOrThrow(_cursor, "respuesta1");
      final int _cursorIndexOfRespuesta2 = CursorUtil.getColumnIndexOrThrow(_cursor, "respuesta2");
      final int _cursorIndexOfRespuesta3 = CursorUtil.getColumnIndexOrThrow(_cursor, "respuesta3");
      final int _cursorIndexOfRespuesta4 = CursorUtil.getColumnIndexOrThrow(_cursor, "respuesta4");
      final int _cursorIndexOfReferenciaRespuestaCorrecta = CursorUtil.getColumnIndexOrThrow(_cursor, "referenciaRespuestaCorrecta");
      final List<Pregunta> _result = new ArrayList<Pregunta>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Pregunta _item;
        final int _tmpIdPregunta;
        _tmpIdPregunta = _cursor.getInt(_cursorIndexOfIdPregunta);
        final String _tmpTxPregunta;
        _tmpTxPregunta = _cursor.getString(_cursorIndexOfTxPregunta);
        final String _tmpRespuesta1;
        _tmpRespuesta1 = _cursor.getString(_cursorIndexOfRespuesta1);
        final String _tmpRespuesta2;
        _tmpRespuesta2 = _cursor.getString(_cursorIndexOfRespuesta2);
        final String _tmpRespuesta3;
        _tmpRespuesta3 = _cursor.getString(_cursorIndexOfRespuesta3);
        final String _tmpRespuesta4;
        _tmpRespuesta4 = _cursor.getString(_cursorIndexOfRespuesta4);
        final int _tmpReferenciaRespuestaCorrecta;
        _tmpReferenciaRespuestaCorrecta = _cursor.getInt(_cursorIndexOfReferenciaRespuestaCorrecta);
        _item = new Pregunta(_tmpIdPregunta,_tmpTxPregunta,_tmpRespuesta1,_tmpRespuesta2,_tmpRespuesta3,_tmpRespuesta4,_tmpReferenciaRespuestaCorrecta);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Object getRespuestasByPregunta(final int idPregunta,
      final Continuation<? super List<Respuesta>> $completion) {
    final String _sql = "SELECT * FROM Respuesta WHERE idPregunta = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, idPregunta);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Respuesta>>() {
      @Override
      @NonNull
      public List<Respuesta> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfIdPregunta = CursorUtil.getColumnIndexOrThrow(_cursor, "idPregunta");
          final int _cursorIndexOfRespuestaSeleccionada = CursorUtil.getColumnIndexOrThrow(_cursor, "respuestaSeleccionada");
          final int _cursorIndexOfEsCorrecta = CursorUtil.getColumnIndexOrThrow(_cursor, "esCorrecta");
          final List<Respuesta> _result = new ArrayList<Respuesta>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Respuesta _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final int _tmpIdPregunta;
            _tmpIdPregunta = _cursor.getInt(_cursorIndexOfIdPregunta);
            final String _tmpRespuestaSeleccionada;
            _tmpRespuestaSeleccionada = _cursor.getString(_cursorIndexOfRespuestaSeleccionada);
            final boolean _tmpEsCorrecta;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfEsCorrecta);
            _tmpEsCorrecta = _tmp != 0;
            _item = new Respuesta(_tmpId,_tmpIdPregunta,_tmpRespuestaSeleccionada,_tmpEsCorrecta);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object contarRespuestasCorrectas(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM Respuesta WHERE esCorrecta = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
