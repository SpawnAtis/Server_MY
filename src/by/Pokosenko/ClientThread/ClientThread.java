
package by.Pokosenko.ClientThread;

import by.Pokosenko.Message.Message;
import by.Pokosenko.Utils.Actions;
import by.Pokosenko.Work.Work;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread extends Thread {

    private Socket socket;
    private Message message;
    private String login;

    public ClientThread(Socket socket) {
        this.socket = socket;
        this.start();
    }

    public void run() {
        try {
            //потоки ввода, вывода для работы с сокетом
            final ObjectInputStream inputStream   = new ObjectInputStream(this.socket.getInputStream());
            final ObjectOutputStream outputStream = new ObjectOutputStream(this.socket.getOutputStream());

            while(true) {

                this.message = (Message)inputStream.readObject();
                Actions sw = this.message.getAction();

                switch (sw)
                {
                    case SignIn:
                        System.out.println("Сервер получил команду к авторизации");
                        break;
                    case Registration:
                        System.out.println("Сервер получил команду к регистрации");
                        Work.Registration();
                        break;

                }

            }

        } catch (SocketException e) {
            //System.out.println(login + " disconnected!");
            //Server.getUserList().deleteUser(login);
            //broadcast(Server.getUserList().getClientsList(), new Message("Server-Bot", "The user " + login + " has been disconnect", getUserList().getUsers()));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
