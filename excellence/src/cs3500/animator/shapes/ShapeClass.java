package cs3500.animator.shapes;

public enum ShapeClass {
    RECT("rectangle", "rect"), OVAL("oval", "ellipse"), TRIANGLE("triangle", "polygon");

    String text;
    String svg;

    ShapeClass(String text, String svg) {
        this.text = text;
        this.svg = svg;
    }

    String textName() {
        return text;
    }

    String svgName() {
        return svg;
    }
}
