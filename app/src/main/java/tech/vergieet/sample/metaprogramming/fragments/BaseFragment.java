package tech.vergieet.sample.metaprogramming.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import tech.vergieet.sample.metaprogramming.recycleview.BaseRecyclerViewAdapter;
import tech.vergieet.sample.metaprogramming.R;
import tech.vergieet.sample.metaprogramming.annotations.FromApi;
import tech.vergieet.sample.metaprogramming.helpers.BaseHelper;
import tech.vergieet.sample.metaprogramming.models.BaseListResult;

public class BaseFragment<T> extends Fragment implements Callback {

    private BaseRecyclerViewAdapter<T> adapter;
    private List<T> data;

    private Class<T> clazz;
    private String apiUrl;

    public BaseFragment(Class<T> clazz) {
        this.clazz = clazz;
        apiUrl = clazz.getAnnotation(FromApi.class).url();
    }

    public static <T> BaseFragment create(Class<T> clazz) {
        return new BaseFragment<>(clazz);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_model_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            data = new ArrayList<>();
            adapter = new BaseRecyclerViewAdapter<T>(data, clazz);

            BaseHelper.create(this.apiUrl)
                    .getAll(this);

            recyclerView.setAdapter(adapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        Log.d("TEST_REQUEST", Objects.requireNonNull(e.getMessage()));
    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        final String resultString = response.body().string();
        Log.d("TEST_REQUEST", resultString);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                BaseListResult<T> dataResult = null;
                dataResult = new GsonBuilder().create().fromJson(resultString, TypeToken.getParameterized(BaseListResult.class, clazz).getType());
                data.clear();
                Log.d("TEST_d", String.valueOf(dataResult.getData().get(0).getClass()));
                data.addAll(dataResult.getData());
                Log.d("TEST_REQUEST", data.toString());
                adapter.notifyDataSetChanged();
            }
        });

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_basic, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                FragmentSwitcher.create(getActivity().getSupportFragmentManager())
                        .switchTo(new MainMenuFragment());
                return true;
            case R.id.action_add:
                FragmentSwitcher.create(getActivity().getSupportFragmentManager())
                        .switchTo(BaseFormFragment.create(clazz));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
