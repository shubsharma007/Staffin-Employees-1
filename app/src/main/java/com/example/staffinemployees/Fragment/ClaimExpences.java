package com.example.staffinemployees.Fragment;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.staffinemployees.Interface.ApiInterface;
import com.example.staffinemployees.MainActivity;
import com.example.staffinemployees.Response.LoginResponse;
import com.example.staffinemployees.Retrofit.RetrofitServices;
import com.example.staffinemployees.databinding.FragmentClaimExpencesBinding;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Part;
import retrofit2.http.Path;


public class ClaimExpences extends Fragment {
    public static final int CAMERA = 1234;
    public static final int GALLERY = 5342;

    List<String> imagePath;
    FragmentClaimExpencesBinding binding;
    TextView addText;
    static int count = 0;
    String naame = " ";
    String priice = " ";
    boolean atleastOne = false;
    String Id;
    ProgressDialog progress;
    SharedPreferences sharedPreferences;
    ApiInterface apiInterface;
    MultipartBody.Part part1, part2, part3, part4, part5, part6, part7, part8, part9, part10;
    String image1, image2, image3, image4, image5, image6, image7, image8, image9, image10;
    File ximg1, ximg2, ximg3, ximg4, ximg5, ximg6, ximg7, ximg8, ximg9, ximg10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentClaimExpencesBinding.inflate(inflater, container, false);
        clickListeners();
        progress = new ProgressDialog(getContext());
        progress.setMessage("please wait...");
        progress.setCancelable(false);
        sharedPreferences = getContext().getSharedPreferences("staffin", Context.MODE_PRIVATE);
        Id = sharedPreferences.getAll().get("Id").toString();
        return binding.getRoot();
    }

    private void clickListeners() {
        imagePath = new ArrayList<>();
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
        binding.openCameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

                    if (imagePath.size() <= 10) {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA);
                    } else {
                        Toast.makeText(getContext(), "you can't add more than 10 images", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "Your Device Does not Support This Functionality\n Please Click On (Click To Upload Images)", Toast.LENGTH_SHORT).show();
                }

            }
        });

        binding.clickToAddTv.setOnClickListener(v -> {
            if (imagePath.size() <= 10) {
                Intent imgIntent = new Intent(Intent.ACTION_PICK);
                imgIntent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(imgIntent, GALLERY);
            } else {
                Toast.makeText(getContext(), "you can't add more than 10 images", Toast.LENGTH_SHORT).show();
            }
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
                naame = binding.titleEt.getText().toString();
                priice = binding.amountEt.getText().toString();


                updateDetails(imagePath, naame, priice);


            }
        });

    }

    private void updateDetails(List<String> imagePath, String naame, String priice) {


        if (atleastOne) {

            RequestBody name = RequestBody.create(MediaType.parse("text/plain"), naame);
            RequestBody price = RequestBody.create(MediaType.parse("text/plain"), priice);
            RequestBody rimg1, rimg2, rimg3, rimg4, rimg5, rimg6, rimg7, rimg8, rimg9, rimg10;


            if (imagePath.size() == 1) {
                ximg1 = new File(imagePath.get(0));
                rimg1 = RequestBody.create(MediaType.parse("image/*"), ximg1);
                part1 = MultipartBody.Part.createFormData("image1", ximg1.getName(), rimg1);
                part2 = null;
                part3 = null;
                part4 = null;
                part5 = null;
                part6 = null;
                part7 = null;
                part8 = null;
                part9 = null;
                part10 = null;

            } else if (imagePath.size() == 2) {
                ximg1 = new File(imagePath.get(0));
                rimg1 = RequestBody.create(MediaType.parse("image/*"), ximg1);
                part1 = MultipartBody.Part.createFormData("image1", ximg1.getName(), rimg1);

                ximg2 = new File(imagePath.get(1));
                rimg2 = RequestBody.create(MediaType.parse("image/*"), ximg2);
                part2 = MultipartBody.Part.createFormData("image2", ximg2.getName(), rimg2);

                part3 = null;
                part4 = null;
                part5 = null;
                part6 = null;
                part7 = null;
                part8 = null;
                part9 = null;
                part10 = null;
            } else if (imagePath.size() == 3) {
                ximg1 = new File(imagePath.get(0));
                rimg1 = RequestBody.create(MediaType.parse("image/*"), ximg1);
                part1 = MultipartBody.Part.createFormData("image1", ximg1.getName(), rimg1);

                ximg2 = new File(imagePath.get(1));
                rimg2 = RequestBody.create(MediaType.parse("image/*"), ximg2);
                part2 = MultipartBody.Part.createFormData("image2", ximg2.getName(), rimg2);

                ximg3 = new File(imagePath.get(2));
                rimg3 = RequestBody.create(MediaType.parse("image/*"), ximg3);
                part3 = MultipartBody.Part.createFormData("image3", ximg3.getName(), rimg3);

                part4 = null;
                part5 = null;
                part6 = null;
                part7 = null;
                part8 = null;
                part9 = null;
                part10 = null;
            } else if (imagePath.size() == 4) {
                ximg1 = new File(imagePath.get(0));
                rimg1 = RequestBody.create(MediaType.parse("image/*"), ximg1);
                part1 = MultipartBody.Part.createFormData("image1", ximg1.getName(), rimg1);

                ximg2 = new File(imagePath.get(1));
                rimg2 = RequestBody.create(MediaType.parse("image/*"), ximg2);
                part2 = MultipartBody.Part.createFormData("image2", ximg2.getName(), rimg2);

                ximg3 = new File(imagePath.get(2));
                rimg3 = RequestBody.create(MediaType.parse("image/*"), ximg3);
                part3 = MultipartBody.Part.createFormData("image3", ximg3.getName(), rimg3);

                ximg4 = new File(imagePath.get(3));
                rimg4 = RequestBody.create(MediaType.parse("image/*"), ximg4);
                part4 = MultipartBody.Part.createFormData("image4", ximg4.getName(), rimg4);


                part5 = null;
                part6 = null;
                part7 = null;
                part8 = null;
                part9 = null;
                part10 = null;
            } else if (imagePath.size() == 5) {
                ximg1 = new File(imagePath.get(0));
                rimg1 = RequestBody.create(MediaType.parse("image/*"), ximg1);
                part1 = MultipartBody.Part.createFormData("image1", ximg1.getName(), rimg1);

                ximg2 = new File(imagePath.get(1));
                rimg2 = RequestBody.create(MediaType.parse("image/*"), ximg2);
                part2 = MultipartBody.Part.createFormData("image2", ximg2.getName(), rimg2);

                ximg3 = new File(imagePath.get(2));
                rimg3 = RequestBody.create(MediaType.parse("image/*"), ximg3);
                part3 = MultipartBody.Part.createFormData("image3", ximg3.getName(), rimg3);

                ximg4 = new File(imagePath.get(3));
                rimg4 = RequestBody.create(MediaType.parse("image/*"), ximg4);
                part4 = MultipartBody.Part.createFormData("image4", ximg4.getName(), rimg4);

                ximg5 = new File(imagePath.get(4));
                rimg5 = RequestBody.create(MediaType.parse("image/*"), ximg5);
                part5 = MultipartBody.Part.createFormData("image5", ximg5.getName(), rimg5);


                part6 = null;
                part7 = null;
                part8 = null;
                part9 = null;
                part10 = null;
            } else if (imagePath.size() == 6) {
                ximg1 = new File(imagePath.get(0));
                rimg1 = RequestBody.create(MediaType.parse("image/*"), ximg1);
                part1 = MultipartBody.Part.createFormData("image1", ximg1.getName(), rimg1);

                ximg2 = new File(imagePath.get(1));
                rimg2 = RequestBody.create(MediaType.parse("image/*"), ximg2);
                part2 = MultipartBody.Part.createFormData("image2", ximg2.getName(), rimg2);

                ximg3 = new File(imagePath.get(2));
                rimg3 = RequestBody.create(MediaType.parse("image/*"), ximg3);
                part3 = MultipartBody.Part.createFormData("image3", ximg3.getName(), rimg3);

                ximg4 = new File(imagePath.get(3));
                rimg4 = RequestBody.create(MediaType.parse("image/*"), ximg4);
                part4 = MultipartBody.Part.createFormData("image4", ximg4.getName(), rimg4);

                ximg5 = new File(imagePath.get(4));
                rimg5 = RequestBody.create(MediaType.parse("image/*"), ximg5);
                part5 = MultipartBody.Part.createFormData("image5", ximg5.getName(), rimg5);

                ximg6 = new File(imagePath.get(5));
                rimg6 = RequestBody.create(MediaType.parse("image/*"), ximg6);
                part6 = MultipartBody.Part.createFormData("image6", ximg6.getName(), rimg6);


                part7 = null;
                part8 = null;
                part9 = null;
                part10 = null;
            } else if (imagePath.size() == 7) {
                ximg1 = new File(imagePath.get(0));
                rimg1 = RequestBody.create(MediaType.parse("image/*"), ximg1);
                part1 = MultipartBody.Part.createFormData("image1", ximg1.getName(), rimg1);

                ximg2 = new File(imagePath.get(1));
                rimg2 = RequestBody.create(MediaType.parse("image/*"), ximg2);
                part2 = MultipartBody.Part.createFormData("image2", ximg2.getName(), rimg2);

                ximg3 = new File(imagePath.get(2));
                rimg3 = RequestBody.create(MediaType.parse("image/*"), ximg3);
                part3 = MultipartBody.Part.createFormData("image3", ximg3.getName(), rimg3);

                ximg4 = new File(imagePath.get(3));
                rimg4 = RequestBody.create(MediaType.parse("image/*"), ximg4);
                part4 = MultipartBody.Part.createFormData("image4", ximg4.getName(), rimg4);

                ximg5 = new File(imagePath.get(4));
                rimg5 = RequestBody.create(MediaType.parse("image/*"), ximg5);
                part5 = MultipartBody.Part.createFormData("image5", ximg5.getName(), rimg5);

                ximg6 = new File(imagePath.get(5));
                rimg6 = RequestBody.create(MediaType.parse("image/*"), ximg6);
                part6 = MultipartBody.Part.createFormData("image6", ximg6.getName(), rimg6);

                ximg7 = new File(imagePath.get(6));
                rimg7 = RequestBody.create(MediaType.parse("image/*"), ximg7);
                part7 = MultipartBody.Part.createFormData("image7", ximg7.getName(), rimg7);


                part8 = null;
                part9 = null;
                part10 = null;
            } else if (imagePath.size() == 8) {
                ximg1 = new File(imagePath.get(0));
                rimg1 = RequestBody.create(MediaType.parse("image/*"), ximg1);
                part1 = MultipartBody.Part.createFormData("image1", ximg1.getName(), rimg1);

                ximg2 = new File(imagePath.get(1));
                rimg2 = RequestBody.create(MediaType.parse("image/*"), ximg2);
                part2 = MultipartBody.Part.createFormData("image2", ximg2.getName(), rimg2);

                ximg3 = new File(imagePath.get(2));
                rimg3 = RequestBody.create(MediaType.parse("image/*"), ximg3);
                part3 = MultipartBody.Part.createFormData("image3", ximg3.getName(), rimg3);

                ximg4 = new File(imagePath.get(3));
                rimg4 = RequestBody.create(MediaType.parse("image/*"), ximg4);
                part4 = MultipartBody.Part.createFormData("image4", ximg4.getName(), rimg4);

                ximg5 = new File(imagePath.get(4));
                rimg5 = RequestBody.create(MediaType.parse("image/*"), ximg5);
                part5 = MultipartBody.Part.createFormData("image5", ximg5.getName(), rimg5);

                ximg6 = new File(imagePath.get(5));
                rimg6 = RequestBody.create(MediaType.parse("image/*"), ximg6);
                part6 = MultipartBody.Part.createFormData("image6", ximg6.getName(), rimg6);

                ximg7 = new File(imagePath.get(6));
                rimg7 = RequestBody.create(MediaType.parse("image/*"), ximg7);
                part7 = MultipartBody.Part.createFormData("image7", ximg7.getName(), rimg7);

                ximg8 = new File(imagePath.get(7));
                rimg8 = RequestBody.create(MediaType.parse("image/*"), ximg8);
                part8 = MultipartBody.Part.createFormData("image8", ximg8.getName(), rimg8);


                part9 = null;
                part10 = null;
            } else if (imagePath.size() == 9) {
                ximg1 = new File(imagePath.get(0));
                rimg1 = RequestBody.create(MediaType.parse("image/*"), ximg1);
                part1 = MultipartBody.Part.createFormData("image1", ximg1.getName(), rimg1);

                ximg2 = new File(imagePath.get(1));
                rimg2 = RequestBody.create(MediaType.parse("image/*"), ximg2);
                part2 = MultipartBody.Part.createFormData("image2", ximg2.getName(), rimg2);

                ximg3 = new File(imagePath.get(2));
                rimg3 = RequestBody.create(MediaType.parse("image/*"), ximg3);
                part3 = MultipartBody.Part.createFormData("image3", ximg3.getName(), rimg3);

                ximg4 = new File(imagePath.get(3));
                rimg4 = RequestBody.create(MediaType.parse("image/*"), ximg4);
                part4 = MultipartBody.Part.createFormData("image4", ximg4.getName(), rimg4);

                ximg5 = new File(imagePath.get(4));
                rimg5 = RequestBody.create(MediaType.parse("image/*"), ximg5);
                part5 = MultipartBody.Part.createFormData("image5", ximg5.getName(), rimg5);

                ximg6 = new File(imagePath.get(5));
                rimg6 = RequestBody.create(MediaType.parse("image/*"), ximg6);
                part6 = MultipartBody.Part.createFormData("image6", ximg6.getName(), rimg6);

                ximg7 = new File(imagePath.get(6));
                rimg7 = RequestBody.create(MediaType.parse("image/*"), ximg7);
                part7 = MultipartBody.Part.createFormData("image7", ximg7.getName(), rimg7);

                ximg8 = new File(imagePath.get(7));
                rimg8 = RequestBody.create(MediaType.parse("image/*"), ximg8);
                part8 = MultipartBody.Part.createFormData("image8", ximg8.getName(), rimg8);

                ximg9 = new File(imagePath.get(8));
                rimg9 = RequestBody.create(MediaType.parse("image/*"), ximg9);
                part9 = MultipartBody.Part.createFormData("image9", ximg9.getName(), rimg9);

                part10 = null;
            } else if (imagePath.size() == 10) {
                ximg1 = new File(imagePath.get(0));
                rimg1 = RequestBody.create(MediaType.parse("image/*"), ximg1);
                part1 = MultipartBody.Part.createFormData("image1", ximg1.getName(), rimg1);

                ximg2 = new File(imagePath.get(1));
                rimg2 = RequestBody.create(MediaType.parse("image/*"), ximg2);
                part2 = MultipartBody.Part.createFormData("image2", ximg2.getName(), rimg2);

                ximg3 = new File(imagePath.get(2));
                rimg3 = RequestBody.create(MediaType.parse("image/*"), ximg3);
                part3 = MultipartBody.Part.createFormData("image3", ximg3.getName(), rimg3);

                ximg4 = new File(imagePath.get(3));
                rimg4 = RequestBody.create(MediaType.parse("image/*"), ximg4);
                part4 = MultipartBody.Part.createFormData("image4", ximg4.getName(), rimg4);

                ximg5 = new File(imagePath.get(4));
                rimg5 = RequestBody.create(MediaType.parse("image/*"), ximg5);
                part5 = MultipartBody.Part.createFormData("image5", ximg5.getName(), rimg5);

                ximg6 = new File(imagePath.get(5));
                rimg6 = RequestBody.create(MediaType.parse("image/*"), ximg6);
                part6 = MultipartBody.Part.createFormData("image6", ximg6.getName(), rimg6);

                ximg7 = new File(imagePath.get(6));
                rimg7 = RequestBody.create(MediaType.parse("image/*"), ximg7);
                part7 = MultipartBody.Part.createFormData("image7", ximg7.getName(), rimg7);

                ximg8 = new File(imagePath.get(7));
                rimg8 = RequestBody.create(MediaType.parse("image/*"), ximg8);
                part8 = MultipartBody.Part.createFormData("image8", ximg8.getName(), rimg8);

                ximg9 = new File(imagePath.get(8));
                rimg9 = RequestBody.create(MediaType.parse("image/*"), ximg9);
                part9 = MultipartBody.Part.createFormData("image9", ximg9.getName(), rimg9);

                ximg10 = new File(imagePath.get(9));
                rimg10 = RequestBody.create(MediaType.parse("image/*"), ximg10);
                part10 = MultipartBody.Part.createFormData("image10", ximg10.getName(), rimg10);

            }

            Call<LoginResponse> callPostExpenses = apiInterface.postExpenses(Integer.parseInt(Id), part1, part2, part3, part4, part5, part6,
                    part7, part8, part9, part10, name, price);

            progress.show();
//        Log.d("SizeOfMultipart", String.valueOf(bill_image.length));
            callPostExpenses.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    if (response.isSuccessful()) {
                        progress.dismiss();
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        Toast.makeText(getContext(), "submitted", Toast.LENGTH_SHORT).show();
//                    Log.d("SizeOfMultipartPass", String.valueOf(bill_image.length));
                        Log.d("imagePathImagePath", String.valueOf(imagePath));
                        getActivity().finish();
                    } else {
                        Log.d("dfsdfsdf", response.message()+response.code());

                        Toast.makeText(getContext(), "some error occured", Toast.LENGTH_SHORT).show();
                        progress.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.d("dfsdf", t.getMessage());
                    progress.dismiss();
                    Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getContext(), "please upload atleast 1 image", Toast.LENGTH_SHORT).show();
        }
//

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String imgString;

        if (resultCode == RESULT_OK && requestCode == GALLERY) {
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

        if (requestCode == CAMERA && resultCode == RESULT_OK) {


            if (data.getExtras().get("data") == null) {

                Toast.makeText(getContext(), "this device is unable to perform this functionality", Toast.LENGTH_SHORT).show();
            } else {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
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