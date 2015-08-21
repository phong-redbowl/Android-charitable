package demoapp.akiraaman.demo_sqlite;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.method.MovementMethod;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    static final String tkadmin = "phamducquan";
    static final String mkadmin = "123456";
    DatabaseHelper helper = new DatabaseHelper(this);
    EditText edtUserName, edtPassWord;
    Button btnLogin;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUserName = (EditText)findViewById(R.id.editTextUserName);
        edtPassWord = (EditText)findViewById(R.id.editTextPassWord);
        btnLogin = (Button)findViewById(R.id.buttonLogin);
        btnSignUp = (Button)findViewById(R.id.buttonSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tk = edtUserName.getText().toString();
                if(helper.searchTK(tk) == true) {
                    if (edtPassWord.getText().toString().equals(helper.searchPass(tk))) {
                        Toast.makeText(getApplicationContext(), R.string.loginsuccess, Toast.LENGTH_LONG).show();
                        Intent mh2 = new Intent(MainActivity.this, QuanLy_Activity.class);
                        mh2.putExtra("id", edtUserName.getText().toString());
                        mh2.putExtra("pass", edtPassWord.getText().toString());
                        startActivity(mh2);
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.loginerror, Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    if(edtUserName.getText().toString().equals(tkadmin) && edtPassWord.getText().toString().equals(mkadmin)){
                        Intent mhad = new Intent(MainActivity.this, Admin_Activity.class);
                        startActivity(mhad);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), R.string.notfoundTK, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhDangKy = new Intent(MainActivity.this, DangKy_Activity.class);
                startActivity(mhDangKy);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
