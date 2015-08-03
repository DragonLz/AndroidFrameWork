package com.example.os;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Mr.Jude on 2015/8/3.
 */
public class MessageQueue {

    /**
     * ��Ϣ����
     */
    private LinkedBlockingDeque<Message> queue = new LinkedBlockingDeque<>();
    //AndroidԴ���ʵ��ò���Լ�ʵ����һ���̰߳�ȫ��Queue���ܸ��ӡ������ģ��ԭ����ʵ���BlockingDequeҲ�Դ�ͬ����δʹ�á�


    /**
     * ��
     */
    private final Object lock = new Object();

    /**
     * ����һ��message.û����������ֱ�������ٷ��ء�
     * @return
     */
    Message next(){
        while (true){

            if (queue.peek() !=null){

                //�����������Message��ֱ�ӷ���
                return queue.poll();

            }else {

                //���������û��Message�����������ȴ��¼���Messageʱ����
                synchronized (lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
    }


    /**
     * ����м���һ��Message��������ǰ���wait
     * ������������������̵߳��á�
     * @param message
     */
    void enqueueMessage(Message message){
        synchronized (lock){
            queue.push(message);
            lock.notify();
        }
    }
}
