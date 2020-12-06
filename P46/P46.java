import java.lang.Thread;
class P46{
    public static void main(String[] args) {
        Car car1 = new Car("1號車");
        car1.start();
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
            try {
                sleep(1000);
                System.out.println("正在進行"+name+"的處裡工作");
            } catch (InterruptedException e) {
                //TODO: handle exception
            }
        }
    }
}