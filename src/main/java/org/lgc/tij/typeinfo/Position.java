package org.lgc.tij.typeinfo;

/**
 * 将Person对象放在Position
 * Created by laigc on 2017/1/1.
 */
public class Position {
    private String title;
    private Person person;

    public Position(String title, Person person) {
        this.title = title;
        this.person = person;
        if (person == null) {
            person = Person.NULL;
        }
    }

    public Position(Person person) {
        this.person = person;
    }

    public Position(String title) {
        this.title = title;
        person = Person.NULL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        if (person == null) {
            person = Person.NULL;
        }
    }

    @Override
    public String toString() {
        return "Position{" +
                "title='" + title + '\'' +
                ", person=" + person +
                '}';
    }
}
