
public class domineeringGame
{//created by Justin Schaefer, based on the domino-placing game invented by Goran Andersson.
	public static int instructions()
	{
		StdDraw.clear(StdDraw.BLUE);
		StdDraw.text(0.5,0.9,"Domineering!");
		StdDraw.text(0.5,0.7,"You will take turns placing rectangles that cover two squares.");
		StdDraw.text(0.5,0.65,"Player 1 will place vertical pieces, while player 2 places horizontal pieces.");
		StdDraw.text(0.5,0.6,"When a player has no possible plays, that player loses the game.");
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledSquare(0.5,0.15,0.05);
		StdDraw.filledCircle(0.45,0.15,0.05);
		StdDraw.filledCircle(0.55,0.15,0.05);
		StdDraw.setPenColor();
		StdDraw.text(0.5,0.15,"Small Board");
		StdDraw.setPenColor(StdDraw.YELLOW);
		StdDraw.filledSquare(0.5,0.3,0.05);
		StdDraw.filledCircle(0.45,0.3,0.05);
		StdDraw.filledCircle(0.55,0.3,0.05);
		StdDraw.setPenColor();
		StdDraw.text(0.5,0.3,"Normal Board");
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledSquare(0.5,0.45,0.05);
		StdDraw.filledCircle(0.45,0.45,0.05);
		StdDraw.filledCircle(0.55,0.45,0.05);
		StdDraw.setPenColor();
		StdDraw.text(0.5,0.45,"Large Board");
		while(!StdDraw.mousePressed()){
		}
		double x=StdDraw.mouseX();
		double y=StdDraw.mouseY();
		while(y>0.5||(y<0.4&&y>0.35)||(y<0.25&&y>0.2)||y<0.1||x>0.6||x<0.4){
			while(!StdDraw.mousePressed()){
			}
			x=StdDraw.mouseX();
			y=StdDraw.mouseY();
			while(StdDraw.mousePressed()){
			}
		}
		int choice=0;
		if(y<=0.5&&y>=0.4){choice=10;}
		if(y<=0.35&&y>=0.25){choice=8;}
		if(y<=0.2&&y>=0.1){choice=6;}
		return choice;
	}
	
	public static void board(int[][] spaces)
	{
		StdDraw.setXscale(0,600);
		StdDraw.setYscale(0,700);
		int N=spaces.length;
		double r=600/(2*N);
		for(int i=0;i<N;i++){
			double y=r+(double)(600*i/N);
			for(int j=0;j<N;j++){
				double x=r+(double)(600*j/N);
				if(spaces[j][i]==1){
					StdDraw.setPenColor();
				}
				else if(j%2==i%2){
					StdDraw.setPenColor(StdDraw.RED);
				}
				else if(j%2!=i%2){
					StdDraw.setPenColor(StdDraw.YELLOW);
				}
				StdDraw.filledSquare(x,y,r);
			}
		}
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledRectangle(300,650,300,51);
		StdDraw.setPenColor();
	}
	
	public static void turnprompt(int turn)
	{
		if(turn%2==1){
			StdDraw.text(300,660,"Please click a space to put a vertical piece on that space and the space below.");
		}
		if(turn%2==0){
			StdDraw.text(300,660,"Please click a space to put a horizontal piece on that space and the space to the right.");
		}
	}
	
	public static int[][] getMove(int turn,int[][] spaces)
	{
		int N=spaces.length;
		int column=0;
		int row=0;
		double x=StdDraw.mouseX();
		double y=StdDraw.mouseY();
		StdDraw.setPenColor(StdDraw.BOOK_RED);
		for(int i=1;i<=N;i++){
			if(x<(600*(double)i/N)){column=i-1;break;}
		}
		for(int i=1;i<=N;i++){
			if(y<(600*(double)i/N)){row=i-1;break;}
		}
		if(turn%2==1){
			while(spaces[column][row]==1||row-1<0||spaces[column][row-1]==1){
				while(!StdDraw.mousePressed()){
				}
				StdDraw.text(300,630,"That is not a valid move, please choose another space.");
				x=StdDraw.mouseX();
				y=StdDraw.mouseY();
				for(int i=1;i<=N;i++){
					if(x<(600*(double)i/N)){column=i-1;break;}
				}
				for(int i=1;i<=N;i++){
					if(y<(600*(double)i/N)){row=i-1;break;}
				}
				while(StdDraw.mousePressed()){
				}
			}
			spaces[column][row]=1;
			spaces[column][row-1]=1;
		}else{
			while(spaces[column][row]==1||column+1>=N||spaces[column+1][row]==1){
				while(!StdDraw.mousePressed()){
				}
				StdDraw.text(300,630,"That is not a valid move, please choose another space.");
				x=StdDraw.mouseX();
				y=StdDraw.mouseY();
				for(int i=1;i<=N;i++){
					if(x<(600*(double)i/N)){column=i-1;break;}
				}
				for(int i=1;i<=N;i++){
					if(y<(600*(double)i/N)){row=i-1;break;}
				}
			}
			spaces[column][row]=1;
			spaces[column+1][row]=1;
		}
		return spaces;
	}
	
	public static boolean testWin(int turn,int[][] spaces)
	{
		if(turn%2==1){
			for(int i=spaces.length-1;i>=0;i--){
				if(i==0){return false;}
				for(int j=spaces.length-1;j>=0;j--){
					if(spaces[j][i]==0){
						if(spaces[j][i-1]==0){return true;}
					}
				}
			}
		}
		if(turn%2==0){
			for(int j=0;j<spaces.length;j++){
				if(j==spaces.length-1){return false;}
				for(int i=0;i<spaces.length;i++){
					if(spaces[j][i]==0){
						if(spaces[j+1][i]==0){return true;}
					}
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args)
	{
		StdDraw.setCanvasSize(600,700);
		int N=instructions();
		while(StdDraw.mousePressed()){
		}
		int[][] spaces=new int[N][N];
		int turn=1;
		boolean game=true;
		StdDraw.clear(StdDraw.BLACK);
		while(turn<=N*N/2+1){
			board(spaces);
			turnprompt(turn);
			while(!StdDraw.mousePressed()){
			}
			spaces=getMove(turn,spaces);
			while(StdDraw.mousePressed()){
			}
			turn++;
			game=testWin(turn,spaces);
			if(game==false){break;}
		}
		board(spaces);
		if(turn%2==1){
			StdDraw.text(300,675,"Player 1 cannot play! Player 2 wins!!!");
		}
		if(turn%2==0){
			StdDraw.text(300,675,"Player 2 cannot play! Player 1 wins!!!");
		}
		StdDraw.text(300,650,"Press space to play again.");
		StdDraw.text(300,625,"Press any other key to quit.");
		while(!StdDraw.hasNextKeyTyped()){
			//wait for key press
		}
		char j=StdDraw.nextKeyTyped();
		if(j==' '){
			main(args);
		}else{
			System.exit(0);
		}
	}

}
