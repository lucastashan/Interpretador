import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Main {

  private static int[] mem;
  private static int programPointer, dataPointer;

  public static void main(String[] args) throws IOException{

    mem = new int[1000];
    programPointer = dataPointer = 0;

    String source = readFile("SOURCE.txt", StandardCharsets.UTF_8);
    char[] charArr = source.toCharArray();

    while( programPointer < charArr.length ){
      
      if ( charArr[programPointer] == '>' ) dataPointer++;
      if ( charArr[programPointer] == '<' ) dataPointer++;
      if ( charArr[programPointer] == '+' ) mem[dataPointer]++;
      if ( charArr[programPointer] == '-' ) mem[dataPointer]--;
      if ( charArr[programPointer] == '[' ) {
        if( mem[dataPointer] == 0 ){
          while( charArr[programPointer] != ']' ) programPointer++;
        }
      }
      if ( charArr[programPointer] == '[' ) {
        if( mem[dataPointer] == 0 ){
          while( charArr[programPointer] != ']' ) programPointer++;
        }
      }
    }
  }

  

  static String readFile(String path, Charset encoding) throws IOException 
  {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return encoding.decode(ByteBuffer.wrap(encoded)).toString();
  }

}