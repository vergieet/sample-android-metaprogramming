package tech.vergieet.sample.metaprogramming.fragments;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tech.vergieet.sample.metaprogramming.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class BasicActivityFragment extends Fragment {

    public BasicActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basic, container, false);
    }
}
