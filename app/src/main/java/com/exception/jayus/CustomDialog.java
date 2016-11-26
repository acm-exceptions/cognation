package com.exception.jayus;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.widget.ImageView;
import android.widget.Toast;

public class CustomDialog extends DialogFragment
{
    private static final int IMAGE_CAPTURE_CODE = 0;
    private static final int IMAGE_SELECT_CODE = 1;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        String[] items = {"Take a Photo", "Upload"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setItems(items, new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                switch (i)
                {
                    case 0:
                        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        getActivity().startActivityForResult(captureIntent, IMAGE_CAPTURE_CODE);
                        break;
                    case 1:
                        Intent selectIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        getActivity().startActivityForResult(selectIntent, IMAGE_SELECT_CODE);
                        break;
                    default:
                        break;
                }
            }

        });

        return builder.create();
    }
}
