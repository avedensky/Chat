package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;



public class Controller {
    private IMessages service; // Interface for call remote methods

    //Chat
    @FXML
    private TextArea chatArea;
    @FXML
    private TextField sendMessageField;

    //Options
    @FXML
    private CheckBox checkBoxServerClient;
    @FXML
    private TextField nameField;
    @FXML
    private TextField portField;
    @FXML
    private TextField ipField;

    @FXML
    private Circle connectLED;


    /**
     * Nothing :)
     */
    public void initialize() {
        IMessages service = null;
    }

    /**
     * Create Thread updater for show new messages
     */
    public void startShowingMessage() {
        ChatUpdaterThread ChatUpdaterThread = new ChatUpdaterThread();
        ChatUpdaterThread.start();
    }


    /**
     * Click connect button. Set client or server, check state and run thread for update messages in text area.
     *
     * @param event
     */
    @FXML
    protected void onClickConnect(ActionEvent event) {

        String port = portField.getText().trim();
        String ip = ipField.getText();
        String sreviceName = "MessagesService";
        String connectPath = "rmi://" + ip + ":" + port + "/" + sreviceName;

        if (checkBoxServerClient.isSelected()) { //if Server
            try {
                service = new Messages();
                Registry registry = LocateRegistry.createRegistry(Integer.parseInt(port));
                Naming.rebind(connectPath, service);

                connectLED.setFill(Color.LIME);
                startShowingMessage ();
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                connectLED.setFill(Color.RED);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                connectLED.setFill(Color.RED);
            }

        } else { //if Client
            try {
                service = (IMessages) Naming.lookup(connectPath); //Ищем наш сервис в удаленном регистре
                if (service.getHello().equals("hello")) {//server is alive ?
                    connectLED.setFill(Color.LIME);// Yes
                    startShowingMessage ();
                    return;
                }
            } catch (NotBoundException e) {
                e.printStackTrace();
                connectLED.setFill(Color.RED);
            } catch (RemoteException e) {
                e.printStackTrace();
                connectLED.setFill(Color.RED);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                connectLED.setFill(Color.RED);
            }
        }
    }


    /**
     * Click button and send text message. (call remote or local method)
     *
     * @param event
     */
    @FXML
    protected void onClickSend(ActionEvent event) {
        try {
            service.addMessage(nameField.getText() + ": " + sendMessageField.getText());
            sendMessageField.clear();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    /**
     * Update Chat Area (Thread)
     */
    public class ChatUpdaterThread extends Thread {
        public void run() {
            try {
                int n = service.getCount();
                while (true) {
                    if (service.getCount() > n) {
                        chatArea.appendText(service.getLast() + "\n");
                        n = service.getCount();
                    }
                    Thread.sleep(500);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
