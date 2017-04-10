package sample;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Alexey Vedensky on 10.04.2017.
 * MAIL: avedensky@gmail.com
 */
public class Messages extends UnicastRemoteObject implements IMessages {
    static ArrayList<String> messageList = new ArrayList<>();

    Messages() throws java.rmi.RemoteException {
        super();
    }

    @Override
    public String getLast() throws RemoteException {
        if (messageList.size() == 0)
            return null;
        return messageList.get(messageList.size() - 1);
    }

    @Override
    public String[] getLast(int n) throws RemoteException {
        if (messageList.size() == 0)
            return null;

        int start = messageList.size() - n;

        if (start < 0)
            start = 0;

        String[] result = new String[n];

        for (int i = start, j = 0; i < messageList.size(); i++, j++)
            result[j] = messageList.get(i);

        return result;
    }


    @Override
    public void addMessage(String s) throws RemoteException {
        messageList.add(s);
    }

    @Override
    public int getCount() throws RemoteException {
        return messageList.size();
    }


    @Override
    public void sayHello() {
        System.out.println("Hello");
    }

    @Override
    public String getHello() {
        return "hello";
    }
}
