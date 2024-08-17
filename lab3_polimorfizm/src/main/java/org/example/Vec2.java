package org.example;

class Vec2 {
    public final float x;
    public final float y;

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
class Segment {
    private Vec2 vec21;
    private Vec2 vec22;
    //akcesory to to samo co gettery a mutatory to to samo co settery

    public Vec2 getPoint1() {
        return vec21;
    }

    public Vec2 getPoint2() {
        return vec22;
    }

    public float segmentSize (){
        return (float) Math.sqrt((Math.pow(vec22.x - vec21.x, 2) + Math.pow(vec22.y - vec21.y, 2)));
    }

    public Segment(Vec2 vec21, Vec2 vec22) {
        this.vec21 = vec21;
        this.vec22 = vec22;
    }
    public String toSvg() {
        return String.format("<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" stroke=\"black\" />",
                vec21.x, vec21.y, vec22.x, vec22.y);
    }
    public static Segment[] perpendicular(Vec2 vec2, Segment segment){
        // a = segment.y2 - segment.y1 / segment.x2 - segment.x1
        // vec2.y = aP * vec2.x + bP bP=?
        // z = segment.length
        // z^2 = xOffset^2 + yOffset^2
        // yOffset = pA * xOffset
        //xOffsey = sqrt(length^2/1 + pA^2
        //endVec21=(vec2.x+xOffset,vec2.y+yOffset)
        //endVec22=(vec2.x−xOffset,vec2.y−yOffset)
        float a = (segment.getPoint2().y - segment.getPoint1().y)/(segment.getPoint2().x - segment.getPoint1().x);
        float aP = a * (-1/a);
        float length = segment.segmentSize();
        float xOffset = (float) Math.sqrt(Math.pow(length,2)/(1 + Math.pow(aP,2)));
        float yOffset = aP * xOffset;
        Vec2 endVec21 =new Vec2(vec2.x+xOffset, vec2.y+yOffset);
        Vec2 endVec22 = new Vec2(vec2.x - xOffset, vec2.y - yOffset);
        Segment pSegment1 = new Segment(vec2, endVec21);
        Segment pSegment2 = new Segment(vec2, endVec22);
        return new Segment[] {pSegment1, pSegment2};
    }
    public static Segment[] perpendicular(Vec2 vec2, Segment segment, Float length){

        float a = (segment.getPoint2().y - segment.getPoint1().y)/(segment.getPoint2().x - segment.getPoint1().x);
        float aP = a * (-1/a);
        float xOffset = (float) Math.sqrt(Math.pow(length,2)/(1 + Math.pow(aP,2)));
        float yOffset = aP * xOffset;
        Vec2 endVec21 =new Vec2(vec2.x+xOffset, vec2.y+yOffset);
        Vec2 endVec22 = new Vec2(vec2.x - xOffset, vec2.y - yOffset);
        Segment pSegment1 = new Segment(vec2, endVec21);
        Segment pSegment2 = new Segment(vec2, endVec22);
        return new Segment[] {pSegment1, pSegment2};
    }
}
