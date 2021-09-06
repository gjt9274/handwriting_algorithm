package design.factory;

public class FactoryPattern {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.createShape("CIRCLE");
        shape.draw();
    }
}

// 1. 产品接口
interface Shape{
    void draw();
}

// 2. 定义具体产品
class Circle implements Shape{

    @Override
    public void draw() {
        System.out.println("this is a circle");
    }
}

class Rectangle implements Shape{

    @Override
    public void draw() {
        System.out.println("this is a rectangle");
    }
}

class Square implements Shape{

    @Override
    public void draw() {
        System.out.println("this is a square");
    }
}

// 3. 创建工厂，工厂根据给定信息来创建具体的对象实例
class ShapeFactory{
    Shape createShape(String shapeType){
        if (shapeType == null){
            return null;
        }
        if ("CIRCLE".equals(shapeType)) {
            return new Circle();
        }else if("RECTANGLE".equals(shapeType)){
            return new Rectangle();
        }else if("SQUARE".equals(shapeType)){
            return new Square();
        }
        return null;
    }
}