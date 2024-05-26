package ba.unsa.etf.rpr.domain;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class NotificationCard extends CardDecorator {
    private final String emailAddress;

    public NotificationCard(Card decoratedCard, String emailAddress) {
        super(decoratedCard);
        this.emailAddress = emailAddress;
    }

    public void sendLowBalanceNotification() {
        String subject = "Low Balance Notification";
        String message = "Your balance is low. Please deposit more funds.";
        sendEmail(emailAddress, subject, message);
    }

    public void sendCouponActivatedNotification() {
        String subject = "Coupon Activated";
        String message = "You have successfully activated your monthly coupon.";
        sendEmail(emailAddress, subject, message);
    }

    private void sendEmail(String to, String subject, String message) {
        final String username = emailAddress;
        final String password = "realgmailpassword";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(emailAddress));
            mimeMessage.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            Transport.send(mimeMessage);

            System.out.println("Sent email successfully");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
