package org.example.model.member;

public class Group {
    private int id;
    private String groupName;

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public Group(int id) {
        this.id = id;
    }

    public Group(int id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }

    public Group() {
    }

    public String getGroupName() {
        return groupName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
