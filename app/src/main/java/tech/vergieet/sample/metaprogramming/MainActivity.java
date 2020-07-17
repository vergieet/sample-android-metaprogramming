package tech.vergieet.sample.metaprogramming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button useModelButton, useEndpointButton, useEndpointGeneratedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        useModelButton = findViewById(R.id.button);
        useEndpointButton = findViewById(R.id.button2);
        useEndpointGeneratedButton = findViewById(R.id.button3);

        useModelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BasicActivity.class);
                intent.putExtra("MENU","USE_MODEL");
                startActivity(intent);
            }
        });
        useEndpointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BasicActivity.class);
                intent.putExtra("MENU","USE_ENDPOINT");
                startActivity(intent);
            }
        });
        useEndpointGeneratedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BasicActivity.class);
                intent.putExtra("MENU","USE_ENDPOINT_GENERATED");
                startActivity(intent);
            }
        });
    }

}
