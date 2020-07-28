package tech.vergieet.sample.metaprogramming.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import tech.vergieet.sample.metaprogramming.R;
import tech.vergieet.sample.metaprogramming.annotations.FromApi;
import tech.vergieet.sample.metaprogramming.annotations.HideOnForm;
import tech.vergieet.sample.metaprogramming.helpers.BaseHelper;

public class BaseFormFragment<T> extends Fragment implements Callback {

    private Class<T> clazz;
    private String apiUrl;

    private Map<String, TextInputEditText> mapOfEditTextByFieldName = new HashMap<>();

    public BaseFormFragment(Class<T> clazz) {
        this.clazz = clazz;
        this.apiUrl = clazz.getAnnotation(FromApi.class).url();
    }

    public static <T> BaseFormFragment create(Class<T> clazz) {
        return new BaseFormFragment<>(clazz);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.empty_linear_layout, container, false);
        LinearLayout linearLayout = view.findViewById(R.id.empty_linear_layout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            HideOnForm hideOnForm = field.getAnnotation(HideOnForm.class);
            if (hideOnForm == null || !hideOnForm.value()) {

                TextInputEditText inputForm = new TextInputEditText(view.getContext());
                inputForm.setHint(
                        field.getName().toUpperCase().charAt(0) + field.getName().substring(1)
                );
                inputForm.setLayoutParams(layoutParams);
                mapOfEditTextByFieldName.put(field.getName(), inputForm);
                linearLayout.addView(inputForm);
            }
        }

        Button saveButton = new Button(view.getContext());
        saveButton.setText("Save");
        saveButton.setLayoutParams(layoutParams);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> formValues = new HashMap<>();
                for (String key : mapOfEditTextByFieldName.keySet()) {
                    formValues.put(
                            key,
                            mapOfEditTextByFieldName.get(key).getText().toString().trim().equals("") ?
                                    null :
                                    mapOfEditTextByFieldName.get(key).getText().toString()
                    );
                }
                BaseHelper.create(apiUrl)
                        .insert(formValues, BaseFormFragment.this);
            }
        });
        linearLayout.addView(saveButton);

        return view;
    }

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {

    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        Log.d("TEST", response.body().string());
        FragmentSwitcher.create(getFragmentManager()).switchTo(BaseFragment.create(clazz));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentSwitcher.create(getActivity().getSupportFragmentManager())
                        .switchTo(BaseFragment.create(clazz));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
