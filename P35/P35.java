import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
class P35{
    public static void main(String[] args){
        try {
            //FileWriter fw = new FileWriter("D:/onedrive/文件/大學/三年級/應用軟體實習/java/HW5/P35/test1.txt");
            //vs code預設輸出目錄並不是在專案目錄，因此要加上完整路徑
            FileWriter fw = new FileWriter("test1.txt");
            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

            pw.println("Hello!");
            pw.println("GoodBye!");

            pw.close();
            System.out.println("資料已經寫入檔案了");
        } catch (IOException e) {
            System.out.println("輸出入錯誤");
        }
    }
}