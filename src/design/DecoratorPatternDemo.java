package design;

public class DecoratorPatternDemo {
    public static void main(String[] args) {
        Shape circle =  new Circle();
        ShapeDecorator redShapeDecorator = new RedShapeDecorator(circle); // 给shape添加红色
        BoldShapeDecorator boldShapeDecorator = new BoldShapeDecorator(redShapeDecorator); //给shape添加粗边
        boldShapeDecorator.draw();
    }
}

// 定义接口
interface Shape{
    void draw();
}

// 定义接口的具体实现，后面需要用装饰器来扩展其功能
class Circle implements Shape{

    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}

class RectAngle implements Shape{

    @Override
    public void draw() {
        System.out.println("Shape: RectAngle");
    }
}

// 创建Shape的抽象基础装饰器
abstract class ShapeDecorator implements Shape{
    protected Shape decoratorShape;
    public ShapeDecorator(Shape decoratorShape){
        this.decoratorShape = decoratorShape;
    }
    public void draw(){
        decoratorShape.draw();
    }
}

// 扩展基础装饰器的具体装饰类
class RedShapeDecorator extends ShapeDecorator{
    public RedShapeDecorator(Shape decoratorShape) {
        super(decoratorShape);
    }

    @Override
    public void draw() {
        decoratorShape.draw();
        setRedShapeDecorator(decoratorShape);

    }
    private void setRedShapeDecorator(Shape decoratorShape){
        System.out.println("Border color: Red");
    }
}

class BoldShapeDecorator extends ShapeDecorator{
    public BoldShapeDecorator(Shape decoratorShape) {
        super(decoratorShape);
    }

    @Override
    public void draw() {
        decoratorShape.draw();
        setBoldShapeDecorator(decoratorShape);

    }
    private void setBoldShapeDecorator(Shape decoratorShape){
        System.out.println("Border size: Bold");
    }
}