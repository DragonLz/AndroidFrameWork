package com.example.os;

/**
 * Created by Mr.Jude on 2015/8/3.
 */
public class Message {

    //һ��ѿ�Я������
    public int what;
    public int arg1;
    public int arg2;
    public Object obj;


    Runnable callback;

    /**
     * Ŀ��Handler��
     * һ�㶼�Ƿ�����Message��Handler.
     */
    Handler target;

    public Message(Runnable callback, int what) {
        this.callback = callback;
        this.what = what;
    }
}
