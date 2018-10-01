package classify.spam.dexter.firebase_auth_spam_detect;

/**
 * Created by Prashant on 07-10-2017.
 */

public class Mail {
public String from,message,subject,to;

    public Mail(String from, String message, String subject,String to) {
        this.from = from;
        this.message = message;
        this.subject = subject;
        this.to=to;
    }
    public Mail()
    {

    }

    public String getTo() {return to;}

    public String getFrom() {
        return from;
    }

    public void setTo(String to) {this.to=to;}

    public void setFrom(String from) {
        this.from = from;
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
        return "Mail{" +
                "from='" + from + '\'' +
                ", message='" + message + '\'' +
                ", subject='" + subject + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
