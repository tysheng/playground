package com.tysheng.playground.share;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.tysheng.playground.R;

/**
 * Created by tysheng
 * Date: 4/10/17 1:40 PM.
 * Email: tyshengsx@gmail.com
 */

public class ProgressDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(R.layout.dialog_progress);
        return builder.create();
    }
}
