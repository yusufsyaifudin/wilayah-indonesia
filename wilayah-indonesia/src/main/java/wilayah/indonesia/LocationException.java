package wilayah.indonesia;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LocationException extends RuntimeException {

    public LocationException(String message) {
        super(message);
    }
}
