package org.example;

public class DropShadowDecorator extends ShapeDecorator{
    //jej celem jest udekorowanie obiektu Shape rzucanym cieniem
    private static int indexCounter = 0;
    private int index;
    private SvgScene svgScene;
    
    public DropShadowDecorator(Shape decoratedShape, SvgScene svgScene) {
        super(decoratedShape);
        this.index = ++indexCounter;
        this.svgScene = svgScene;
        String defs = String.format("\\t<filter id=\\\"f%d\\\" x=\\\"-100%%\\\" y=\\\"-100%%\\\" width=\\\"300%%\\\" height=\\\"300%%\\\">\\n\" +\n" +
                "\"\\t\\t<feOffset result=\\\"offOut\\\" in=\\\"SourceAlpha\\\" dx=\\\"5\\\" dy=\\\"5\\\" />\\n\" +\n" +
                "\"\\t\\t<feGaussianBlur result=\\\"blurOut\\\" in=\\\"offOut\\\" stdDeviation=\\\"5\\\" />\\n\" +\n" +
                "\"\\t\\t<feBlend in=\\\"SourceGraphic\\\" in2=\\\"blurOut\\\" mode=\\\"normal\\\" />\\n\" +\n" +
                "\"\\t</filter>\"", index);
        svgScene.addDefs(defs);
    }

    @Override
    public String toSvg(String tag) {
        String filterTag = String.format("filter=\"url(#f%d)\" ", index);
        return super.toSvg(filterTag + tag);
    }
}
