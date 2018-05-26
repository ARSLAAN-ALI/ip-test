package com.example.arsalan.ip;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;



public class Main2Activity extends AppCompatActivity {
    Button button;
    TextView textview;
    String IPaddress;
    Boolean IPValue;
    String OPENLAUNCHPackageName = "com.example.arsalan.launch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final Button file = (Button) findViewById(R.id.button9);
        file.setOnClickListener(new View.OnClickListener() {
            public Context getContext() {
                return context;
            }

            public Context context;
            public android.R.attr os;

            @Override
            public void onClick(View v) {
               // File file = new File("/IP test/P.pdf");
               //if(file !=null && file.exists()){


               File applictionFile = new File(Environment.getExternalStorageDirectory() + "/IP test/h.pdf");

               if (applictionFile != null && applictionFile.exists()) {
                    Toast.makeText(Main2Activity.this, "file exist ", Toast.LENGTH_SHORT).show();

                } else {

                  // String serviceString = Context.DOWNLOAD_SERVICE;
                   //DownloadManager downloadManager;
                   //downloadManager = (DownloadManager)getSystemService(serviceString);

                   //ri uri = Uri.parse("http://www.pdf995.com/samples/pdf.pdf");
                   //DownloadManager.Request request = new DownloadManager.Request(uri);
                   //long reference = downloadManager.enqueue(request);
                   //request.setDestinationUri(Uri.fromFile(IP test));


                   // startDownload ();
                   String fileId = "1ZdR3L3qP4Bkq8noWLJHSr_iBau0DNT4Kli4SxNc2YEo";
                   OutputStream outputStream = new ByteArrayOutputStream();
                   Object driveService=null;
                   assert driveService != null;
                   driveService.files().export(fileId, "application/pdf")
                           .executeMediaAndDownloadTo(outputStream);




                   // Intent intent = new Intent(android.content.Intent.ACTION_VIEW);


                // intent.setData(Uri.parse("http://www.pdf995.com/samples/pdf.pdf"));
                // request.setDestinationUri(Uri.fromFile());

                     // request.setDestinationInExternalFilesDir(this,
                          // Environment.DIRECTORY_DOWNLOADS, "pdf.pdf");

                  //startActivity(intent);;
                 // File file=new File().getFilesDir(/data/data/package/files);
                // File file=new File (Environment.getExternalStorageDirectory()+"Android/data/com.example.arsalan.ip/files");

                  // startActivity(intent);
                    Toast.makeText(Main2Activity.this, "file is not exist", Toast.LENGTH_SHORT).show();

                }

            }

           // private void startDownload() {
              //  DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
              //  DownloadManager.Request mRqRequest = new DownloadManager.Request(Uri.parse("https://drive.google.com/open?id=1YimQgn3t8IdOgj3BNgLucXMM7q4jBnSB"));
             //   mRqRequest.setDescription("This is Test File");
//  mRqRequest.setDestinationUri(Uri.parse("give your local path"));
          //  }

        });

        Button onclick = (Button) findViewById(R.id.button3);
        textview = (TextView) findViewById(R.id.textView2);
        Button click = (Button) findViewById(R.id.button4);
        click.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, 0);


            }
        });



        Button clickK = (Button) findViewById(R.id.button6);
        clickK.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {


                Intent intent = getPackageManager().getLaunchIntentForPackage("com.example.arsalan.launch");
                startActivity(intent);


    }
        });


        Button clickkk = (Button) findViewById(R.id.button5);
        clickkk.setOnClickListener(new View.OnClickListener()

        {


            @Override
            public void onClick(View v) {

                boolean isAppInstalled = appInstalledOrNot("com.devuni.flashlight");

                if(isAppInstalled) {

                    Intent LaunchIntent = getPackageManager()
                            .getLaunchIntentForPackage("com.devuni.flashlight");
                    startActivity(LaunchIntent);



                } else {

                    Intent  intent = new Intent(android.content.Intent.ACTION_VIEW);


                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.devuni.flashlight"));

                    startActivity(intent);


                }
            }

            private boolean appInstalledOrNot(String uri) {
                PackageManager pm = getPackageManager();
                try {
                    pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
                    return true;
                } catch (PackageManager.NameNotFoundException e) {
                }

                return false;
            }





        });

        onclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub


                getTunIPAddress(true);
                String var =getTunIPAddress(true);


                Toast.makeText(Main2Activity.this, var, Toast.LENGTH_SHORT).show();


            }


           // }


            // TODO Auto-generated method stub


            public String getTunIPAddress(boolean useIPv4) {
                try {


                    // List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
                    NetworkInterface intf = NetworkInterface.getByName("wlan0");
                    // for (NetworkInterface intf : interfaces) {
                    List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                    for (InetAddress addr : addrs) {
                        if (!addr.isLoopbackAddress()) {
                            String sAddr = addr.getHostAddress();
                            //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                            boolean isIPv4 = sAddr.indexOf(':')<0;

                            if (useIPv4) {
                                if (isIPv4)
                                    return sAddr;
                            } else {
                                if (!isIPv4) {
                                    int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                    return delim<0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                                }
                            }
                        }
                    }
                    // }
                } catch (Exception ex) { } // for now eat exceptions
                return "connect to wifi";
            }

        });}}