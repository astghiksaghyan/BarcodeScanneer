package com.delaroystudios.barcodescanner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static android.content.Context.CLIPBOARD_SERVICE;

public class BarcodeDialogFragment extends DialogFragment {
    public static BarcodeDialogFragment newInstance(String title) {
        BarcodeDialogFragment frag = new BarcodeDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        final String title = getArguments().getString("title");
        final Button button_left = v.findViewById(R.id.but_left);
        final Button button_right = v.findViewById(R.id.but_right);
        button_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyToClipboard(title);
                dismiss();
            }
        });
        button_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        TextView textView = v.findViewById(R.id.massage);
        textView.setText(title);

        // Do all the stuff to initialize your custom view

        return v;
    }


    public void copyToClipboard(String data) {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", data);
        clipboard.setPrimaryClip(clip);
    }
}