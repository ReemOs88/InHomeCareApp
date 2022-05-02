package com.example.inhomecareapp.chat;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.inhomecareapp.databinding.ActivityChatBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    ActivityChatBinding binding;
    private static final String TAG = "Chat";
    private String receiverId;
    private String receiverName;
    private String type;
    private MessagesAdapter adapter;
    private List<Message> messages = new ArrayList<>();

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        receiverId = getIntent().getStringExtra("userId");
        receiverName = getIntent().getStringExtra("username");
        type = getIntent().getStringExtra("type");

        binding.ivSend.setOnClickListener(view -> sendMessage());

        getMessages();
    }


    private void sendMessage() {
        String message = binding.etMessage.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            return;
        }

        binding.etMessage.setText("");

        Message newMessageData = new Message(
                String.valueOf(System.currentTimeMillis()),
                message,
                FirebaseAuth.getInstance().getUid()
        );

        Person senderPerson = new Person(
                "Reem",
                FirebaseAuth.getInstance().getUid(),
                message
        );

        Person receiverPerson = new Person(
                receiverName,
                receiverId,
                message
        );

        boolean isCustomer = type.equals("customer");

        if (isCustomer) {
            firestore.collection("inHomeCustomers")
                    .document(FirebaseAuth.getInstance().getUid())
                    .collection("messages")
                    .document(receiverId)
                    .set(receiverPerson);

            firestore.collection("inHomeCustomers")
                    .document(FirebaseAuth.getInstance().getUid())
                    .collection("messages")
                    .document(receiverId)
                    .collection("chat")
                    .document(newMessageData.getId())
                    .set(newMessageData);

            firestore.collection("inHomeCaregivers")
                    .document(receiverId)
                    .collection("messages")
                    .document(FirebaseAuth.getInstance().getUid())
                    .set(senderPerson);

            firestore.collection("inHomeCaregivers")
                    .document(receiverId)
                    .collection("messages")
                    .document(FirebaseAuth.getInstance().getUid())
                    .collection("chat")
                    .document(newMessageData.getId())
                    .set(newMessageData);
        }

        boolean isCaregiver = type.equals("caregiver");

        if (isCaregiver) {
            firestore.collection("inHomeCustomers")
                    .document(receiverId)
                    .collection("messages")
                    .document(FirebaseAuth.getInstance().getUid())
                    .set(receiverPerson);

            firestore.collection("inHomeCustomers")
                    .document(receiverId)
                    .collection("messages")
                    .document(FirebaseAuth.getInstance().getUid())
                    .collection("chat")
                    .document(newMessageData.getId())
                    .set(newMessageData);

            firestore.collection("inHomeCaregivers")
                    .document(FirebaseAuth.getInstance().getUid())
                    .collection("messages")
                    .document(receiverId)
                    .set(senderPerson);

            firestore.collection("inHomeCaregivers")
                    .document(FirebaseAuth.getInstance().getUid())
                    .collection("messages")
                    .document(receiverId)
                    .collection("chat")
                    .document(newMessageData.getId())
                    .set(newMessageData);
        }

    }


    private void getMessages() {
        String mainCollection = type.equals("customer") ? "inHomeCustomers" : "inHomeCaregivers";

        firestore.collection(mainCollection)
                .document(FirebaseAuth.getInstance().getUid())
                .collection("messages")
                .document(receiverId)
                .collection("chat")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        messages.clear();

                        for (DocumentSnapshot document : value.getDocuments()) {
                            Message message = document.toObject(Message.class);
                            messages.add(message);
                        }

                        adapter = new MessagesAdapter(messages);
                        binding.rvMessages.setAdapter(adapter);
                    }
                });
    }

}