import java.io.*;
public class TagChecking {

    public static void main(String[] args){
        String filename = args[0];
        StringStackImpl<String> stack = new StringStackImpl<>();
        try{
          BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            line = reader.readLine(); //skip first line
          while (line!=null){
              for (int i =0; i<line.length();i++){
                  if (line.charAt(i)=='<' && line.charAt(i+1)!='/'){
                      String tag = getTag(line,i);
                      if (tag.equals("br") || tag.equals("img")){
                          continue;
                      }
                      stack.push(getTag(line,i));
                  }else if (line.charAt(i)=='<' && line.charAt(i+1)=='/'){
                      String tag = getTag(line,i+1);
                      if (stack.isEmpty()){
                          System.out.println("Error: Tag mismatch "+tag);
                          return;
                      }
                      if (stack.peek().equals(tag)){
                          stack.pop();
                      }else{
                          System.out.println("Error: Expected </"+stack.peek()+"> found </"+tag+">");
                          return;
                      }
                  }
              }
            line = reader.readLine();
          }
            if (!stack.isEmpty()){
                System.out.println("Error: Missing closing tag </"+stack.peek()+">");
            }else{
                System.out.println("Tags are correct!");
            }

        }catch (FileNotFoundException e){
            System.err.println("File not found!");
        } catch (IOException e) {
            System.err.println("Error reading file!");
        }
    }

     static String getTag(String line, int i){
            int j = i+1;
            StringBuilder result = new StringBuilder();
            while (line.charAt(j)!='>'){
                result.append(line.charAt(j));
                j++;
            }
            return result.toString();
        }
}