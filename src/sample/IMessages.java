package sample;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Alexey Vedensky on 10.04.2017.
 * MAIL: avedensky@gmail.com
 */
public interface IMessages  extends Remote{
    String getLast() throws RemoteException;
    String[] getLast(int n) throws RemoteException;
    void addMessage(String s) throws RemoteException;
    int getCount() throws RemoteException;

    void sayHello() throws RemoteException;
    String getHello () throws RemoteException;
}
