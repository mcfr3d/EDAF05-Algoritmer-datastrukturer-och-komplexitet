
public class Needleman {

	private int gap = Blosum.getValue('*', 'A');
	private int[][] opt;
	private String alignment1;
	private String alignment2;

	public int allignScore(String a, String b) {
		
		opt = new int[a.length() + 1][b.length() + 1];
		opt[0][0] = 0;
		
		for (int i = 1; i <= a.length(); i++) {
			opt[i][0] = i * gap;
		}
		for (int i = 1; i <= b.length(); i++) {
			opt[0][i] = i * gap;
		}
		for (int i = 1; i <= a.length(); i++) {
			for (int j = 1; j <= b.length(); j++) {
				int scoreDiag = opt[i - 1][j - 1] + Blosum.getValue(a.charAt(i-1), b.charAt(j-1));
				int scoreDown = opt[i - 1][j] + gap;
				int scoreLeft = opt[i][j - 1] + gap;

				opt[i][j] = Math.max(Math.max(scoreDiag, scoreLeft), scoreDown);

			}

		}
		allignSequence(a,b);
		return opt[a.length()][b.length()];
	}

	private void allignSequence(String a, String b) {
		int i = a.length();
		int j = b.length();
		StringBuilder alignBuilder1 = new StringBuilder(); 
		StringBuilder alignBuilder2 = new StringBuilder();
		while(i >0 && j >0){
		int score = opt[i][j];
		int scoreDiag = opt[i - 1][j - 1];
		int scoreDown = opt[i - 1][j];
		int scoreLeft = opt[i][j - 1];
		if(score == scoreDiag + Blosum.getValue(a.charAt(i-1), b.charAt(j-1))){
			alignBuilder1.append(a.charAt(i-1));
			alignBuilder2.append(b.charAt(j-1));
			i--;
			j--;
		}
		
		else if(score == scoreDown  + gap){
			alignBuilder1.append(a.charAt(i-1));
			alignBuilder2.append('-');
			i--;
		}
		else if(score == scoreLeft + gap){
			alignBuilder1.append('-');
			alignBuilder2.append(b.charAt(j-1));
			j--;
		}
		
		}
		while (i > 0){
			alignBuilder1.append(a.charAt(i-1));
			alignBuilder2.append('-');
			i--;
		}
		
		while (j > 0){
			alignBuilder1.append('-');
			alignBuilder2.append(b.charAt(j-1));
			j--;
		}
		
		alignment1 = alignBuilder1.reverse().toString();
		alignment2 = alignBuilder2.reverse().toString();

	}

	public String getAlign1(){
		return alignment1;
	}
	public String getAlign2(){
		return alignment2;
	}
	public void clear(){
		opt = null;
		alignment1= null;
		alignment2=null;
				
	}
	
	
}
