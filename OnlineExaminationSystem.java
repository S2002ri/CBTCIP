import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {
    private String questionText;
    private List<String> options;
    private int correctOption;

    public Question(String questionText, List<String> options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

public class OnlineExaminationSystem {
    private static List<Question> questions = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    private static User loggedInUser;
    private static boolean sessionOpen = false;

    public static void main(String[] args) {
        initializeQuestions();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    register(scanner);
                    break;
                case 3:
                    System.out.println("Exiting the system.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please select a valid option!");
            }
        }
    }

    public static void initializeQuestions() {
        List<String> options1 = new ArrayList<>();
        options1.add("Guido van Rossum");
        options1.add("James Gosling");
        options1.add("Dennis Ritchie");
        options1.add("Bjarne Stroustrup");

        List<String> options2 = new ArrayList<>();
        options2.add("def");
        options2.add("define");
        options2.add("fn");
        options2.add("function");

        List<String> options3 = new ArrayList<>();
        options3.add("Object-oriented");
        options3.add("Functional");
        options3.add("Procedural");
        options3.add("All of the above");

        List<String> options4 = new ArrayList<>();
        options4.add(".py");
        options4.add(".txt");
        options4.add(".class");
        options4.add(".java");

        List<String> options5 = new ArrayList<>();
        options5.add("Polymorphism");
        options5.add("Inheritance");
        options5.add("Indentation");
        options5.add("Encapsulation");

        Question question1 = new Question("Who created the Python programming language?", options1, 0);
        Question question2 = new Question("Which keyword is used to define a function in Python?", options2, 0);
        Question question3 = new Question("What type of programming language is Python?", options3, 3);
        Question question4 = new Question("What is the extension of Python code files?", options4, 0);
        Question question5 = new Question("Which of the following is not a feature of Python?", options5, 1);

        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);
    }

    public static void register(Scanner scanner) {
        System.out.print("Enter a Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter a Password: ");
        String password = scanner.nextLine();

        // Check if the username is already taken
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username already taken. Please choose another one.");
                return;
            }
        }

        User newUser = new User(username, password);
        users.add(newUser);
        System.out.println("Registration successful! You can now log in.");
    }

    public static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Validate credentials
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                sessionOpen = true;
                startExam(scanner);
                return;
            }
        }

        System.out.println("Invalid credentials. Please try again!");
    }

    public static void startExam(Scanner scanner) {
        System.out.println("\nWelcome, " + loggedInUser.getUsername() + "!");
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getQuestionText());
            for (int j = 0; j < question.getOptions().size(); j++) {
                System.out.println((j + 1) + ". " + question.getOptions().get(j));
            }

            System.out.print("Select your answer (1-" + question.getOptions().size() + "): ");
            int userChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (userChoice == question.getCorrectOption() + 1) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect!");
            }
        }

        System.out.println("Exam completed! Your score is: " + score + "/" + questions.size());
        sessionOpen = false;
    }
}