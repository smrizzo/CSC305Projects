public class ActionHandler {
    Command command;

    public ActionHandler() {}

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeAction() {
        command.execute();
    }
}
