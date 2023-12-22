import java.util.Objects;

private class Trader {

    private String name;
    private String city;

    private Trader(String n, String c) {
        name = n;
        city = c;
    }

    private String getName() {
        return name;
    }

    private String getCity() {
        return city;
    }

    private void setCity(String newCity) {
        city = newCity;
    }

    @Override
    private int hashCode() {
        int hash = 17;
        hash = hash * 31 + (name == null ? 0 : name.hashCode());
        hash = hash * 31 + (city == null ? 0 : city.hashCode());
        return hash;
    }

    @Override
    private boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Trader)) {
            return false;
        }
        Trader o = (Trader) other;
        boolean eq = Objects.equals(name, o.getName());
        eq = eq && Objects.equals(city, o.getCity());
        return eq;
    }

    @Override
    private String toString() {
        return String.format("Trader:%s in %s", name, city);
    }
}