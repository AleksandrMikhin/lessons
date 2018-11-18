package lesson12;

import java.util.Objects;

public class User implements Comparable<User>{
    public int id;
    public String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name);
    }

    public int hashCodwwe() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }



    @Override
    public int compareTo(User o) {
        return name.length() - o.name.length();
    }
}
