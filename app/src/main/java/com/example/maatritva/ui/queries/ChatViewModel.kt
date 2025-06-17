package com.example.maatritva.ui.queries

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val TAG = "ChatViewModel"

    val messageList = mutableStateListOf<MessageModel>()

    private val generativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = Constants.apiKey,
        generationConfig = generationConfig {
            temperature = 0.7f
            topK = 40
            topP = 0.95f
            maxOutputTokens = 1000
        }
    )

    init {
        try {
            // Add welcome message
            messageList.add(MessageModel(
                "Hello! I'm your maternal care assistant. How can I help you today?",
                "model"
            ))
            Log.d(TAG, "ChatViewModel initialized successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing ChatViewModel: ${e.message}", e)
        }
    }

    fun sendMessage(question: String) {
        viewModelScope.launch {
            try {
                Log.d(TAG, "Sending message: $question")

                // Add user message immediately
                messageList.add(MessageModel(question, "user"))

                // Add typing indicator
                messageList.add(MessageModel("Typing...", "model"))

                // Create chat history
                val chat = generativeModel.startChat(
                    history = messageList.dropLast(1).map {
                        content(it.role) { text(it.message) }
                    }
                )
                Log.d(TAG, "Chat history created successfully")

                // Get response
                val response = chat.sendMessage(question)
                Log.d(TAG, "Received response from Gemini")

                // Remove typing indicator and add response
                messageList.removeAt(messageList. lastIndex)
                messageList.add(MessageModel(response.text ?: "Sorry, I couldn't generate a response.", "model"))
                Log.d(TAG, "Message processed successfully")
            } catch (e: Exception) {
                Log.e(TAG, "Error in sendMessage: ${e.message}", e)
                // Remove typing indicator and add error message
                messageList.removeAt(messageList. lastIndex)
                messageList.add(MessageModel(
                    "I apologize, but I'm having trouble connecting right now. Please try again in a moment.",
                    "model"
                ))
            }
        }
    }
}