package com.example.pandacare.PandaChat;

public class ChatModal{

        // initialise string to store our message and sender
        private String message;
        private String sender;

        // creating constructor
        public ChatModal(String message, String sender) {
            this.message = message;
            this.sender = sender;
        }

        // initialise getter and setter methods
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }
}
