package com.hienqp.tourbook.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hienqp.tourbook.R;
import com.hienqp.tourbook.fragments.AboutHelpFragment;

public class ContactActivity extends ListActivity {
    public static final String KEY_SHOW_WHAT = "show_what";
    public static final String VALUE_SHOW_ABOUT = "show_about";
    public static final String VALUE_SHOW_HELP = "show_help";

    /** DataSource cho Adapter sử dụng*/
    private static final String[] items = {"lorem", "ipsum", "dolor",
            "sit", "amet", "consectetuer",
            "adipiscing", "elit", "morbi",
            "vel", "ligua", "vitae",
            "arcu", "aliquet", "mollis",
            "eiam", "vel", "erat",
            "placerat", "ante", "porttitor",
            "sodales", "pellentesque", "augue",
            "purus"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /** nếu sử dụng ListActivity kết hợp thêm 1 View khác bên cạnh ListView của ListActivity
         * ta cần phải set UI cho ContactActivity*/
        setContentView(R.layout.activity_contact);

        /** nếu sử dụng ListActivity cho ContactActivity thì không cần setContentView
         * vì ListActivity có sẵn content là ListView cho chúng ta*/
//        setContentView(R.layout.activity_contact);

        /** ListActivity không hỗ trợ các tùy chọn hiển thị Button trên ActionBar
         * ListActivity sẽ hiển thị 1 ListView full màn hình
         * hiển thị tùy chọn các Button trên ActionBar*/
//        // hiển thị nút Home trên ActionBar
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        // cho phép tương tác nút Home trên ActionBar
//        getSupportActionBar().setHomeButtonEnabled(true);

        /** thiết lập Intent truyền Bundle*/
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//
//        if (bundle != null) {
//            String valueShow = bundle.getString(ContactActivity.KEY_SHOW_WHAT, "");
//            Toast.makeText(ContactActivity.this, "Show value: " + valueShow, Toast.LENGTH_SHORT).show();

        /** thiết lập hiển thị WebViewFragment*/
//            FragmentManager fragmentManager = getFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//            if (valueShow.equals(VALUE_SHOW_ABOUT)) {
//                AboutHelpFragment aboutHelpFragment = AboutHelpFragment.newInstance("https://yellowcodebooks.com/about/");
//                fragmentTransaction.add(R.id.contactMainFrame, aboutHelpFragment);
//                fragmentTransaction.commit();
//            } else if (valueShow.equals(VALUE_SHOW_HELP)) {
//                AboutHelpFragment aboutHelpFragment = AboutHelpFragment.newInstance("https://yellowcodebooks.com/contact/");
//                fragmentTransaction.add(R.id.contactMainFrame, aboutHelpFragment);
//                fragmentTransaction.commit();
//            }
//        }

        /** xây dụng Adapter (ArrayAdapter) và AdapterView (ListView)*/
//         //khai báo 1 đối tượng ListView liên kết đến UI XML ListView
//        ListView myListView = findViewById(R.id.my_listview);
//        // khai báo đối tượng ArrayAdapter làm trung gian giữa DataSource là AdapterView
//        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<>(ContactActivity.this, android.R.layout.simple_list_item_1, items);
//        // gắn Adapter vào AdapterView
//        myListView.setAdapter(myArrayAdapter);
//        /** xây dựng sự kiện click cho ListView do ta tự xây dựng*/
//        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(ContactActivity.this, items[position] + ": clicked", Toast.LENGTH_SHORT).show();
//            }
//        });

        /** nếu sử dụng ListActivity, tức là có sẵn AdapterView chính là ListView
         * chỉ cần khai báo 1 Adapter, sau đó gắn Adapter vào ListView có sẵn mà không cần khai báo 1 ListView*/
        // khai báo Adapter (ArrayAdapter)
        ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<>(ContactActivity.this, android.R.layout.simple_list_item_1, items);
        // gắn ArrayAdapter vào ContactActivity (lúc này là ListView của ListActivity)
        setListAdapter(myArrayAdapter);
        /** xây dựng sự kiện click cho ListView của ListActivity*/
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ContactActivity.this, items[position] +": click", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}