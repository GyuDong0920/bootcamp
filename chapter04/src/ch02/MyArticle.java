package ch02;

// 호출자
public class MyArticle {

	String article;
	WriteAticle onWriteAticle;
	
	// 주소값 연결 : 생성자에서 연결 하는 방법.
	public MyArticle(String artcle, WriteAticle onWriteAticle ) {
		this.article = artcle;
		this.onWriteAticle = onWriteAticle;
	}
	
	public void complete() {
		onWriteAticle.printArticle(article);
	}
	
}
