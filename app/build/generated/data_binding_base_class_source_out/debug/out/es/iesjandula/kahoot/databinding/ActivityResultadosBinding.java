// Generated by view binder compiler. Do not edit!
package es.iesjandula.kahoot.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public final class ActivityResultadosBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnVolverMenu;

  @NonNull
  public final CardView cardResultadosContainer;

  @NonNull
  public final ConstraintLayout resultadosAppbar;

  @NonNull
  public final TextView resultadosAppbarTitle;

  @NonNull
  public final TextView tvResultadoFinal;

  private ActivityResultadosBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button btnVolverMenu, @NonNull CardView cardResultadosContainer,
      @NonNull ConstraintLayout resultadosAppbar, @NonNull TextView resultadosAppbarTitle,
      @NonNull TextView tvResultadoFinal) {
    this.rootView = rootView;
    this.btnVolverMenu = btnVolverMenu;
    this.cardResultadosContainer = cardResultadosContainer;
    this.resultadosAppbar = resultadosAppbar;
    this.resultadosAppbarTitle = resultadosAppbarTitle;
    this.tvResultadoFinal = tvResultadoFinal;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityResultadosBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityResultadosBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_resultados, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityResultadosBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnVolverMenu;
      Button btnVolverMenu = ViewBindings.findChildViewById(rootView, id);
      if (btnVolverMenu == null) {
        break missingId;
      }

      id = R.id.cardResultadosContainer;
      CardView cardResultadosContainer = ViewBindings.findChildViewById(rootView, id);
      if (cardResultadosContainer == null) {
        break missingId;
      }

      id = R.id.resultadosAppbar;
      ConstraintLayout resultadosAppbar = ViewBindings.findChildViewById(rootView, id);
      if (resultadosAppbar == null) {
        break missingId;
      }

      id = R.id.resultadosAppbarTitle;
      TextView resultadosAppbarTitle = ViewBindings.findChildViewById(rootView, id);
      if (resultadosAppbarTitle == null) {
        break missingId;
      }

      id = R.id.tvResultadoFinal;
      TextView tvResultadoFinal = ViewBindings.findChildViewById(rootView, id);
      if (tvResultadoFinal == null) {
        break missingId;
      }

      return new ActivityResultadosBinding((ConstraintLayout) rootView, btnVolverMenu,
          cardResultadosContainer, resultadosAppbar, resultadosAppbarTitle, tvResultadoFinal);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
