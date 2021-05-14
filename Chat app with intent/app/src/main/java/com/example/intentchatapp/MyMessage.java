package com.example.intentchatapp;

import java.util.ArrayList;
import java.util.List;

public class MyMessage {
    ArrayList<String> sender = new ArrayList<>();
    List<Boolean> isSender = new ArrayList<Boolean>();
    List<Boolean> isPicture = new ArrayList<Boolean>();

    // Thêm tin nhắn từ người gửi
    public  void AddSender(String list1)
    {
        sender.add(list1);
        isSender.add(true);
        isPicture.add(false);
    }

    // Thêm tin nhắn từ user 2
    public  void AddReceiver(String list1)
    {
        sender.add(list1);
        isSender.add(false);
        isPicture.add(false);
    }

    // Thêm hình từ người gửi
    public void AddSenderPicture(String imageInfo)
    {

        sender.add(imageInfo);
        isSender.add(true);
        isPicture.add(true);
    }

    // Thêm hình từ user2
    public void AddReciverPicture(String imageInfo)
    {

        sender.add(imageInfo);
        isSender.add(false);
        isPicture.add(true);
    }

}
