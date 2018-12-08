public class CCMove implements Command {
    private StarModel model;

    public CCMove(StarModel model) {
        this.model = model;
    }


    @Override
    public void execute() {
        model.regularMove();
    }

    @Override
    public void undo() {
        model.undoLastMove();
    }
}
