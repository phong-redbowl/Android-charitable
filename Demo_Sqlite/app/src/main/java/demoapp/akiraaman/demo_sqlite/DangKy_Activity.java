package demoapp.akiraaman.demo_sqlite;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class DangKy_Activity extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);
    EditText edtTaiKhoan;
    EditText edtMatKhau;
    EditText edtMatKhau2;
    EditText edtTen;
    EditText edtDiaChi;
    EditText edtSDT;
    Button btnDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_);

                                // Ánh x?
        edtTaiKhoan = (EditText)findViewById(R.id.editTextTK);
        edtMatKhau = (EditText)findViewById(R.id.editTextMK);
        edtMatKhau2 = (EditText)findViewById(R.id.editTextXNMK);
        edtTen = (EditText)findViewById(R.id.editTextTenChuTK);
        edtDiaChi = (EditText)findViewById(R.id.editTextDiaChi);
        edtSDT = (EditText)findViewById(R.id.editTextSDT);
        btnDangKy = (Button)findViewById(R.id.buttonDangKy);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtMatKhau.getText().toString().equals(edtMatKhau2.getText().toString())) {
                    TaiKhoan kh = new TaiKhoan();
                    kh.setM_TK(edtTaiKhoan.getText().toString());
                    kh.setM_Matkhau(edtMatKhau.getText().toString());
                    kh.setM_Ten(edtTen.getText().toString());
                    kh.setM_Diachi(edtDiaChi.getText().toString());
                    kh.setM_Sdt(edtSDT.getText().toString());
                    helper.insertTK(kh);
                    Toast.makeText(getApplicationContext(),R.string.signsuccess,Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.notmatch, Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dang_ky_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
