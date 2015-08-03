package com.example.os;

/**
 * Created by Mr.Jude on 2015/8/3.
 */
public class Handler {
    private Looper looper;
    final Callback mCallback;

    public interface Callback {
        boolean handleMessage(Message msg);
    }

    public Handler(){
        this(null);
    }

    public Handler(Callback callback) {
        looper = Looper.myLooper();
        mCallback = callback;
    }


    public void post(Runnable runnable){
        send(new Message(runnable,0));
    }

    //��Looper����Message��Looper���������߳��ϵ����Լ���dispatchMessage()
    public void send(Message message){
        looper.mQueue.enqueueMessage(message);
    }

    //���¾��Ƿ��䴦��Message�����ˡ����߳��޹ء�
    public void dispatchMessage(Message msg) {
        if (msg.callback != null) {
            handleCallback(msg);
        } else {
            if (mCallback != null) {
                if (mCallback.handleMessage(msg)) {
                    return;
                }
            }
            handleMessage(msg);
        }
    }

    private static void handleCallback(Message message) {
        message.callback.run();
    }

    public void handleMessage(Message msg) {
    }


}
