import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;


public class Main {
  public static void main(String[] args) throws IOException{

    int[] mem = new int[1000];
    int programPointer = 0;
    int dataPointer = 0;

    String source = readFile("SOURCE.txt", StandardCharsets.UTF_8);
    char[] charArr = source.toCharArray();

    String arqIF = readFile("IF.txt", StandardCharsets.UTF_8);
    int ifCounter = 0;

    String arqOF = "";

    while( charArr[programPointer] != '$' ){
      
      if ( charArr[programPointer] == '>' ) dataPointer++;
      else if ( charArr[programPointer] == '<' ) dataPointer--;
      else if ( charArr[programPointer] == '+' ) mem[dataPointer]++;
      else if ( charArr[programPointer] == '-' ) mem[dataPointer]--;
      else if ( charArr[programPointer] == '[' ) {
        if( mem[dataPointer] == 0 ){
          int corr = 0;
          while( charArr[programPointer] != ']' || corr > 0) {
            if( corr > 0 && charArr[programPointer] == ']' ) corr--;
            if( charArr[programPointer] == '[' ) corr++; 
            programPointer++;
          }
        }
      }
      else if ( charArr[programPointer] == ']' ) {
        if( mem[dataPointer] != 0 ){
          while( charArr[programPointer] != '[' ) programPointer--;
          continue;
        }
      }
      else if ( charArr[programPointer] == ',' ){
        mem[ifCounter] = arqIF.charAt(ifCounter);
        ifCounter++;
      }
      else if ( charArr[programPointer] == '.' ){
        arqOF += (char)mem[dataPointer];
      }

      programPointer++;

    }

    FileWriter fw = new FileWriter(new File("OF.txt"));
    BufferedWriter bw = new BufferedWriter(fw);
    bw.write(arqOF + "\n" + Arrays.toString(mem));
    bw.close();
    fw.close();
  }

  

  static String readFile(String path, Charset encoding) throws IOException 
  {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return encoding.decode(ByteBuffer.wrap(encoded)).toString();
  }

}