package tech.vergieet.sample.metaprogramming.fragments;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import tech.vergieet.sample.metaprogramming.R;

public class FragmentSwitcher {

    private FragmentManager fragmentManager;
    private int containerViewId;

    private FragmentSwitcher(FragmentManager fragmentManager, int containerViewId) {
        this.fragmentManager = fragmentManager;
        this.containerViewId = containerViewId;
    }

    public static FragmentSwitcher create(FragmentManager fragmentManager) {
        return create(fragmentManager, R.id.fragment);
    }

    public static FragmentSwitcher create(FragmentManager fragmentManager, int containerViewId) {
        return new FragmentSwitcher(fragmentManager, containerViewId);
    }

    public void switchTo(Fragment fragmentToSwitch) {
        FragmentTransaction transaction = this.fragmentManager.beginTransaction();

        transaction.replace(containerViewId, fragmentToSwitch);
        transaction.addToBackStack(null);

        transaction.commit();
    }
}
