package libs;

public class Colours {
    private static String[] colourList = new String[]{"#6c7ae0", "#49a0d8", "#d353a0", "#ffc527", "#df4c27"};
    private static int currentColour = 0;

    public static String getNext() {
        String colour = colourList[currentColour];
        if (currentColour + 1 > colourList.length - 1) {
            currentColour++;
        } else {
            currentColour = 0;
        }
        return colour;
    }
}
