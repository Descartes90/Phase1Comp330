import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question {

        public static final String questionsFile = "questions.json";

  private String displayQuestion;
  private List<String> answerOptions;
  private String correctAnswer;
  private QuestionType type;


  public Question(String displayQuestion, List<String> answerOptions, 
      String correctAnswer, QuestionType type) {
    this.displayQuestion = displayQuestion;
    this.answerOptions = answerOptions;
    this.correctAnswer = correctAnswer;
    this.type = type;
  }

  public Question(QuestionType type, String displayQuestion, String correctAnswer) {


    switch(type) {
      case TRUE_FALSE:
        this.answerOptions = Arrays.asList(new String[] {"True","False"});
        break;
      case FILL_IN_BLANK:
      case MULTIPLE_CHOICE:
    	  this.answerOptions = Arrays.asList(new String[] {"Int", "Double", "Short", "String"});
    	  break;
      case MATCHING:
            this.answerOptions = new ArrayList<String>();
        break;

    }

    this.displayQuestion = displayQuestion;
    this.correctAnswer = correctAnswer;
    this.type = type;


  }
  
  public String getDisplayQuestion() {
    return displayQuestion;
  }

  public List<String> getAnswerOptions() {
    return answerOptions;
  }

  public String getCorrectAnswer() {
    return correctAnswer;
  }

  public QuestionType getType() {
    return this.type;
  }




} 
