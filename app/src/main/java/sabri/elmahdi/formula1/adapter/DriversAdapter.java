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
import sabri.elmahdi.formula1.service.Driver;

public class DriversAdapter extends RecyclerView.Adapter<DriversAdapter.ViewHolder> {
    private final List<ApiResponse> mDrivers;
    private final Context context;
    private OnNoteListener mOnNoteListener;

    public DriversAdapter(List<ApiResponse> mDrivers, Context context, OnNoteListener mOnNoteListener) {
        this.mDrivers = mDrivers;
        this.context = context;
        this.mOnNoteListener = mOnNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.drivers_item, parent, false);
        return new ViewHolder(contactView,mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ApiResponse driver = mDrivers.get(position);

        TextView employeeNameTextView = holder.nameTextView;
        employeeNameTextView.setText(driver.getDriver().getName());

        TextView employeeSalaryTextView = holder.teamTextView;
        employeeSalaryTextView.setText(driver.getTeam().getName());

        TextView employeeAgeTextView = holder.pointsTextView;
        employeeAgeTextView.setText(driver.getPoints() + " pts");



    }

    @Override
    public int getItemCount() {
        return mDrivers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nameTextView;
        public TextView teamTextView;
        public TextView pointsTextView;
        OnNoteListener mOnNoteListener;

        public ViewHolder(View itemView, OnNoteListener onNoteListener  ) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.name);
            teamTextView = (TextView) itemView.findViewById(R.id.team);
            pointsTextView = (TextView) itemView.findViewById(R.id.points);

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
