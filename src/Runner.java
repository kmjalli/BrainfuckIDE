import java.io.File;

public class Runner {

    public static void main(String[] args){
        Compiler compiler = new Compiler();
        File file = new File("res/test.txt");
        //System.out.println(compiler.compile(file));
        compiler.compile(file);
        int[] arr = compiler.run(new File("res/output.bf"));
        for(int i = 0; i<arr.length; i++){
            System.out.print(arr[i] + " ");

        }
    }

}
