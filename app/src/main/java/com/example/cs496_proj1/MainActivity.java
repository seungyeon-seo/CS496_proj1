package com.example.cs496_proj1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;
import android.widget.ImageView;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity
    implements ActivityCompat.OnRequestPermissionsResultCallback

    {
        private static final int PERMISSIONS_REQUEST_CODE = 100;
        String[] REQUIRED_PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_CONTACTS};
        public int[] grandResults = {-1 , -1};

        private ViewPager2 viewPager;
        TabPagerAdapter fgAdapter;

        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TabLayout Initialization
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        // ViewPager Initialization
        viewPager = (ViewPager2) findViewById(R.id.pager);
        TabPagerAdapter fgAdapter = new TabPagerAdapter(this, 3);
        viewPager.setAdapter(fgAdapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                default:
                    tab.setText("Contacts");
                    break;
                case 1:
                    tab.setText("Gallery");
                    break;
                case 2:
                    tab.setText("Temp");
                    break;
            }
            viewPager.setCurrentItem(0);
        }).attach();

        // TabSelectedListener Initialization
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        GetPermission();
        //System.out.println(grandResults);
        onRequestPermissionsResult(PERMISSIONS_REQUEST_CODE, REQUIRED_PERMISSIONS, grandResults);
    }

        public void GetPermission () {
        LinearLayout mLayout = findViewById(R.id.main_layout);
        int readExternalStoragePermission = ContextCompat.checkSelfPermission(this, REQUIRED_PERMISSIONS[0]);
        int readContactPermission = ContextCompat.checkSelfPermission(this, REQUIRED_PERMISSIONS[1]);
        grandResults[0] = readExternalStoragePermission;
        grandResults[1] = readContactPermission;

        if (!(grandResults[0] == PackageManager.PERMISSION_GRANTED
                && grandResults[1] == PackageManager.PERMISSION_GRANTED)) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                    || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {
                Snackbar.make(mLayout, "이 앱을 실행하려면 외부 저장소 및 연락처 접근 권한이 필요합니다.",
                        Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                        ActivityCompat.requestPermissions(MainActivity.this, REQUIRED_PERMISSIONS,
                                PERMISSIONS_REQUEST_CODE);
                    }
                }).show();
            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }
        }
    }

        @Override
        public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
        @NonNull int[] grandResults){
        LinearLayout mLayout = findViewById(R.id.main_layout);
        if (requestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면
            boolean check_result = true;

            // 모든 퍼미션을 허용했는지 체크합니다.
            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }

            if (!check_result) {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {

                    // 사용자가 거부만 선택한 경우에는 앱을 다시 실행하여 허용을 선택하면 앱을 사용할 수 있습니다.
                    Snackbar.make(mLayout, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요. ",
                            Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    }).show();
                } else {
                    // “다시 묻지 않음”을 사용자가 체크하고 거부를 선택한 경우에는 설정(앱 정보)에서 퍼미션을 허용해야 앱을 사용할 수 있습니다.
                    Snackbar.make(mLayout, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ",
                            Snackbar.LENGTH_INDEFINITE).setAction("확인", new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    }).show();
                }
            }
        }
    }
    }


