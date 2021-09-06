package design.factory;

public class FactoryMethodPattern {
    public static void main(String[] args) {
        Dialog windowsDialog = new WindowsDialog();
        Dialog htmlDialog = new HtmlDialog();
        windowsDialog.renderWindow();
        htmlDialog.renderWindow();
    }
}

// 1. 产品接口
interface Button{
    void render();
}

// 2. 具体产品
class  WindowsButton implements Button{

    @Override
    public void render() {
        System.out.println("this is a windows button");
    }
}

class HtmlButton implements Button{

    @Override
    public void render() {
        System.out.println("this is a web button");
    }
}

// 3. 定义基础工厂，抽象类，除了有创建具体工厂的方法，
//也可以有其他方法，即其他业务
abstract  class Dialog{
    void renderWindow(){
        Button button = createButton();
        button.render();
    }
    abstract  Button createButton();
}

// 4. 具体工厂,用来生产具体的产品
class WindowsDialog extends Dialog{

    @Override
    Button createButton() {
        return new WindowsButton();
    }
}

class HtmlDialog extends Dialog{

    @Override
    Button createButton() {
        return new HtmlButton();
    }
}