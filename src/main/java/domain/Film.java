package domain;

import java.util.List;

public class Film {
private String name;
private int id;
private List<Comment> comments;
private int comcounter=0;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id=id;
}
public void setName(String name) {
	this.name = name;
}
public String getName() {
	return name;
}
public List<Comment> getComments(){
	return comments;
}
public void setComments(List<Comment> comments) {
	this.comments = comments;
}

public int getComcounter() {
	return comcounter;
}
public void setComcounter(int comcounter) {
	this.comcounter=comcounter;
}

}
