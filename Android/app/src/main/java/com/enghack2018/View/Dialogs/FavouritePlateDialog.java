package com.enghack2018.View.Dialogs;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.enghack2018.Model.PlateDO;
import com.enghack2018.R;

public class FavouritePlateDialog extends Dialog implements View.OnClickListener {

    private PlateDO plateDO;

    public FavouritePlateDialog(@NonNull Context context, int style, PlateDO plateDO) {
        super(context, style);
        this.plateDO = plateDO;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        setupDialogComponents();
    }

    private void setupDialogComponents() {
        ((TextView)findViewById(R.id.plateName)).setText(plateDO.getName());
        ((TextView)findViewById(R.id.googleMapsLink)).setText(R.string.view_location);
        ((TextView)findViewById(R.id.googleMapsLink)).setTextColor(Color.BLUE);
        findViewById(R.id.googleMapsLink).setOnClickListener(view ->{
            String query = plateDO.getName().replaceAll("\\s", "+");
            String urlString="https://www.google.ca/maps/place/" + query;
            Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage("com.android.chrome");
            try {
                getContext().startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                // Chrome browser presumably not installed so allow user to choose instead
                intent.setPackage(null);
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {}
}
