package vip.hengnai.wine.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import vip.hengnai.wine.R;

/**
 * on 2019/12/6.
 *
 * @author hua.
 */

public class LocationAlert {
    /**
     * 定位失败弹窗
     */
    public static void showDialog(Context context) {
        final AlertDialog dlg = new AlertDialog.Builder(context, R.style.ActionSheetDialogStyle).create();
        dlg.show();
        Window window = dlg.getWindow();
        window.setContentView(R.layout.alert_dialog_location);
        window.setGravity(Gravity.CENTER);
        TextView textView = window.findViewById(R.id.dialog_text_title);
        textView.setText("哎呀，定位失败");
        window.findViewById(R.id.again_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyMapUtils.startLocation();
                dlg.dismiss();
            }
        });
        window.findViewById(R.id.other_shop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dlg.dismiss();
            }
        });
    }
}
