package org.example;

class Point {
    public final float x;
    public final float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
class Segment {
    private Point point1;
    private Point point2;
    //akcesory to to samo co gettery a mutatory to to samo co settery

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public float segmentSize (){
        return (float) Math.sqrt((Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y -point1.y, 2)));
    }

    public Segment(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }
    public String toSvg() {
        return String.format("<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" stroke=\"black\" />",
                point1.x, point1.y, point2.x, point2.y);
    }
    public static Segment[] perpendicular(Point point, Segment segment){
        // a = segment.y2 - segment.y1 / segment.x2 - segment.x1
        // point.y = aP * point.x + bP bP=?
        // z = segment.length
        // z^2 = xOffset^2 + yOffset^2
        // yOffset = pA * xOffset
        //xOffsey = sqrt(length^2/1 + pA^2
        //endPoint1=(point.x+xOffset,point.y+yOffset)
        //endPoint2=(point.x−xOffset,point.y−yOffset)
        float a = (segment.getPoint2().y - segment.getPoint1().y)/(segment.getPoint2().x - segment.getPoint1().x);
        float aP = a * (-1/a);
        float length = segment.segmentSize();
        float xOffset = (float) Math.sqrt(Math.pow(length,2)/(1 + Math.pow(aP,2)));
        float yOffset = aP * xOffset;
        Point endPoint1=new Point(point.x+xOffset,point.y+yOffset);
        Point endPoint2 = new Point(point.x - xOffset, point.y - yOffset);
        Segment pSegment1 = new Segment(point, endPoint1);
        Segment pSegment2 = new Segment(point, endPoint2);
        return new Segment[] {pSegment1, pSegment2};
    }
    public static Segment[] perpendicular(Point point, Segment segment, Float length){

        float a = (segment.getPoint2().y - segment.getPoint1().y)/(segment.getPoint2().x - segment.getPoint1().x);
        float aP = a * (-1/a);
        float xOffset = (float) Math.sqrt(Math.pow(length,2)/(1 + Math.pow(aP,2)));
        float yOffset = aP * xOffset;
        Point endPoint1=new Point(point.x+xOffset,point.y+yOffset);
        Point endPoint2 = new Point(point.x - xOffset, point.y - yOffset);
        Segment pSegment1 = new Segment(point, endPoint1);
        Segment pSegment2 = new Segment(point, endPoint2);
        return new Segment[] {pSegment1, pSegment2};
    }
}
