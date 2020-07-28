package tech.vergieet.sample.metaprogramming.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import tech.vergieet.sample.metaprogramming.R;
import tech.vergieet.sample.metaprogramming.models.Department;
import tech.vergieet.sample.metaprogramming.models.Employee;

public class MainMenuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);

        Button employeesButton = view.findViewById(R.id.button_employees);
        Button departmentsButton = view.findViewById(R.id.button_departments);

        employeesButton .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                FragmentSwitcher
                        .create(getFragmentManager())
                        .switchTo(BaseFragment.create(Employee.class));
            }
        });

        departmentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                FragmentSwitcher
                        .create(getFragmentManager())
                        .switchTo(BaseFragment.create(Department.class));
            }
        });

        return view;
    }
}
