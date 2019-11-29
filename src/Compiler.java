import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Compiler {

    public boolean compile(File file){
        Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (Exception e){
            System.out.println("Please select a valid file.");
            return false;
        }
        String code = "";
        while(input.hasNext()){
            code += input.next();
        }
        String readyCode = "";
        for(int i = 0; i < code.length(); i++){
            if(code.charAt(i) == '<' || code.charAt(i) == '>' || code.charAt(i) == '[' || code.charAt(i) == ']' || code.charAt(i) == '-' || code.charAt(i) == '+' || code.charAt(i) == '.' || code.charAt(i) == ','){
                readyCode += code.charAt(i);
            }
        }
        input.close();
        File output = new File("C:\\Users\\Karam\\Desktop\\Brainfuck\\res\\output.bf");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(output);
            fileWriter.write(readyCode);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try{
                fileWriter.close();
                return true;
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return true;
    }

    public int[] run(File file){
        ArrayList<Integer> bracketIndex = new ArrayList<>();
        int[] arr = new int[10];
        for(int i = 0; i < arr.length; i++)
            arr[i] = 0;
        int arrIndex = 0;
        Scanner input = null;
        try{
            input = new Scanner(file);
        } catch (Exception e){
            e.printStackTrace();
        }
        String code = "";
        while(input.hasNext()){
            code += input.next();
        }
        System.out.println(code);
        for(int i = 0; i < code.length(); i++){
            char symbol = code.charAt(i);
            if(symbol == '<' && arrIndex > 0)
                arrIndex--;
            else if(symbol == '>' && arrIndex < arr.length)
                arrIndex++;
            else if(symbol == '+')
                arr[arrIndex]++;
            else if(symbol == '-')
                arr[arrIndex]--;
            else if(symbol == '.')
                System.out.print((char)arr[arrIndex]);
            else if(symbol == '['){
                if(arr[arrIndex] != 0){
                    bracketIndex.add(i);

                } else {
                    int brackets = 1;

                    while(brackets != 0){
                        i++;
                        if(code.charAt(i) == '[')
                            brackets++;
                        else if(code.charAt(i) == ']')
                            brackets--;

                    }
                }
            } else if(symbol == ']'){
                if(arr[arrIndex] == 0){
                    bracketIndex.remove(bracketIndex.size()-1);

                } else {
                    i = bracketIndex.get(bracketIndex.size()-1);
                }
            }
        }
        return arr;
    }
}
