import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;
class P40{
    public static void main(String[] args){
        //vscode的使用方式不一樣，要在launch.json裡面增加args選項
        if(args.length !=1){
            System.out.println("請指定正確的檔案名稱");
            System.exit(1);
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));

            String str;
            while((str = br.readLine())!=null){
                System.out.println(str);
            }
            br.close();
        } catch (IOException e) {
            //TODO: handle exception
            System.out.println("輸出入錯誤");
        }
    }
}