package classify.spam.dexter.firebase_auth_spam_detect;

/**
 * Created by Prashant on 11-10-2017.
 */

public class MailSent {

    public String to,message,subject;

    public MailSent(String to, String message, String subject) {
        this.to = to;
        this.message = message;
        this.subject = subject;
    }

    public MailSent()
    {

    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "MailSent{" +
                "to='" + to + '\'' +
                ", message='" + message + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
