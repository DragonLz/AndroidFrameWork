package com.example.os;

/**
 * Created by Mr.Jude on 2015/8/3.
 */
public class Looper {

    /**
     * ÿ���̶߳�ֻ��һ������(Looper)��
     * ThreadLocal ���ʿ��Լ����Ϊ HashMap<Thread,Object>.
     * ��get��set��KeyĬ��Ϊ��ǰ�߳�
     */
    private static ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();

    /**
     * ��Ϣ����
     */
    static MessageQueue mQueue;


    Looper(){
        mQueue = new MessageQueue();
    }

    /**
     * ׼����newһ��Looper�Ž�ThreadLocal��
     */
    public static void prepare(){
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper());
    }

    /**
     * ������������������Ϳ�ʼ������߳�������ѭ��
     */
    public static void loop(){
        final Looper me = myLooper();
        if (me == null) {
            throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
        }


        while (true){
            // ��ȡ��һ��Message��������Զ��ȡ��һ����
            // ֻ�е����ȥnull��ʱ��Ż᷵��null��
            // ��������Ϊ��ʱ��������ֱ��������Message���ٷ��ء�
            Message msg = mQueue.next();

            //���ȡ��null�����˳�
            if (msg == null)return;

            //ȡ���˾ͽ���Message��Ŀ��Handlerȥ����
            msg.target.dispatchMessage(msg);
        }
    }

    /**
     * ��ȡ��ǰ�̵߳�Looper
     * @return
     */
    public static Looper myLooper(){ return sThreadLocal.get();}
}
