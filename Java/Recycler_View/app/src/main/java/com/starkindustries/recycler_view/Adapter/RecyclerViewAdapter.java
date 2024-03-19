package com.starkindustries.recycler_view.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputBinding;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.starkindustries.recycler_view.Model.Contact;
import com.starkindustries.recycler_view.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    public int row_position=-1;
    public Context context;
    public ArrayList<Contact> contact_list;
    public AppCompatEditText name,number;
    public AppCompatButton update_button;
    public RecyclerViewAdapter(Context context,ArrayList<Contact> contact_list)
    {
        this.context=context;
        this.contact_list=contact_list;
    }
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(context).inflate(R.layout.contact_row,parent,false);
        ViewHolder viewholder = new ViewHolder(itemview);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.img.setImageResource(contact_list.get(position).image);
        holder.name.setText(contact_list.get(position).name);
        holder.number.setText(contact_list.get(position).number);
        setanimation(holder.itemView, holder.getAdapterPosition());
        holder.row_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder update = new AlertDialog.Builder(context);
                update.setIcon(R.drawable.update);
                update.setTitle("Update");
                update.setMessage("Are you sure,you want to Update?");
                update.setCancelable(false);
                update.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Dialog dialog = new Dialog(context);
                        dialog.setCancelable(false);
                        dialog.setContentView(R.layout.update_dialog);
                        name=dialog.findViewById(R.id.name);
                        number=dialog.findViewById(R.id.number);
                        update_button=dialog.findViewById(R.id.update);
                        name.setText(contact_list.get(holder.getAdapterPosition()).name);
                        number.setText(contact_list.get(holder.getAdapterPosition()).number);
                        update_button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                contact_list.set(holder.getAdapterPosition(),new Contact(name.getText().toString().trim(),number.getText().toString().trim()));
                                notifyItemChanged(holder.getAdapterPosition());
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                    }
                });
                update.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                update.show();
            }
        });
        holder.row_click.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder delete_dialog = new AlertDialog.Builder(context);
                delete_dialog.setIcon(R.drawable.delete);
                delete_dialog.setTitle("Delete");
                delete_dialog.setMessage("Are you sure,you want to delete?");
                delete_dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        notifyItemRemoved(holder.getAdapterPosition());
                    }
                });
                delete_dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                delete_dialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                delete_dialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return contact_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        AppCompatTextView name,number;
        LinearLayoutCompat row_click;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.contact_logo);
            name=itemView.findViewById(R.id.contact_name);
            number=itemView.findViewById(R.id.contact_number);
            row_click= itemView.findViewById(R.id.update);
        }
    }
    public void setanimation(View itemview,int position)
    {
        if(row_position>0) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemview.startAnimation(animation);
            row_position=position;
        }
    }
}
