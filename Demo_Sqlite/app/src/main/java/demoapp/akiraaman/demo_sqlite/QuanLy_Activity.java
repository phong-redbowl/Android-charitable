package demoapp.akiraaman.demo_sqlite;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class QuanLy_Activity extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);
    EditText edtMKCu;
    EditText edtMKMoi;
    EditText edtXNMKMoi;
    Button btnDoiMK;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_);

        edtMKCu = (EditText)findViewById(R.id.editTextMK1);
        edtMKMoi = (EditText)findViewById(R.id.editTextMK2);
        edtXNMKMoi = (EditText)findViewById(R.id.editTextXNMK2);
        btnDoiMK = (Button)findViewById(R.id.buttonDoiMK);
        btnLogout = (Button)findViewById(R.id.buttonLogout);

        Bundle extras = getIntent().getExtras();
        final String id = extras.getString("id");
        final String pass = extras.getString("pass");


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mh1 = new Intent(QuanLy_Activity.this, MainActivity.class);
                startActivity(mh1);
            }
        });

        btnDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(helper.searchPass(id).equals(edtMKCu.getText().toString())){
                    if(edtMKMoi.getText().toString().equals(edtXNMKMoi.getText().toString())){
                        helper.UpdateTK(id,edtMKMoi.getText().toString());
                        Toast.makeText(getApplicationContext(),R.string.changepass_success,Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),R.string.notmatch,Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quan_ly_, menu);
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
