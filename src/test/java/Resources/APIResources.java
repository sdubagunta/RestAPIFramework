package Resources;

public enum APIResources {
    addPlaceAPI ("/maps/api/place/add/json"),
    getPlaceAPI ("/maps/api/place/get/json"),
    deletePlaceAPI ("/maps/api/place/delete/json");

   public String resource;

    APIResources(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
