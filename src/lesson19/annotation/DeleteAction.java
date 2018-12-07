package lesson19.annotation;

//аннотация лежит как комментарий, но можно ее использовать
//класс могут использовать только пользователи ADMIN
@PermissionRequired(User.Permission.ADMIN)
public class DeleteAction {
    public void delete(User user) {
        System.out.println("del user");
    }
}
