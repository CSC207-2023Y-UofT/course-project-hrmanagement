import UI.PositionBox;

public class HRManagement {

    static void viewPositionBox(){
        PositionBox positionBox = new PositionBox();
        positionBox.createChoosePositionBox();
    }

    public static void main(String[] args) {
        viewPositionBox();
    }
}
