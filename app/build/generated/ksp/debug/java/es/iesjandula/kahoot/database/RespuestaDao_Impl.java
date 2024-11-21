package es.iesjandula.kahoot.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
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
public final class RespuestaDao_Impl implements RespuestaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Respuesta> __insertionAdapterOfRespuesta;

  public RespuestaDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
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
