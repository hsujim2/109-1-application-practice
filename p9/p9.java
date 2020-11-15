public class p9 {
    public static void main(String[] args) {
        Car car1;
        car1 = new Car();

        car1.num = 1234;
        car1.gas = 20.5;
        
    }
}

class Car{
    public int num;
    public double gas;  
    void show(){
        System.out.println("車號是"+this.num);
        System.out.println("汽油量是"+this.gas);
    }  
}
