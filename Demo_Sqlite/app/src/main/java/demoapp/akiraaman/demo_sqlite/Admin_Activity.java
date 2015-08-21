package demoapp.akiraaman.demo_sqlite;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class Admin_Activity extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);
    EditText edtTaiKhoan;
    Button btnXoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_);



        edtTaiKhoan = (EditText)findViewById(R.id.editTextTaiKhoan);
        btnXoa = (Button)findViewById(R.id.buttonXoa);

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( helper.searchTK(edtTaiKhoan.getText().toString()) == true ){
                    //helper.deleteUser(edtTaiKhoan.getText().toString());
                    helper.deleteAll();
                    Toast.makeText(getApplicationContext(),R.string.delete_success,Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),R.string.delete_error,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin_, menu);
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
