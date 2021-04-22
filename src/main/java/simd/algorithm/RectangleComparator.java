package simd.algorithm;

import models.RectangleShape;

public class RectangleComparator extends Comparator<RectangleShape> {
    public RectangleShape exec(RectangleShape rectangle, RectangleShape board) {
        if (board == null) {
            return new RectangleShape(rectangle.x, rectangle.y, rectangle.width, rectangle.height, rectangle.colorShape);
        }
        float x = Math.min(board.x, rectangle.x);
        float y = Math.min(board.y, rectangle.y);
        float width = board.x + board.width > rectangle.x + rectangle.width ? (board.x + board.width) - x : (rectangle.x + rectangle.width) - x;
        float height = board.y + board.height > rectangle.y + rectangle.height ? (board.y + board.height) - y : (rectangle.y + rectangle.height) - y;
        return new RectangleShape(x, y, width, height, board.colorShape);
    }
}
