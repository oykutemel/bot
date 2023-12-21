package com.example.mesajlasmabotu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText messageEditText;
    private MessageAdapter messageAdapter;
    private List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        messageEditText = findViewById(R.id.messageEditText);
        Button sendButton = findViewById(R.id.sendButton);

        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(messageAdapter);

        sendButton.setOnClickListener(v -> sendMessage());
    }

    @SuppressLint("NotifyDataSetChanged")
    private void sendMessage() {
        String userMessage = messageEditText.getText().toString().trim();

        // Burada botunuzun geri dönüş mesajını oluşturun.
        String botReply = generateBotReply(userMessage);

        // Kullanıcının mesajını ekleyin.
        messageList.add(new Message(userMessage, Message.USER_TYPE));

       // Botun cevabını ekleyin.
        messageList.add(new Message(botReply, Message.BOT_TYPE));

        // RecyclerView'ı güncelleyin.
        messageAdapter.notifyDataSetChanged();

        // Mesaj girişini temizleyin.
        messageEditText.getText().clear();

        // RecyclerView'u en sona kaydırın.
        recyclerView.scrollToPosition(messageList.size() - 1);
    }

    private String generateBotReply(String userMessage) {
        // Kullanıcının sorduğu soruya göre botun cevabını oluşturun.
        String botReply;

        if (containsIgnoreCase(userMessage, "nasıl") || containsIgnoreCase(userMessage, "nasılsın")) {
            botReply = "Ben bir botum, duygularım yok ama teşekkür ederim. Sen nasılsın?";
        } else {
            // Diğer durumlar için kullanıcı mesajını olduğu gibi döndürün.
            botReply = "Bot: " + userMessage;
        }

        return botReply;
    }

    // Büyük/küçük harfe duyarlı olmayan içerme kontrolü için yardımcı fonksiyon
    private boolean containsIgnoreCase(String source, String subItem) {
        return source.toLowerCase().contains(subItem.toLowerCase());
    }

}
