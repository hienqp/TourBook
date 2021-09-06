package com.hienqp.tourbook.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hienqp.tourbook.fragments.FirstFragment;
import com.hienqp.tourbook.R;
import com.hienqp.tourbook.fragments.SecondFragment;

public class MainActivity extends AppCompatActivity implements FirstFragment.OnFirstFragmentInteractionListener {

    // Khai Báo DrawerLayout
    private DrawerLayout drawerLayout;
    // Khai Báo ActionBarDrawerToggle
    private ActionBarDrawerToggle actionBarDrawerToggle;
    // khai báo Intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh Xạ đến ID của DrawerLayout trong XML
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer);

        // Khởi tạo ActionBarDrawerToggle
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                MainActivity.this,
                drawerLayout ,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        // gắn ActionBarDrawerToggle vào DrawerLayout
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        // hiển thị nút Home trên ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // cho phép tương tác nút Home trên ActionBar
        getSupportActionBar().setHomeButtonEnabled(true);


        /** hiển thị FirstFragment theo kiểu mặc định portrait*/
//        androidx.fragment.app.FragmentManager fragmentManager = getSupportFragmentManager();
//        androidx.fragment.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        FirstFragment firstFragment = new FirstFragment();
//        fragmentTransaction.add(R.id.firstFrame, firstFragment);
//        fragmentTransaction.commit();


        /** hiển thị Fragment tùy theo portrait hoặc landscape*/
        // tạo đối tượng FirstFragment
        FirstFragment firstFragment = new FirstFragment();
        // nếu portrait_Frame != null tức là đang ở portrait mode
        if (findViewById(R.id.portrait_Frame) != null) {
            if (savedInstanceState != null) {
                getSupportFragmentManager().executePendingTransactions();
                Fragment fragmentById = getSupportFragmentManager().findFragmentById(R.id.portrait_Frame);
                if (fragmentById != null) {
                    getSupportFragmentManager().beginTransaction().remove(fragmentById).commit();
                }
            }
            // add new FirstFragment
            getSupportFragmentManager().beginTransaction().add(R.id.portrait_Frame, firstFragment).commit();
        } else {
            // đang ở landscape mode
            // remove Fragment đang tồn tại trước khi add mới
            if (savedInstanceState != null) {
                getSupportFragmentManager().executePendingTransactions();
                Fragment firstFragmentById = getSupportFragmentManager().findFragmentById(R.id.landscape_firstFrame);
                if (firstFragmentById != null) {
                    getSupportFragmentManager().beginTransaction().remove(firstFragmentById).commit();
                }
                Fragment secondFragmentById = getSupportFragmentManager().findFragmentById(R.id.landscape_secondFrame);
                if (secondFragmentById != null) {
                    getSupportFragmentManager().beginTransaction().remove(secondFragmentById).commit();
                }
            }
            // add new FirstFragment
            getSupportFragmentManager().beginTransaction().add(R.id.landscape_firstFrame, firstFragment).commit();
        }

        // VD sử dụng CDATA String
//        usesCDATAString();

        // VD sử dụng SpannableString class
//        usesSpannableString();

        // VD sử dụng Formatting String
//        usesFormattingString();

        // VD sử dụng plurals string
//        usesPluralsString();

        // VD sử dụng string array để lấy về mảng String
//        usesStringArrayResource();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.search:
                Toast.makeText(this, "Search button is selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.about:
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString(ContactActivity.KEY_SHOW_WHAT, ContactActivity.VALUE_SHOW_ABOUT);

                intent.putExtras(bundle);

                startActivity(intent);
                return true;
            case R.id.help:
                intent = new Intent(MainActivity.this, ContactActivity.class);

                bundle = new Bundle();
                bundle.putString(ContactActivity.KEY_SHOW_WHAT, ContactActivity.VALUE_SHOW_HELP);

                intent.putExtras(bundle);

                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemPressed(String content) {
        /** code xử lý SecondFragment ở mặc định portrait mode*/
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        SecondFragment secondFragment = SecondFragment.newInstance(content);
//        fragmentTransaction.replace(R.id.secondFrame, secondFragment);
//        fragmentTransaction.commit();

        /** code xử lý SecondFragment tùy theo mode portrait hay landscape*/
        // kiểm tra phải ở portrait mode hay không
        SecondFragment secondFragment = SecondFragment.newInstance(content);
        if (findViewById(R.id.portrait_Frame) != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.portrait_Frame, secondFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else {
            // landscape mode
            getSupportFragmentManager().beginTransaction().replace(R.id.landscape_secondFrame, secondFragment).commit();
        }
    }

//    private void usesStringArrayResource() {
//        String[] stringsArray = getResources().getStringArray(R.array.example_string_array);
//
//        StringBuilder stringBuilder = new StringBuilder();
//        for (String s : stringsArray) {
//            stringBuilder.append(s + "\n");
//        }
//
//        Toast.makeText(MainActivity.this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
//    }
//    private void usesPluralsString() {
//        // giả lập số lượng note
//        int numOfNotes = 1;
//
//        // setText cho TextView từ String lấy được từ plurals resource theo item chỉ định
//        textView.setText(getResources().getQuantityString(R.plurals.example_plurals, numOfNotes, numOfNotes));
//    }

//    private void usesFormattingString() {
//        // lấy thời gian lịch hệ thống
//        Calendar calendar = Calendar.getInstance();
//        // thiết lập định dạng cho Calendar chỉ hiển thị DD/MM/YYYY
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy");
//        // lấy chuỗi của Calendar theo định dạng của SimpleDateFormat
//        String currentDate = simpleDateFormat.format(calendar.getTime());
//
//        // Set text có các tham số truyền vào tương đương các định dạng %1$s, %2$d, %3$f trong XML
//        textView.setText(getString(R.string.example_Formatting_String,currentDate, 0, 3.5f));
//    }

//    private void usesCDATAString() {
//        textView.setText(Html.fromHtml(getString(R.string.example_CDATA_string)));
//    }

//    private void usesSpannableString() {
//
//        // lấy plain string từ R.string.[name_của_plain_string] trả về cho CharSequence
//        CharSequence charSequence = getText(R.string.example_SpannableString);
//        // truyền đối tượng của CharSequence vào SpannableString constructor
//        SpannableString spannableString = new SpannableString(charSequence);
//        // set màu đỏ cho string (1) vị trí từ 0-30
//        spannableString.setSpan(new ForegroundColorSpan(Color.RED), 0, 30, 0);
//        // set size của string (2) gấp 2 lần so với các string xung quanh, từ vị trí 84-85
//        spannableString.setSpan(new RelativeSizeSpan(2), 84, 85, 0);
//        // tạo khả năng click cho string đồng thời sẽ hiển thị thông báo kiểu Toast khi click vào
//        ClickableSpan clickableSpan = new ClickableSpan() {
//            @Override
//            public void onClick(@NonNull View widget) {
//                Toast.makeText(MainActivity.this, "Will be implement later...", Toast.LENGTH_SHORT).show();
//            }
//        };
//        // truyền khả năng click cho string (3) từ vị trí 128-150
//        spannableString.setSpan(clickableSpan, 128, 150, 0);
//        // set màu xanh cho string (3) từ vị trí 128-150
//        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), 128, 150, 0);
//        // set string (4) là 1 link liên kết (khi click vào sẽ dẫn đến đường dẫn của website chỉ định) từ vị trí 178-cuối string
//        spannableString.setSpan(new URLSpan("https://google.com"), 178, charSequence.length(), 0);
//        // set string (4) có màu đen
//        spannableString.setSpan(new ForegroundColorSpan(Color.BLACK), 178, charSequence.length(), 0);
//        // set string (4) có size gấp 2 lần string xung quanh
//        spannableString.setSpan(new RelativeSizeSpan(2), 178, charSequence.length(), 0);
//        // kích hoạt clickableSpann và URLSpan có thể hoạt động
//        textView.setMovementMethod(LinkMovementMethod.getInstance());
//
//        //  set spannableString vào textView
//        textView.setText(spannableString);
//    }


}
