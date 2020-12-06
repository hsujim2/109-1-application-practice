import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
class P36{
    public static void main(String[] args){
        try {
            //BufferedReader br = new BufferedReader(new FileReader("D:/onedrive/文件/大學/三年級/應用軟體實習/java/HW5/P36/test1.txt"));
            //vs code預設目錄並不是在專案目錄，因此要加上完整路徑
            BufferedReader br = new BufferedReader(new FileReader("test1.txt"));
            String str1 = br.readLine();
            String str2 = br.readLine();

            System.out.println("寫入到檔案中的兩個字串是");
            System.out.println(str1);
            System.out.println(str2);

            br.close();
        } catch (IOException e) {
            System.out.println("輸出入錯誤");
        }
    }
}