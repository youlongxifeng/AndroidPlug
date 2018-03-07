package com.youlong.plugapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author Administrator
 * @name PlugApp
 * @class name：com.youlong.plugapp
 * @class describe
 * @time 2018/3/7 14:28
 * @change
 * @class describe
 */

public class PlugFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_plug, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        String buttonName = null;

        Button button = (Button) view.findViewById(R.id.start_toast);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplication(), "问候一下!", Toast.LENGTH_SHORT).show();
            }
        });
        if (bundle != null) {
            buttonName = bundle.getString("bundler");
            Log.i("TAG", "bundle==" + buttonName);
            button.setText(buttonName);
        }

    }
}
