package classify.spam.dexter.firebase_auth_spam_detect;

/**
 * Created by Prashant on 10-10-2017.
 */

public class SpamClassify {


    boolean classify(String message)
    {

        if(message.contains("spam"))

        {
            return true;
        }
        else
        {
            return false;
        }
    }


}
