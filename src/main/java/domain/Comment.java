package domain;

public class Comment {
	private String text;
	private int id;
	private float score;

public void setText(String text) {
	this.text=text;
}
public String getText() {
	return text;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id=id;
}
public void setScore(float score) {
	this.score=score;
}
public float getScore() {
	return score;
}
	
}
