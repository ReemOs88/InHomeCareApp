package com.example.inhomecareapp.chat;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inhomecareapp.R;
import com.example.inhomecareapp.databinding.ItemRecieveMessageBinding;
import com.example.inhomecareapp.databinding.ItemSendMessageBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
public class MessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int SEND_MESSAGE = 0;
    private final int RECEIVE_MESSAGE = 1;
    private final List<Message> messages;
    public MessagesAdapter(List<Message> messages) {
        this.messages = messages;
    }
    @Override
    public int getItemViewType(int position) {
        String userId = FirebaseAuth.getInstance().getUid();
        String messageUserId = String.valueOf(messages.get(position).getUserId());
        if (userId.equals(messageUserId)) {
            return SEND_MESSAGE;
        } else {
            return RECEIVE_MESSAGE;
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SEND_MESSAGE) {
            return new SendHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_send_message, parent, false));
        } else {
            return new ReceiveHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_recieve_message, parent, false));
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);
        if (holder.getItemViewType() == SEND_MESSAGE) {
            ((SendHolder) holder).binding.message.setText(message.getMessage());
        } else {
            ((ReceiveHolder) holder).binding.message.setText(message.getMessage());
        }
    }
    @Override
    public int getItemCount() {
        return messages.size();
    }
    class SendHolder extends RecyclerView.ViewHolder {
        ItemSendMessageBinding binding;
        public SendHolder(@NonNull ItemSendMessageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    class ReceiveHolder extends RecyclerView.ViewHolder {
        ItemRecieveMessageBinding binding;
        public ReceiveHolder(@NonNull ItemRecieveMessageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
