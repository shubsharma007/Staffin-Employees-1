package com.example.staffinemployees.Fragment;

import static android.Manifest.permission_group.STORAGE;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.staffinemployees.Interface.ApiInterface;
import com.example.staffinemployees.R;
import com.example.staffinemployees.Response.PaySlipResponse;
import com.example.staffinemployees.Response.PayslipDetail;
import com.example.staffinemployees.Retrofit.RetrofitServices;
import com.example.staffinemployees.databinding.FragmentPayrollBinding;
import com.example.staffinemployees.databinding.FragmentPayslipBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Payslip extends Fragment {
    FragmentPayslipBinding binding;
    ApiInterface apiInterface;
    String Id;
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static final int STORAGE = 125;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPayslipBinding.inflate(inflater, container, false);
        sharedPreferences = getActivity().getSharedPreferences("staffin", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Id = sharedPreferences.getAll().get("Id").toString();
        Log.i("Id AArahi AHI", Id);
//        binding.notFoundLayout.setVisibility(View.VISIBLE);
//        binding.nestedScrollFirst.setVisibility(View.GONE);
        apiInterface = RetrofitServices.getRetrofit().create(ApiInterface.class);
//        getApi();
        clickListener();
        return binding.getRoot();

    }

    private void clickListener() {
        binding.downloadBtn.setOnClickListener(v -> {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                requestRuntimePermissionFunc("manageStorage");
            } else {
                requestRuntimePermissionFunc("storage");
            }

        });

    }

    private void requestRuntimePermissionFunc(String str) {
        if (str.equals("storage")) {
            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                generatePdf();
                Toast.makeText(getActivity(), "storage permission already granted", Toast.LENGTH_SHORT).show();
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("this permission is required for this and this")
                        .setTitle("storage required")
                        .setCancelable(false)
                        .setPositiveButton("accept", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE);
                            }
                        })
                        .setNegativeButton("reject", (dialog, which) -> dialog.dismiss())
                        .show();
            } else {

                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE);
            }
        } else if (str.equals("manageStorage")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    // Permission is granted
                    generatePdf();
                    Toast.makeText(getActivity(), "manage storage permission already granted", Toast.LENGTH_SHORT).show();
                    Log.d("dgdgsdfgsdfgs", "yes yes yes yes ");
                } else {
                    // Permission is not granted, request it
                    Log.d("dgdgsdfgsdfgs", "no no no no ");

                    Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                    Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                }
            }

        }
    }

    private void generatePdf() {

//            // Create a PDDocument instance
//            PDDocument document = new PDDocument();
//
//// Create a PDPage instance
//            PDPage page = new PDPage();
//
//// Add the page to the document
//            document.addPage(page);
//
//// Create a PDPageContentStream instance for the page
//            PDPageContentStream contentStream = null;
//            try {
//                contentStream = new PDPageContentStream(document, page);
//
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//// Create a PDImageXObject instance from the screenshot Bitmap
//            PDImageXObject imageXObject = LosslessFactory.createFromImage(document, bitmap);
//
//            try {
//                // Get the width and height of the image
//                float imageWidth = imageXObject.getWidth();
//                float imageHeight = imageXObject.getHeight();
//
//                // Set the position and size of the image on the page
//                float x = 0;
//                float y = page.getMediaBox().getHeight() - imageHeight;
//                float width = page.getMediaBox().getWidth();
//                float height = imageHeight;
//
//                // Draw the image on the page
//                contentStream.drawImage(imageXObject, x, y, width, height);
//
//                // Close the content stream
//                contentStream.close();
//
//                // Save the document to a file
//                File file = new File(Environment.getExternalStorageDirectory() + "/screenshot.pdf");
//                document.save(file);
//
//                Toast.makeText(this, "Screenshot saved to " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//            } catch (IOException e) {
//                e.printStackTrace();
//                Toast.makeText(this, "Error saving screenshot", Toast.LENGTH_SHORT).show();
//            } finally {
//                // Close the document
//                document.close();
//
//                // Clear the Bitmap
//                bitmap.recycle();
//            }


//            try {
//                PdfDocument pdfDocument = new PdfDocument(new PdfWriter("output.pdf"));
//                Document document = new Document(pdfDocument);
//
//                // Create a table with 3 columns
//                Table table = new Table(3);
//
//                // Add table headers
//                table.addHeaderCell("Header 1");
//                table.addHeaderCell("Header 2");
//                table.addHeaderCell("Header 3");
//
//                // Add table rows
//                table.addCell("Row 1, Cell 1");
//                table.addCell("Row 1, Cell 2");
//                table.addCell("Row 1, Cell 3");
//
//                table.addCell("Row 2, Cell 1");
//                table.addCell("Row 2, Cell 2");
//                table.addCell("Row 2, Cell 3");
//
//                // Add the table to the document
//                document.add(table);
//
//                // Close the document
//                document.close();
//
//                // Download the PDF file
//                File pdfFile = new File("output.pdf");
//                // Modify the file path according to your requirements
//
//                // Trigger the file download
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                Uri uri = FileProvider.getUriForFile(this, getPackageName() + ".provider", pdfFile);
//                intent.setDataAndType(uri, "application/pdf");
//                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                startActivity(intent);
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            }

//            initDownload();
        View rootView = requireActivity().getWindow().getDecorView().getRootView();
        rootView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);
        // Save the Bitmap to a file
//            File file = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
//            try {
//                FileOutputStream fos = new FileOutputStream(file);
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
//                fos.flush();
//                fos.close();
//                Toast.makeText(this, "Attendance Saved To " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//            } catch (IOException e) {
//                e.printStackTrace();
//                Toast.makeText(this, "Error Saving Attendance", Toast.LENGTH_SHORT).show();
//            }


        // Create a new document
        PdfDocument document = new PdfDocument();

// Create a blank page with the desired dimensions
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

// Get the canvas for drawing on the page
        Canvas canvas = page.getCanvas();

// Draw the screenshot bitmap on the canvas
        canvas.drawBitmap(bitmap, 0, 0, null);

// Finish the page3
        document.finishPage(page);

// Save the document to a file
        File file = new File(Environment.getExternalStorageDirectory() + "/Attendance.pdf");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            document.writeTo(fos);
            fos.close();
            Toast.makeText(getActivity(), "attendance saved to " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error saving attendance", Toast.LENGTH_SHORT).show();
        }
// Close the document
        document.close();

// Clear the Bitmap
        bitmap.recycle();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE) {// storage
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "accepted", Toast.LENGTH_SHORT).show();
                generatePdf();
            } else if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("this feature is unavailable , now open settings ")
                        .setTitle("storage to chaiye")
                        .setCancelable(false)
                        .setPositiveButton("accept", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                                intent.setData(uri);
                                startActivity(intent);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("reject", (dialog, which) -> dialog.dismiss())
                        .show();
            } else {
                requestRuntimePermissionFunc("storage");
            }
        }
    }

    private void getApi() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Call<PaySlipResponse> paySlipResponseCall = apiInterface.getPaySlip(Integer.parseInt(Id));
        paySlipResponseCall.enqueue(new Callback<PaySlipResponse>() {
            @Override
            public void onResponse(Call<PaySlipResponse> call, Response<PaySlipResponse> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.body().getPayslipDetails().size() == 0) {

                        binding.notFoundLayout.setVisibility(View.VISIBLE);
                        binding.nestedScrollFirst.setVisibility(View.GONE);

                    } else {
                        binding.nestedScrollFirst.setVisibility(View.VISIBLE);
                        binding.notFoundLayout.setVisibility(View.GONE);
                        PayslipDetail singleUnit = response.body().getPayslipDetails().get(0);
                        Glide.with(getActivity()).load(singleUnit.getEmployeeId().get(0).getProfileImageUrl()).placeholder(R.drawable.img_dp).into(binding.dpImg);
                        binding.nameTv.setText(singleUnit.getEmployeeId().get(0).getFullName());

                        binding.indicator.setText(singleUnit.getStatus());
                        binding.basicAmount.setText(singleUnit.getBasic());
                        binding.hourlyAmount.setText(singleUnit.getOvertimeHours());
                        binding.expenseAmount.setText(singleUnit.getExpense());
                        binding.bounceAmount.setText(singleUnit.getTotalAllowance());
                        binding.deductionAmount.setText(singleUnit.getTotalDeduction());
                        binding.netAmount.setText(singleUnit.getNetSalary());
                        binding.empId.setText("Emp. ID - " + singleUnit.getEmployeeId().get(0).getEmployeeID());
                        binding.txt1.setText("Month:-" + singleUnit.getMonth());
                        binding.txt2.setText("Year:-" + singleUnit.getYear());
                        Log.e("data DEkho to", response.message());
                    }
                } else {
                    binding.notFoundLayout.setVisibility(View.VISIBLE);
                    binding.nestedScrollFirst.setVisibility(View.GONE);
                    progressDialog.dismiss();
                    Log.d("onresponseelse", response.message());
                    Toast.makeText(getActivity(), "Try Again", Toast.LENGTH_SHORT).show();
                    Log.e("Try Again Karo", response.message());
                }
            }

            @Override
            public void onFailure(Call<PaySlipResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
                Log.e("Api not Working", t.getMessage());
            }
        });

    }
}