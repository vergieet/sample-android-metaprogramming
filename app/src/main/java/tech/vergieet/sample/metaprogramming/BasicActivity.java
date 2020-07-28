package tech.vergieet.sample.metaprogramming;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import tech.vergieet.sample.metaprogramming.fragments.FragmentSwitcher;
import tech.vergieet.sample.metaprogramming.fragments.MainMenuFragment;

public class BasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        FragmentSwitcher
                .create(getSupportFragmentManager())
                .switchTo(new MainMenuFragment());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                FragmentSwitcher.create(getSupportFragmentManager())
                        .switchTo(new MainMenuFragment());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
