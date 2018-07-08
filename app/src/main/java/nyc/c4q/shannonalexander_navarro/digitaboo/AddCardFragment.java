package nyc.c4q.shannonalexander_navarro.digitaboo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddCardFragment extends Fragment {

    private FloatingActionButton closeBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.add_card_fragment, container, false);
        initViews(root);
        closeClick();
        return root;
    }

    private void initViews(View view) {
        closeBtn = view.findViewById(R.id.close_frag_btn);
    }

    private void closeClick() {
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeFragment();
            }
        });
    }

    private void closeFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
