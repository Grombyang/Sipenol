package com.example.sipenol.Entitas;

public class ServerMessage {
    private String title;
    private boolean status;
    private String body;

    public ServerMessage(String title, boolean status, String body) {
        this.title = title;
        this.status = status;
        this.body = body;
    }
    public ServerMessage(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
