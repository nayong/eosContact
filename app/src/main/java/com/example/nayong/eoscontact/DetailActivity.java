package com.example.nayong.eoscontact;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    int clickedStar = 0;

    ImageButton btnDetailStar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btnDetailStar = (ImageButton) findViewById(R.id.btnDetailStar);

        //set Text txtName, PhoneNum
    }

    public void onClick(View v) {

        switch (v.getId()) {

            case (R.id.btnDetailBack): {

                //Intent intentBack = new Intent(this, );
                //startActivity(intentBack);
                break;
            }

            case (R.id.btnDetailEdit): {


                break;
            }

            case (R.id.btnDetailPhoto): {

                break;
            }

            case (R.id.btnDetailAlbum): {

                break;
            }

            case (R.id.btnDetailStar): {

                if (clickedStar == 0) {
                    clickedStar++;
                    btnDetailStar.setImageResource(R.drawable.contact_detail_star_selected);
                    Toast.makeText(getApplicationContext(), "즐겨찾기 설정되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    clickedStar--;
                    btnDetailStar.setImageResource(R.drawable.contact_detail_star_unselected);
                    Toast.makeText(getApplicationContext(), "즐겨찾기 해제되었습니다.", Toast.LENGTH_SHORT).show();
                }

                break;

            }

            case (R.id.btnDetailCall1): {

                PermissionRequest();

                break;
            }

            case (R.id.btnDetailMsg1): {

                Uri uri = Uri.parse("smsto:01022865413");
                Intent intentSms = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(intentSms);

                break;
            }

            case (R.id.btnDetailMail): {

                Uri uri1 = Uri.parse("mailto:na_yong517@naver.com");
                Intent intentMail = new Intent(Intent.ACTION_SENDTO, uri1);
                startActivity(intentMail.createChooser(intentMail, "Send feedback"));

                break;

            }

            case (R.id.btnDetailCall2): {

                PermissionRequest();

                break;
            }

            case (R.id.btnDetailMsg2): {

                Uri uri = Uri.parse("smsto:01022865413");
                Intent intentSms = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(intentSms);

                break;
            }

            case (R.id.btnDetailGroup): {

                break;
            }

            case (R.id.txtDetailGroup): {

                break;
            }

            case (R.id.btnDetailShare): {

                break;
            }

            case (R.id.btnDetailDelete): {

                break;
            }

        }


    }


    public void PermissionRequest(){

        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("010-2286-5413"));

                /*
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    onRequestPermissionsResult();
                    return;
                }
                */

        int result = new PermissionRequester.Builder(DetailActivity.this)
                .setTitle("권한 요청")
                .setMessage("권한을 요청합니다.")
                .setPositiveButtonName("네")
                .setNegativeButtonName("아니요.")
                .create()
                .request(Manifest.permission.CALL_PHONE, 1000, new PermissionRequester.OnClickDenyButtonListener() {
                    @Override
                    public void onClick(Activity activity) {
                        Log.d("xxx", "취소함.");
                    }
                });
        if (result == PermissionRequester.ALREADY_GRANTED) {
            Log.d("RESULT", "권한이 이미 존재함.");
            if (ActivityCompat.checkSelfPermission(DetailActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-2286-5413"));
                startActivity(intent);
            }
        } else if (result == PermissionRequester.NOT_SUPPORT_VERSION) {
            Log.d("RESULT", "마쉬멜로우 이상 버젼 아님.");
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-2286-5413"));
            startActivity(intent);
        } else if (result == PermissionRequester.REQUEST_PERMISSION) {
            Log.d("RESULT", "요청함. 응답을 기다림.");
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-2286-5413"));
            startActivity(intent);
        }

        startActivity(intentCall);

    }


    /**
     * 권한 요청에 대한 응답을 이곳에서 가져온다. *
     * * @param requestCode 요청코드
     * * @param permissions 사용자가 요청한 권한들
     * * @param grantResults 권한에 대한 응답들(인덱스별로 매칭)
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1000) {
            // 요청한 권한을 사용자가 "허용" 했다면...
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-2286-5413"));
                // Add Check Permission
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                }
            } else {
                Toast.makeText(DetailActivity.this, "권한요청을 거부했습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}