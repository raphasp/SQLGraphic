package think.sqlgraphic;

public class Lista_entrada {

	private int idImage;
	private String TextTitle;
	private String TextIp;
	
	public Lista_entrada(int idImage_1, String TextTitle_1, String TextIp_1) {
		this.setIdImage(idImage_1);
		this.setTextTitle(TextTitle_1);
		this.setTextIp(TextIp_1);
	}

	public int getIdImage() {
		return idImage;
	}

	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}

	public String getTextTitle() {
		return TextTitle;
	}

	public void setTextTitle(String textTitle) {
		TextTitle = textTitle;
	}

	public String getTextIp() {
		return TextIp;
	}

	public void setTextIp(String textIp) {
		TextIp = textIp;
	}
	


}
