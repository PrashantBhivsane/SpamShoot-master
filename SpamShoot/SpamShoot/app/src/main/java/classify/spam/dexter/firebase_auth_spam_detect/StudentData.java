package classify.spam.dexter.firebase_auth_spam_detect;

/**
 * Created by Prashant on 08-10-2017.
 */

public class StudentData {

    public String cname,lname,name,sname;

    public StudentData(String cname, String lname, String name, String sname) {
        this.cname = cname;
        this.lname = lname;
        this.name = name;
        this.sname = sname;
    }

    @Override
    public String toString() {
        return "StudentData{" +
                "cname='" + cname + '\'' +
                ", lname='" + lname + '\'' +
                ", name='" + name + '\'' +
                ", sname='" + sname + '\'' +
                '}';
    }

    public StudentData()
    {}

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
}
