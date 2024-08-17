package org.example;

public class ShapeDecorator implements Shape{
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    @Override
    public String toSvg(String tag) {
        //wywołanie metody toSvg() na dekorowanym obiekcie
        return decoratedShape.toSvg(tag);
    }
}
//interfejsy w Javie nie mają bezpośredniego
//mechanizmu wywoływania metod domyślnych na poziomie 'super'