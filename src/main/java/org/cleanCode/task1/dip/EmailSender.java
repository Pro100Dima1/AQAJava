package org.cleanCode.task1.dip;

public class EmailSender implements MessageSender{
    @Override
    public void send(String message) {
        System.out.println("Отправка email: " + message);
    }
}
