package sy.project2019.itshow.a2019record.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sy.project2019.itshow.a2019record.R;


public class MonthlyViewFragment extends Fragment {

    public MonthlyViewFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monthly_view, container, false);
        return view;
    }
}
