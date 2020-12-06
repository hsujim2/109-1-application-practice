import jdk.vm.ci.code.site.Site;
class P48{
    public static void main(String[] args) {
        Car car1 = new Car("1號車");
        car1.start();
        try {
            car1.join();
        } catch (Exception e) {
            //TODO: handle exception
        }
        System.out.println("結束main()的處裡工作");
    }
}

class Car extends Thread{
    private String name;
    public Car(String nm){
        name = nm;
    }
    public void run(){
        for(int i=0;i<5;i++){
            sleep(1000);
            System.out.println("正在進行"+name+"的處裡工作");
        }
    }
}