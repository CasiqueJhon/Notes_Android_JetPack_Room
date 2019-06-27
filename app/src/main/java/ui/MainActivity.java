package ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jhonisaac.recyclerviewsample.R;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    Button btn_log_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        email = findViewById(R.id.et_email);
        pass = findViewById(R.id.et_password);
        btn_log_in = findViewById(R.id.btn_log_in);

        btn_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(intent);

            }
        });




    }
}
