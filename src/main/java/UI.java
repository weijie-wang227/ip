public class UI {
    public void welcomeMessage() {
        System.out.println("--------------------------------\n" +
                "Hello! I'm YapperBot\n" +
                "What can I do for you?\n" +
                "--------------------------------");
    }

    public void byeMessage() {
        System.out.println("--------------------------------\n" +
                "Bye. Hope to see you again soon!\n" +
                "--------------------------------");
    }

    public void showError (RuntimeException e) {
        System.out.println(e.getMessage());
    }
}
