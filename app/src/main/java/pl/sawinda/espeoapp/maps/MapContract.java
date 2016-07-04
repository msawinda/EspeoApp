package pl.sawinda.espeoapp.maps;

public interface MapContract {

    public interface View {
        public void setDistance(String distance);
    }

    public interface Actions {
        public void calculateDistance();
        public void setUserPosition();
        public void initiateListeners();
        public void setLocationPosition();
        public void zoomAllMarkers();
    }
}
