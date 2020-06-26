package ch.supsi.teencpu.presentation;


public class InputIntegerPresenter implements Presentable<String> {

    @Override
    public String present(String input) {
        StringBuilder toReturn = new StringBuilder();
        for (char ch : input.toCharArray()) {
            toReturn.append(ch).append("(").append((int) ch).append(")").append(" ");
        }
        return toReturn.toString();
    }

}
