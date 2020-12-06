import jdk.vm.ci.code.site.Site;
class P45{
    public static void main(String[] args) {
        Car car1 = new Car("1號車");
        car1.start();

        Car car2 = new Car("2號車");
        car2.start();
        //不知為何我的看不出結果
        //我將i調整成500才能看出每次都不一樣的結果
        //可能跟電腦執行的速度有關
        for(int i=0;i<5;i++){
            System.out.println("正在進行main()的處裡工作");
        }
    }
}

class Car extends Thread{
    private String name;
    public Car(String nm){
        name = nm;
    }
    public void run(){
        for(int i=0;i<5;i++){
            System.out.println("正在進行"+name+"的處裡工作");
        }
    }
}