import java.util.ArrayList;
import java.util.List;

public class OutputLineDTO {
    String word;
    List<Integer> lineNumbers;

    public OutputLineDTO(String word) {
        this.word = word;
        this.lineNumbers = new ArrayList<>();
    }

    public boolean checkWord(String word){
       return this.word.equals(word);
   }

   public OutputLineDTO updateLineNumber(List<Integer> listOfLineNumbers){
       this.lineNumbers = listOfLineNumbers;
       return this;
   }

    @Override
    public String toString() {
        return  word + " - " + lineNumbers.size() + " - pozycje -> " + lineNumbers.toString();
    }


}
