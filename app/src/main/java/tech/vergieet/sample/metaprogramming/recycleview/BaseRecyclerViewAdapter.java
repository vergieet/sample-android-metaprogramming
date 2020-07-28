package tech.vergieet.sample.metaprogramming.recycleview;

import android.annotation.SuppressLint;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import tech.vergieet.sample.metaprogramming.R;

public class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {

    private final Class<T> clazz;
    private final List<T> mValues;

    public BaseRecyclerViewAdapter(List<T> items, Class<T> clazz) {
        mValues = items;
        this.clazz = clazz;
    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_empty, parent, false);
        return new BaseViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        T objectToReflect = mValues.get(position);
        TableRow tableRow = new TableRow(holder.mView.getContext());

        for (Field field : clazz.getDeclaredFields()) {
            TextView generatedTextView = new TextView(holder.mView.getContext(), null, R.style.TextAppearance_AppCompat_Menu);
            generatedTextView.setPadding(16, 16, 16, 16);
            generatedTextView.setGravity(Gravity.START);


            String getterMethodString = "get" + field.getName().toUpperCase().charAt(0) + field.getName().substring(1);
            try {
                Method getterMethod = clazz.getDeclaredMethod(getterMethodString);
                Object valueFromGetter = getterMethod.invoke(objectToReflect);
                generatedTextView.setText(null == valueFromGetter ? "" : valueFromGetter.toString());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            tableRow.addView(generatedTextView);
        }

        holder.mLayout.addView(tableRow);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

}
