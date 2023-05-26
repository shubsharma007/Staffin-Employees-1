package com.example.staffinemployees;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.staffinemployees.databinding.ActivityPayslipBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Payslip extends AppCompatActivity {
    ActivityPayslipBinding binding;
    public static final int STORAGE=45;
    String dp, name, employeeId, status, monthYear, basic, expenses, overtime, bonus, deductions, net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPayslipBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dp = getIntent().getExtras().getString("dp");
        name = getIntent().getExtras().getString("name");
        employeeId = getIntent().getExtras().getString("employeeId");
        status = getIntent().getExtras().getString("status");
        monthYear = getIntent().getExtras().getString("monthYear");
        basic = getIntent().getExtras().getString("basic");
        expenses = getIntent().getExtras().getString("expenses");
        overtime = getIntent().getExtras().getString("overtime");
        bonus = getIntent().getExtras().getString("bonus");
        deductions = getIntent().getExtras().getString("deductions");
        net = getIntent().getExtras().getString("net");

        Glide.with(Payslip.this).load(dp).into(binding.dp);
        binding.nameTv.setText(name);
        binding.empId.setText(employeeId);
        binding.indicator.setText(status);
        binding.txt2.setText(monthYear);
        binding.basicAmount.setText(basic+ " Rm");
        binding.hourlyAmount.setText(overtime+ " Rm");
        binding.expenseAmount.setText(expenses+ " Rm");
        binding.bounceAmount.setText(bonus+ " Rm");
        binding.deductionAmount.setText(deductions+ " Rm");
        binding.netAmount.setText(net+ " Rm");

        binding.downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        requestRuntimePermissionFunc("manageStorage");
                    } else {
                        requestRuntimePermissionFunc("storage");
                    }
            }
        });

    }

    private void requestRuntimePermissionFunc(String str) {
        if (str.equals("storage")) {
            if (ContextCompat.checkSelfPermission(Payslip.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                generatePdf();
//                Toast.makeText(this, "storage permission already granted", Toast.LENGTH_SHORT).show();
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(Payslip.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Payslip.this);
                builder.setMessage("this permission is required for this and this")
                        .setTitle("storage required")
                        .setCancelable(false)
                        .setPositiveButton("accept", (dialog, which) -> ActivityCompat.requestPermissions(Payslip.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE))
                        .setNegativeButton("reject", (dialog, which) -> dialog.dismiss())
                        .show();
            } else {
                ActivityCompat.requestPermissions(Payslip.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE);
            }
        } else if (str.equals("manageStorage")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    // Permission is granted
                    generatePdf();
//                    Toast.makeText(this, "manage storage permission already granted", Toast.LENGTH_SHORT).show();
                    Log.d("dgdgsdfgsdfgs", "yes yes yes yes ");
                } else {
                    // Permission is not granted, request it
                    Log.d("dgdgsdfgsdfgs", "no no no no ");

                    Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
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
        View rootView = getWindow().getDecorView().getRootView();
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

// Finish the page
        document.finishPage(page);

// Save the document to a file
        File file = new File(Environment.getExternalStorageDirectory() + "/PaySlip.pdf");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            document.writeTo(fos);
            fos.close();
            Toast.makeText(this, "payslip saved to " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving payslip", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, "accepted", Toast.LENGTH_SHORT).show();
                generatePdf();
            } else if (!ActivityCompat.shouldShowRequestPermissionRationale(Payslip.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Payslip.this);
                builder.setMessage("this feature is unavailable , now open settings ")
                        .setTitle("storage to chaiye")
                        .setCancelable(false)
                        .setPositiveButton("accept", (dialog, which) -> {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                            dialog.dismiss();
                        })
                        .setNegativeButton("reject", (dialog, which) -> dialog.dismiss())
                        .show();
            } else {
                requestRuntimePermissionFunc("storage");
            }
        }
    }

}