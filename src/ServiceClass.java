import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ServiceClass {

    public static final String FILE_PATH = "zadanie.txt";
    public List<OutputLineDTO> outputLineDTOList = new ArrayList<>();

    public void printWordsAndLineNumbers() {
        try {
            List<String> linesList = Files.readAllLines(Paths.get(FILE_PATH));
            List<String> wordList = getUniqueWordsFromLines(linesList);
            outputLineDTOList = setWordsInDtosList(wordList);
            outputLineDTOList.forEach(dto -> updateLineNumberInDto(dto.word,findLineNumberOfWord(linesList,dto.word)));
            outputLineDTOList.forEach(line -> System.out.println(line.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<OutputLineDTO> setWordsInDtosList(List<String> listOfWords){
        return listOfWords
                .stream()
                .map(OutputLineDTO::new)
                .collect(Collectors.toList());
    }
    public List<String> getUniqueWordsFromLines(List<String> linesList){
        return linesList.stream()
                .map(line -> line.split("\\R|[^A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]"))
                .flatMap(Arrays::stream)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public void updateLineNumberInDto(String word, List<Integer> listOfLinesNumbers) {
        outputLineDTOList.stream()
                .filter(dto -> dto.checkWord(word))
                .findFirst()
                .map(dto -> dto.updateLineNumber(listOfLinesNumbers));
    }

    public List<Integer> findLineNumberOfWord(List<String> linesList, String word){
        return IntStream.range(0, linesList.size())
                .filter(i -> linesList.get(i).contains(word))
                .mapToObj(i -> i + 1)
                .collect(Collectors.toList());
    }
}
