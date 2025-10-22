public class Injection {
  // 1. Define the dependency interface
  interface MessageService {
    void sendMessage(String message);
  }

  // 2. Create concrete implementations
  static class EmailService implements MessageService {
    @Override
    public void sendMessage(String message) {
      System.out.println("Email sent: " + message);
    }
  }

  static class SMSService implements MessageService {
    @Override
    public void sendMessage(String message) {
      System.out.println("SMS sent: " + message);
    }
  }

  // 3. The class that depends on the service
  // This is loosely coupled. It doesn't know or care which service it's using.
  static class NotificationService {
    private final MessageService messageService;

    // The dependency is "injected" through the constructor
    public NotificationService(MessageService messageService) {
      this.messageService = messageService;
    }

    public void sendNotification(String notification) {
      messageService.sendMessage(notification);
    }
  }

  public static void main(String[] args) {
    // Create the dependency (the service object)
    MessageService emailer = new EmailService();
    MessageService texter = new SMSService();

    // Inject the email service into the notification service
    NotificationService emailNotifier = new NotificationService(emailer);
    emailNotifier.sendNotification("Your rorder has shipped!");

    // Inject the SMS service into a different notification service instance
    NotificationService smsNotifier = new NotificationService(texter);
    smsNotifier.sendNotification("Your package has been delivered!");
  }
}
