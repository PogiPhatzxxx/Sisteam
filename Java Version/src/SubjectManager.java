import java.util.ArrayList;

public class SubjectManager {
    
    public static ArrayList<String> getSubjectsByCourse(String course) {
        ArrayList<String> subjects = new ArrayList<>();
        subjects.add("Select Subject");
        
        if (course.equals("BSIT")) {
            subjects.add("Data Structures and Algorithms");
            subjects.add("Human-Computer Interaction");
            subjects.add("Object-Oriented Programming");
            subjects.add("P.E./PATHFIT 3: Individual-Dual Sports");
            subjects.add("Platform Technology (Operating Systems)");
            subjects.add("Principles of Communication");
            subjects.add("Readings in Philippine History");
            subjects.add("Rizal's Life and Works");
        } else if (course.equals("BMMA")) {
            subjects.add("BMMA Subject 1");
            subjects.add("BMMA Subject 2");
            subjects.add("BMMA Subject 3");
            subjects.add("BMMA Subject 4");
            subjects.add("BMMA Subject 5");
            subjects.add("BMMA Subject 6");
            subjects.add("BMMA Subject 7");
            subjects.add("BMMA Subject 8");
        } else if (course.equals("BSIS")) {
            subjects.add("BSIS Subject 1");
            subjects.add("BSIS Subject 2");
            subjects.add("BSIS Subject 3");
            subjects.add("BSIS Subject 4");
            subjects.add("BSIS Subject 5");
            subjects.add("BSIS Subject 6");
            subjects.add("BSIS Subject 7");
            subjects.add("BSIS Subject 8");
        } else if (course.equals("BSBA")) {
            subjects.add("BSBA Subject 1");
            subjects.add("BSBA Subject 2");
            subjects.add("BSBA Subject 3");
            subjects.add("BSBA Subject 4");
            subjects.add("BSBA Subject 5");
            subjects.add("BSBA Subject 6");
            subjects.add("BSBA Subject 7");
            subjects.add("BSBA Subject 8");
        } else if (course.equals("BSA")) {
            subjects.add("BSA Subject 1");
            subjects.add("BSA Subject 2");
            subjects.add("BSA Subject 3");
            subjects.add("BSA Subject 4");
            subjects.add("BSA Subject 5");
            subjects.add("BSA Subject 6");
            subjects.add("BSA Subject 7");
            subjects.add("BSA Subject 8");
        } else if (course.equals("BSTM")) {
            subjects.add("BSTM Subject 1");
            subjects.add("BSTM Subject 2");
            subjects.add("BSTM Subject 3");
            subjects.add("BSTM Subject 4");
            subjects.add("BSTM Subject 5");
            subjects.add("BSTM Subject 6");
            subjects.add("BSTM Subject 7");
            subjects.add("BSTM Subject 8");
        } else if (course.equals("BSHM")) {
            subjects.add("BSHM Subject 1");
            subjects.add("BSHM Subject 2");
            subjects.add("BSHM Subject 3");
            subjects.add("BSHM Subject 4");
            subjects.add("BSHM Subject 5");
            subjects.add("BSHM Subject 6");
            subjects.add("BSHM Subject 7");
            subjects.add("BSHM Subject 8");
        }
        
        return subjects;
    }
}