import java.util.*;
import java.io.*;
import java.lang.*;

class Player {
	
	String name;
	int x;
	Chessman[] pieces = new Chessman[5];
	Player(String name,int x){
		this.name = name;
		this.x = x;
	}
	
	Chessman[] setChars(String[] chars){
		for(int i=0;i<5;i++){
			chars[i] = this.name + "-" + chars[i];
			this.pieces[i] = new Chessman(chars[i],this.x,i);
		}
		return this.pieces;
	}
	
	static int[] find(String piece,Chessman[][] arr){
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				if(arr[i][j].name.equals(piece)){
					return new int[] {i,j};
				}
			}
		}
		return new int[] {0,0};
	}
	static void setIndex(Chessman[][] arr,int[] ind,String piece){
		arr[ind[0]][ind[1]].name = piece;
		
	}
	void move(String piece,String dir,Chessman[][] arr){
		int[] index = find(piece,arr);
		if(piece.substring(0,1).equals("A")){
			String p = piece.substring(2,4);
			if(dir.equals("F")){
				arr[index[0]][index[1]].name = "-";
				--index[0];
				setIndex(arr,index,piece);
			}
			if(dir.equals("D")){
				arr[index[0]][index[1]].name = "-";
				++index[0];
				setIndex(arr,index,piece);
			}
			if(dir.equals("R")){
				arr[index[0]][index[1]].name = "-";
				++index[1];
				setIndex(arr,index,piece);
			}
			if(dir.equals("L")){
				arr[index[0]][index[1]].name = "-";
				--index[1];
				setIndex(arr,index,piece);
			}

		}else{
			String p = piece.substring(2,4);
			if(dir.equals("F")){
				arr[index[0]][index[1]].name = "-";
				++index[0];
				setIndex(arr,index,piece);
			}
			if(dir.equals("D")){
				arr[index[0]][index[1]].name = "-";
				--index[0];
				setIndex(arr,index,piece);
			}
			if(dir.equals("R")){
				arr[index[0]][index[1]].name = "-";
				--index[1];
				setIndex(arr,index,piece);
			}
			if(dir.equals("L")){
				arr[index[0]][index[1]].name = "-";
				++index[1];
				setIndex(arr,index,piece);
			}
		}
		
	}
	
	
}

class Chessman{
	
	String name;
	int x,y;
	Chessman(String name,int x,int y){
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	
}

public class chess{
	
	public static void main(String[] args){
		
		Scanner scan = new Scanner(System.in);
		
		Player p1 = new Player("A",4);
		Player p2 = new Player("B",0);
		
		Chessman[][] grid = new Chessman[5][5];
		initializeGrid(grid);
		
		System.out.print("Player 1 input: ");
		String[] inp1 = scan.nextLine().split(",");
		grid[4] = p1.setChars(inp1);
		printGrid(grid);
		
		System.out.print("Player 2 input: ");
		String[] inp2 = scan.nextLine().split(",");
		grid[0] = p2.setChars(inp2);
		printGrid(grid);
		
		
		
		int n=0;
		while(scan.hasNext()){
			if(n%2 == 0){
				try{
					String temp = scan.nextLine();
					System.out.println("Player A's Move: " + temp);
					String piece = "A-" + temp.substring(0,2);
					String fun = temp.substring(3);
					p1.move(piece,fun,grid);
					printGrid(grid);
				}catch(Exception e){
					System.out.println("INVALID INPUT");
					//System.exit();
				}
				
			}else{
				
				try{
					String temp = scan.nextLine();
					System.out.println("Player B's Move: " + temp);
					String piece = "B-" + temp.substring(0,2);
					String fun = temp.substring(3);
					p1.move(piece,fun,grid);
					printGrid(grid);
				}catch(Exception e){
					System.out.println("INVALID INPUT");
					//System.exit();
				}
				
			}
			
			n++;
		}
		
		
	}
	
	static void printGrid(Chessman[][] arr){
		arrangeGrid(arr);
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				if(arr[i][j].name.equals("-")){
					System.out.print("   " + arr[i][j].name + "   ");
				}else{
					System.out.print(arr[i][j].name + "   ");
				}
			}
			System.out.println();
		}
		
	}
	
	static void arrangeGrid(Chessman[][] arr){
		ArrayList<Chessman> temp = new ArrayList<>();
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				temp.add(arr[i][j]);
			}
		}
		for(int k=0;k<25;k++){
			arr[temp.get(k).x][temp.get(k).y] = temp.get(k);
		}
	}
	
	
	
	static Chessman[][] initializeGrid(Chessman[][] arr){
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				arr[i][j] = new Chessman("-",i,j);
			}
		}
		return arr;
	}
}
