package com.example.staffinemployees.Fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.staffinemployees.MainActivity;
import com.example.staffinemployees.databinding.FragmentClaimExpencesBinding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ClaimExpences extends Fragment {
    List<String> imagePath;
    FragmentClaimExpencesBinding binding;
    TextView addText;
    static int count = 0;
    String title;
    String amount;
    boolean atleastOne = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentClaimExpencesBinding.inflate(inflater, container, false);
        clickListeners();

        return binding.getRoot();
    }

    private void clickListeners() {
        imagePath = new ArrayList<>();

        binding.openCameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 101);
            }
        });

        binding.clickToAddTv.setOnClickListener(v -> {
            Intent imgIntent = new Intent(Intent.ACTION_PICK);
            imgIntent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(imgIntent, 100);
        });

        binding.SubmitBtn.setOnClickListener(v -> {
            if (binding.titleEt.getText().toString().isEmpty()) {
                binding.titleEt.setError("enter title");
                binding.titleEt.requestFocus();
            } else if (!atleastOne) {
                Toast.makeText(getActivity(), "upload atleast 1 image", Toast.LENGTH_SHORT).show();
            } else if (binding.amountEt.getText().toString().isEmpty()) {
                binding.amountEt.setError("enter amount");
                binding.amountEt.requestFocus();
            } else {
                title = binding.titleEt.getText().toString();
                amount = binding.amountEt.getText().toString();

                startActivity(new Intent(getActivity(), MainActivity.class));
                Toast.makeText(getContext(), "submitted", Toast.LENGTH_SHORT).show();
                Log.d("imagePathImagePath", String.valueOf(imagePath));
                getActivity().finish();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String imgString;

        if (resultCode == RESULT_OK && requestCode == 100) {
            imgString = getRealPathFromURI(data.getData());
            Log.d("ndfsdnf", imgString);
            imagePath.add(imgString);
            addText = new TextView(getContext());

            if (count == 0) {
                count = 1;
                addText.setText(count + ".  " + imgString);
            } else {
                count += 1;
                addText.setText(count + ".  " + imgString);
            }

            addText.setTextSize(12);
            addText.setMaxLines(1);
            addText.setTextColor(Color.parseColor("#808080"));
            binding.llVertical.addView(addText);
            atleastOne = true;
        }

        if (requestCode == 101 && resultCode == RESULT_OK) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");
//            binding.openCameraBtn.setImageBitmap(photo);
            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = getImageUri(getActivity(), photo);

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            File finalFile = new File(getRealPathFromURI(tempUri));

            // Set the image in imageview for display
            Log.d("ndfsdnf", finalFile.toString());

            imgString = finalFile.toString();
            imagePath.add(finalFile.toString());
            addText = new TextView(getContext());

            if (count == 0) {
                count = 1;
                addText.setText(count + ".  " + imgString);
            } else {
                count += 1;
                addText.setText(count + ".  " + imgString);
            }

            addText.setTextSize(12);
            addText.setMaxLines(1);
            addText.setTextColor(Color.parseColor("#808080"));
            binding.llVertical.addView(addText);
            atleastOne = true;

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        count = 0;
    }

    public String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = requireContext().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage,
                "Title", null);
        return Uri.parse(path);
    }

}