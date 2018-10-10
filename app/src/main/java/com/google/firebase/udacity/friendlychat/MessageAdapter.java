package com.google.firebase.udacity.friendlychat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {

    private Context mContext;
    private List<FriendlyMessage> mMessages;

    public MessageAdapter(Context context){
        this.mContext = context;

    }


    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_message, viewGroup, false);

        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {

       /* FriendlyMessage messages = mMessages.get(position);
        String messageCopy = messages.getText();
        String nameValue = messages.getName();*/
       if (mMessages != null) {
           FriendlyMessage meszage = mMessages.get(position);

           boolean isPhoto = meszage.getPhotoUrl() != null;
           if (isPhoto){
               holder.messageTextView.setVisibility(View.GONE);
               holder.photoImageView.setVisibility(View.VISIBLE);
               Glide.with(holder.photoImageView.getContext())
                       .load(meszage.getPhotoUrl())
                       .into(holder.photoImageView);
           }else {
               holder.messageTextView.setVisibility(View.VISIBLE);
               holder.photoImageView.setVisibility(View.GONE);
               holder.messageTextView.setText(meszage.getText());
           }
           holder.nameTextView.setText(meszage.getName());

       }


    }

    @Override
    public int getItemCount() {
        if (mMessages == null){
            return 0;
        }
        return mMessages.size();
    }

    public List<FriendlyMessage> getMessages(){
        return mMessages;
    }

    /**
     * When data changes, this method updates the list of messages
     * and notifies the adapter to use the new values on it
     */
    public void setMessages(List<FriendlyMessage> messageLists) {
        mMessages = messageLists;
        notifyDataSetChanged();
    }

    class MessageHolder extends RecyclerView.ViewHolder{
        private ImageView photoImageView;
        private TextView messageTextView;
        private TextView nameTextView;

       public MessageHolder(View itemView){
           super(itemView);

           photoImageView = (ImageView)itemView.findViewById(R.id.photoImageView);
           messageTextView = (TextView)itemView.findViewById(R.id.messageTextView);
           nameTextView = (TextView)itemView.findViewById(R.id.nameTextView);

       }
    }
}
