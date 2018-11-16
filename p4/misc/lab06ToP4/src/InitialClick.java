public class InitialClick implements Command {
    private StarModel model;
    private int x;
    private int y;

    public InitialClick(StarModel model, int x, int y) {
        this.model = model;
    }

    @Override
    public void execute() {

    }

    @Override
    public void undo() {
        //undo something
    }
}
