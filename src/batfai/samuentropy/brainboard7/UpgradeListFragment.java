package batfai.samuentropy.brainboard7;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class UpgradeListFragment extends android.app.Fragment {

    private List<String> options;
    private ListView optionList;
    private String currentOption;

    public ListView  getOptionList(){
        return  optionList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private Context context;
    private OnItemChangedListener onItemChangedListener;

    public void setOnItemChangedListener(OnItemChangedListener listener){
        onItemChangedListener = listener;
    }
    public String getCurrentOption(){
        return currentOption;
    }

    public UpgradeListFragment(){
        options = new ArrayList<String>();

    }


    public void setOptions(ArrayList<String> options){
        this.options = options;

    }

    public interface OnItemChangedListener{
        public void onItemChanged(String newItem);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_upgrade_list, container, false);
        optionList = (ListView) view.findViewById(R.id.upgrade_list_listview);

        //optionList.setAdapter(new ArrayAdapter<String>(context,  android.R.layout.simple_list_item_1, options));

        /*optionList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("gery", optionList.getSelectedItem().toString());
                onItemChangedListener.onItemChanged(optionList.getSelectedItem().toString());
            }
        });*/
        optionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("gery", options.get(i));
                onItemChangedListener.onItemChanged(options.get(i));
            }
        });
        //return super.onCreateView(inflater, container, savedInstanceState);

        return view;
    }


}
