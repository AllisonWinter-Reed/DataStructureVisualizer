package com.example.datastructurevisualizer.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.datastructurevisualizer.R;
import com.example.datastructurevisualizer.TreeVisualizer;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DialogFileDelete extends DialogFragment {
    private Button fileFinalDelete;
    private Button fileFinalCancel;
    private String fileNameText;
    private boolean deleted;
    private DialogFileAction parentFrag;

    public DialogFileDelete() {
    Log.d("Point Reached", "DialogFile Action");
    }

    public void setFileNameText(String fileNameText) {
        this.fileNameText = fileNameText;
    }

    public void setParentFrag(DialogFileAction parentFrag) {
        this.parentFrag = parentFrag;
    }
    public boolean isDeleted() {
        return deleted;
    }


    public static DialogFileDelete newInstance(String title) {
        DialogFileDelete fileDeleteDialog = new DialogFileDelete();
        Bundle args = new Bundle();
        args.putString("final delete",title);
        fileDeleteDialog.setArguments(args);
        return fileDeleteDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_delete_file, container);
        fileFinalDelete = view.findViewById(R.id.fileFinalDelete);
        fileFinalCancel = view.findViewById(R.id.fileFinalCancel);

        fileFinalDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();

                java.io.File dir = context.getFilesDir();
                File file = new File(dir, fileNameText);
                deleted = file.delete();

                if (deleted) {
                    Toast toast = Toast.makeText(getActivity(), "File " + fileNameText + " successfully deleted", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                    parentFrag.dismiss();

                }
                else{
                    Toast toast = Toast.makeText(getActivity(), "File " + fileNameText + " failed to delete. Please restart app and try again.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                }
                dismiss();

            }
        });

        fileFinalCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }


}