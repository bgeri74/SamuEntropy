package batfai.samuentropy.brainboard7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class UpgradeDetailsFragment extends android.app.Fragment {

    private ImageView imageView;
    private TextView name;
    private TextView count;

    private Button increaseButton, decreaseButton, clearButton;

    public Button getIncreaseButton(){
        return increaseButton;
    }

    public Button getDecreaseButton(){
        return decreaseButton;
    }

    public Button getClearButton(){return clearButton;}

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public String getName() {
        return name.getText().toString();
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setItem(String name){
        int resId = getResources().getIdentifier(name, "drawable",
                "batfai.samuentropy.brainboard7");
        imageView.setImageResource(resId);
        this.name.setText(name);
    }

    public void setItem(int imageResource, String name, int count){
        this.imageView.setImageResource(imageResource);
        this.name.setText(name);
        this.count.setText(count + "");
    }

    public int getCount() {
        return Integer.parseInt(count.getText().toString());
    }

    public void setCount(int count) {
        this.count.setText(count + "");
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View view = super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_upgrade_details, container, false);
        imageView = (ImageView) view.findViewById(R.id.upgrade_details_image);

        name = (TextView) view.findViewById(R.id.upgrade_details_name);

        count = (TextView) view.findViewById(R.id.upgrade_details_count);

       // name.setText("Hello there");
        increaseButton = (Button) view.findViewById(R.id.upgrade_details_increase_btn);
        decreaseButton = (Button) view.findViewById(R.id.upgrade_details_decrease_btn);
        clearButton = (Button) view.findViewById(R.id.upgrade_details_clear_btn);

        return view;
    }
}
