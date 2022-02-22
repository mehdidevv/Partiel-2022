package sabri.elmahdi.formula1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import sabri.elmahdi.formula1.R;
import sabri.elmahdi.formula1.service.ApiResponse;


public class RoundedAdapter extends RecyclerView.Adapter<RoundedAdapter.ViewHolder> {
    private final List<ApiResponse> mDrivers;
    private final Context context;
    private OnNoteListener mOnNoteListener;

    public RoundedAdapter(List<ApiResponse> mDrivers, Context context, OnNoteListener mOnNoteListener) {
        this.mDrivers = mDrivers;
        this.context = context;
        this.mOnNoteListener = mOnNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.circular_item, parent, false);
        return new ViewHolder(contactView,mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ApiResponse driver = mDrivers.get(position);

        ImageView imageImageView = holder.pictureImageView;
        Glide.with(context).load(driver.getDriver().getImage()).into(imageImageView);



    }

    @Override
    public int getItemCount() {
        return mDrivers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView pictureImageView;
        OnNoteListener mOnNoteListener;

        public ViewHolder(View itemView, OnNoteListener onNoteListener  ) {
            super(itemView);


            pictureImageView = (ImageView) itemView.findViewById(R.id.picture);

            mOnNoteListener = onNoteListener;
            itemView.setOnClickListener(this);

        }
        public void onClick(View view) {
            mOnNoteListener.onNoteClick(getAdapterPosition());
        }

    }
    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
