package com.example.team_sallab.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.team_sallab.Network.BackgroundTask;
import com.example.team_sallab.Network.BackgroundTaskInterface;
import com.example.team_sallab.Network.NetworkStatusAndType;
import com.example.team_sallab.R;
import com.example.team_sallab.pref.prefConfig;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private final static String qr_Script = "https://www.deltamedonline.com/testApi/qrMatch.php";
    private com.example.team_sallab.pref.prefConfig prefConfig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final Activity activity=this;

        ImageView img=findViewById(R.id.imageView);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator=new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES)
                        .setPrompt("Scan")
                        .setCameraId(0)
                        .setBeepEnabled(true)
                        .setBarcodeImageEnabled(false)
                        .initiateScan();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!=null){
            if (result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(HomeActivity.this,FailedRegistration.class));

            }else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                String contents = result.getContents();
                doRegister(contents);


            }

        }else {
            super.onActivityResult(requestCode, resultCode, data);

        }




    }

    private void doRegister(String contents) {
        if (contents!=null){
            switch (new NetworkStatusAndType(HomeActivity.this).NetworkStatus())
            {
                case 0:
                    //TODO disconnected
//                    Toast.makeText(this, "You Are Discoonected the Internet", Toast.LENGTH_SHORT).show();

                    break;
                case 1:
                    //TODO connecting
                    break;
                case 2:

                    Map<String,Object> values = new LinkedHashMap<>();
                    String name = prefConfig.readName();
                    values.put("name",name);
                    values.put("QR" , contents);

                    new BackgroundTask(new BackgroundTaskInterface() {
                        @Override
                        public void processFinished(String response) {
                            if(response != null)
                            {
                                try {
                                    Log.e("response" , response);
                                    JSONObject mainObject = new JSONObject(response.substring(response.indexOf("{"),
                                            response.lastIndexOf("}")+1));

                                    int success = mainObject.getInt("success");
                                    switch (success)
                                    {
                                        case 0:
                                            //TODO
                                            // Failed Registration
                                            prefConfig.displayToast("Failed Registration");
                                            startActivity(new Intent(HomeActivity.this,FailedRegistration.class));
                                            finish();
                                            break;
                                        case 1:
                                            // ٌ Successful Registration ٍ

                                            prefConfig.displayToast("Registration Success");
                                            startActivity(new Intent(HomeActivity.this,SuccessfulRegistration.class));
                                            finish();
                                            break;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    },values).execute(qr_Script);
                    break;
            }
        }
        }

    }

