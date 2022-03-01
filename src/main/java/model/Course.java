package model;

import java.util.List;
import java.util.Map;
public class Course {
    String tees;
}
 class Analysis {
    Course name;
    Map<Integer, Hole> holes;
}
 class Hole {
    List<Event> events;
}

class Event {
     EventType s;
     String tag;
}
enum EventType {
    VERY_POSITIVE,
    POSITIVE,
    NEGATIVE,
    VERY_NEGATIVE
}
