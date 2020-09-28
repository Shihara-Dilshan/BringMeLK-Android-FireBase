package app.noobstack.bringme.bringmelk.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import app.noobstack.bringme.bringmelk.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private ViewFlipper viewFlipper;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        viewFlipper = root.findViewById(R.id.v_flipper2);

        int image[] = {R.drawable.imsgeslider1, R.drawable.imsgeslider2, R.drawable.imsgeslider3, R.drawable.imsgeslider4};

        for(int i=0; i<image.length; i++){
            flipperImage(image[i]);
        }

        for(int images: image){
            flipperImage(images);
        }


        return root;
    }


    public void flipperImage(int image){
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getActivity(), android.R.anim.slide_out_right);
    }
}