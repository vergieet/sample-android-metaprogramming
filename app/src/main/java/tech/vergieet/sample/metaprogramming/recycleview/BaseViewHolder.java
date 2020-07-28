package tech.vergieet.sample.metaprogramming.recycleview;

import android.view.View;
import android.widget.TableLayout;

import androidx.recyclerview.widget.RecyclerView;

import tech.vergieet.sample.metaprogramming.R;

public class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public final View mView;
    public final TableLayout mLayout;
    public T mItem;

    public BaseViewHolder(View view) {
        super(view);
        mView = view;
        mLayout = view.findViewById(R.id.empty_layout);

    }

}
