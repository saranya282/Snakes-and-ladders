public class Board {
    private int start;
    private int  end;
    private int Size;
    public Board(int Size){
        this.start= 0;
        this.Size= Size;
        this.end=start+Size;   
    }
    public int getsize(){
        return Size;
    }
    public int getStart(){
        return start;
    }
    public int getEnd(){
        return end;
    }
}
