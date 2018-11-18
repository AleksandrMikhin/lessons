package lesson13;

import java.util.Arrays;

public class EnumExample {
    public static void main(String[] args) {
        User user = new User(1,"User1", Role.ADMIN );
        System.out.println(user);

        Role role = Role.GUEST;
        User user2 = new User(1,"User2", role );

//        Role.values() - возвращает массиы всех значений
        System.out.println(Arrays.toString(Role.values()));

        String str = "USER";
        Role role2 = Role.valueOf(str);

        for (Role roleTemp : Role.values()){
            System.out.println(roleTemp.toString());
        }
    }
}

class User{
    private int id;
    private String name;
    private Role role;

    public User(int id, String name, Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}

enum Role{
    ADMIN, USER, GUEST;

    public Role someRole(){
        return USER;
    }
}
