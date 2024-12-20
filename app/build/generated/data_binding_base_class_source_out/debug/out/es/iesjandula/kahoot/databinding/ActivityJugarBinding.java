// Generated by view binder compiler. Do not edit!
package es.iesjandula.kahoot.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import es.iesjandula.kahoot.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityJugarBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnContestarJugar;

  @NonNull
  public final Button btnRespuesta1;

  @NonNull
  public final Button btnRespuesta2;

  @NonNull
  public final Button btnRespuesta3;

  @NonNull
  public final Button btnRespuesta4;

  @NonNull
  public final Button btnSalirJugar;

  @NonNull
  public final CardView cardJugarContainer;

  @NonNull
  public final ConstraintLayout jugar;

  @NonNull
  public final ConstraintLayout jugarAppbar;

  @NonNull
  public final ImageView jugarAppbarIcon;

  @NonNull
  public final TextView jugarAppbarTitle;

  @NonNull
  public final TextView tvPreguntaActual;

  @NonNull
  public final TextView tvSeleccionaRespuesta;

  private ActivityJugarBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button btnContestarJugar, @NonNull Button btnRespuesta1,
      @NonNull Button btnRespuesta2, @NonNull Button btnRespuesta3, @NonNull Button btnRespuesta4,
      @NonNull Button btnSalirJugar, @NonNull CardView cardJugarContainer,
      @NonNull ConstraintLayout jugar, @NonNull ConstraintLayout jugarAppbar,
      @NonNull ImageView jugarAppbarIcon, @NonNull TextView jugarAppbarTitle,
      @NonNull TextView tvPreguntaActual, @NonNull TextView tvSeleccionaRespuesta) {
    this.rootView = rootView;
    this.btnContestarJugar = btnContestarJugar;
    this.btnRespuesta1 = btnRespuesta1;
    this.btnRespuesta2 = btnRespuesta2;
    this.btnRespuesta3 = btnRespuesta3;
    this.btnRespuesta4 = btnRespuesta4;
    this.btnSalirJugar = btnSalirJugar;
    this.cardJugarContainer = cardJugarContainer;
    this.jugar = jugar;
    this.jugarAppbar = jugarAppbar;
    this.jugarAppbarIcon = jugarAppbarIcon;
    this.jugarAppbarTitle = jugarAppbarTitle;
    this.tvPreguntaActual = tvPreguntaActual;
    this.tvSeleccionaRespuesta = tvSeleccionaRespuesta;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityJugarBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityJugarBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_jugar, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityJugarBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnContestarJugar;
      Button btnContestarJugar = ViewBindings.findChildViewById(rootView, id);
      if (btnContestarJugar == null) {
        break missingId;
      }

      id = R.id.btnRespuesta1;
      Button btnRespuesta1 = ViewBindings.findChildViewById(rootView, id);
      if (btnRespuesta1 == null) {
        break missingId;
      }

      id = R.id.btnRespuesta2;
      Button btnRespuesta2 = ViewBindings.findChildViewById(rootView, id);
      if (btnRespuesta2 == null) {
        break missingId;
      }

      id = R.id.btnRespuesta3;
      Button btnRespuesta3 = ViewBindings.findChildViewById(rootView, id);
      if (btnRespuesta3 == null) {
        break missingId;
      }

      id = R.id.btnRespuesta4;
      Button btnRespuesta4 = ViewBindings.findChildViewById(rootView, id);
      if (btnRespuesta4 == null) {
        break missingId;
      }

      id = R.id.btnSalirJugar;
      Button btnSalirJugar = ViewBindings.findChildViewById(rootView, id);
      if (btnSalirJugar == null) {
        break missingId;
      }

      id = R.id.cardJugarContainer;
      CardView cardJugarContainer = ViewBindings.findChildViewById(rootView, id);
      if (cardJugarContainer == null) {
        break missingId;
      }

      ConstraintLayout jugar = (ConstraintLayout) rootView;

      id = R.id.jugarAppbar;
      ConstraintLayout jugarAppbar = ViewBindings.findChildViewById(rootView, id);
      if (jugarAppbar == null) {
        break missingId;
      }

      id = R.id.jugarAppbarIcon;
      ImageView jugarAppbarIcon = ViewBindings.findChildViewById(rootView, id);
      if (jugarAppbarIcon == null) {
        break missingId;
      }

      id = R.id.jugarAppbarTitle;
      TextView jugarAppbarTitle = ViewBindings.findChildViewById(rootView, id);
      if (jugarAppbarTitle == null) {
        break missingId;
      }

      id = R.id.tvPreguntaActual;
      TextView tvPreguntaActual = ViewBindings.findChildViewById(rootView, id);
      if (tvPreguntaActual == null) {
        break missingId;
      }

      id = R.id.tvSeleccionaRespuesta;
      TextView tvSeleccionaRespuesta = ViewBindings.findChildViewById(rootView, id);
      if (tvSeleccionaRespuesta == null) {
        break missingId;
      }

      return new ActivityJugarBinding((ConstraintLayout) rootView, btnContestarJugar, btnRespuesta1,
          btnRespuesta2, btnRespuesta3, btnRespuesta4, btnSalirJugar, cardJugarContainer, jugar,
          jugarAppbar, jugarAppbarIcon, jugarAppbarTitle, tvPreguntaActual, tvSeleccionaRespuesta);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
