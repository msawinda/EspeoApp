package pl.sawinda.espeoapp.contact;

public interface ContactContract {

    public interface View {

    }

    public interface Actions {
        public void phoneCall();
        public void addContact();
        public void sendEmail();
        public void openLinkedin();
    }
}
