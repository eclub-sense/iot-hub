package cz.iot.local;

/**
 * Created by Michal on 16. 7. 2015.
 */
public class Identifier {

    private String UUID;

    public Identifier() {}

    public Identifier(String UUID) {
        this.UUID = UUID;
    }

    public String getID() {
        return this.UUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Identifier that = (Identifier) o;

        return !(UUID != null ? !UUID.equals(that.UUID) : that.UUID != null);
    }

    @Override
    public int hashCode() {
        return UUID != null ? UUID.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Identifier{" +
                "UUID='" + UUID + '\'' +
                '}';
    }
}
