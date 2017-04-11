# Chat
Chat - Simple network chat application, base on Java RMI Technology and Multithreading.

If you want to use chat on network computers, you need:
1. Add the service.policy file to the application's start directory.
2. Run program with option (for java VM): -Djava.security.policy = server.policy

Content of service.policy file:
grant {
    permission java.security.AllPermission;
};

You can config newtwork security another. you can see:
https://ru.wikipedia.org/wiki/RMI

![Main Window](https://github.com/avedensky/chat/raw/master/img/Chat2Chat.png)

![Options Window](https://github.com/avedensky/chat/raw/master/img/ChatOptions.png)
