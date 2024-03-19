package com.starkindustries.recyclerview_with_database.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.starkindustries.recyclerview_with_database.Database.DatabaseHandler;
import com.starkindustries.recyclerview_with_database.Model.Contacts;
import com.starkindustries.recyclerview_with_database.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    Context context;
    ArrayList<Contacts> contact_list;
    AppCompatEditText update_name,update_number;
    AppCompatButton update;
    public RecyclerViewAdapter(Context context, ArrayList<Contacts> contact_list)
    {
        this.context=context;
        this.contact_list=contact_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(itemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(contact_list.get(holder.getAdapterPosition()).name);
        holder.number.setText(contact_list.get(holder.getAdapterPosition()).number);
        holder.contact_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.update_dialog);
                update_name=dialog.findViewById(R.id.update_name);
                update_number=dialog.findViewById(R.id.update_number);
                update=dialog.findViewById(R.id.update);
                update_name.setText(contact_list.get(holder.getAdapterPosition()).name);
                update_number.setText(contact_list.get(holder.getAdapterPosition()).number);
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseHandler db = new DatabaseHandler(context);
//                        contact_list.set(holder.getAdapterPosition(), new Contacts(update_name.getText()
//                                .toString().trim(),update_number.getText().toString().trim()));
//                        db.update_contact(holder.getAdapterPosition());
                        notifyItemChanged(holder.getAdapterPosition());
                        Log.d("Contact_updated","Contact "+contact_list.get(holder.getAdapterPosition()).name+" updated Sucessfully");
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contact_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView name,number;
        ConstraintLayout contact_row;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            number=itemView.findViewById(R.id.number);
            contact_row=itemView.findViewById(R.id.contact_row);
        }
    }
}
