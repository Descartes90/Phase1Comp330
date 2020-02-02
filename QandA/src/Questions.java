import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Questions {

	public static void main(String[] args) throws IOException {
		Questions question = new Questions();
		question.loadQuestions();
		List <Question> list = question.readQuestions();
		question.displayQuestions(list);

	}
	private void loadQuestions() throws IOException {
		//Create list of questions object
		List <Question> qObjectList = new ArrayList<>();

		//Create question objects
		Question q1 = new Question(QuestionType.FILL_IN_BLANK,"What is the % operator in Java?", "Remainder operator");
		qObjectList.add(q1);

		Question q2 = new Question(QuestionType.TRUE_FALSE,"Is Java an Object-Oriented Language?", "True");
		qObjectList.add(q2);
		
		Question q3 = new Question(QuestionType.MULTIPLE_CHOICE, "Which one of these data types can represent floating point (decimal) numbers?\n----",
				"Double");
		qObjectList.add(q3);
		
		//Save question objects to JSON file
		String json = new Gson().toJson(qObjectList);
		Path path = Paths.get("Questions.json");
		Files.write(path, json.getBytes());
	}

	private List<Question> readQuestions(){
		//Create an empty list of questions
		List<Question> qList = new ArrayList<Question>();

		//Get file path
		Path path = Paths.get("Questions.json");

		//Load in questions from file path
		try(Stream<String> lines = Files.lines(path)){

			List<String> lineList = lines.collect(Collectors.toList());
			Type listType = new TypeToken<List<Question>>(){}.getType();
			qList = new Gson().fromJson(lineList.get(0), listType);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Return list of questions
		return qList;
	}

	private void displayQuestions(List<Question>qList) {
		Scanner keyboard = new Scanner(System.in);
		for(Question question : qList) {
			String userAnswer;
			System.out.println(question.getDisplayQuestion());
			//If statement to print answer options for multiple choice
			if (question.getType() == QuestionType.MULTIPLE_CHOICE) {
				System.out.println("Choose one of the following and enter your answer below: ");
				System.out.println(question.getAnswerOptions());
			}
			//Ask for user input after displayQuestion()
			userAnswer = keyboard.nextLine();
			//Call checkAnswer() method that checks answer against correct answer
			checkAnswer(userAnswer, question.getCorrectAnswer());
			//System.out.println(question.getCorrectAnswer());
		}
		keyboard.close();
	}
	/*Create checkAnswer method, parameters are correctAnswer and userAnswer,
	should return boolean. Should display whether answer is right or wrong.
	*/
	private void checkAnswer(String correctAnswer, String userAnswer) {
		if (userAnswer.equalsIgnoreCase(correctAnswer)) {
			System.out.println("Good Job!");
		}
		else {
			System.out.println("Wrong");
		}
	}

}