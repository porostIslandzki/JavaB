package org.example;

public class TransformationDecorator implements Shape{
    private boolean translate;
    private Vec2 translateVector;
    private boolean rotate;
    private double rotateAngle;
    private Vec2 rotateCenter;
    private boolean scale;
    private Vec2 scaleVector;
    Shape shape;

    public TransformationDecorator(Shape shape) {
        this.shape = shape;
    }

    public static class Builder{
        private boolean translate = false;
        private Vec2 translateVector;
        private boolean rotate = false;
        private double rotateAngle;
        private Vec2 rotateCenter;
        private boolean scale = false;
        private Vec2 scaleVector;
        Shape shape;

        public Builder setTranslate(Vec2 vec2) {
            this.translate = true;
            this.translateVector = translateVector;
            return this;
        }

        public Builder setTranslateVector(Vec2 translateVector) {
            this.translate = true;
            this.translateVector = translateVector;
            return this;

        }

        public Builder setRotate(double rotateAngle, Vec2 rotateCenter) {
            this.rotate = true;
            this.rotateAngle = rotateAngle;
            this.rotateCenter = rotateCenter;
            return this;
        }

        public Builder setScale() {
            this.scale = true;
            this.scaleVector = scaleVector;
            return this;
        }

        public Builder setShape(Shape shape) {
            this.shape = shape;
            return this;
        }

        public TransformationDecorator build() {
            TransformationDecorator decorator = new TransformationDecorator(this.shape);
            decorator.translate = this.translate;
            decorator.translateVector = this.translateVector;
            decorator.rotate = this.rotate;
            decorator.rotateAngle = this.rotateAngle;
            decorator.rotateCenter = this.rotateCenter;
            decorator.scale = this.scale;
            decorator.scaleVector = this.scaleVector;
            return decorator;
        }

    }
    @Override
    public String toSvg(String tag) {
        StringBuilder transform = new StringBuilder();

        if (translate && translateVector != null) {
            transform.append(String.format("translate(%f %f) ", translateVector.x, translateVector.y));
        }
        if (rotate && rotateCenter != null) {
            transform.append(String.format("rotate(%f %f %f) ", rotateAngle, rotateCenter.x, rotateCenter.y));
        }
        if (scale && scaleVector != null) {
            transform.append(String.format("scale(%f %f) ", scaleVector.x, scaleVector.y));
        }

        // Dodanie atrybutu transform i wywołanie toSvg na dekorowanym kształcie
        return shape.toSvg(String.format("transform=\"%s\" %s", transform.toString().trim(), tag));
    }
}
