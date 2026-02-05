package org.cleanCode.task1.dip;

public class Main {
    public static void main(String[] args) {
        MessageSender sender = new EmailSender();
        NotificationService service = new NotificationService(sender);
        service.sendNotification("Привет!");
    }
}
