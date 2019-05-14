package Graph_DS;

public class Exceptions extends RuntimeException {
    public static final String IDS = " _IDS_ ";
    public static final String DATAS = " _Datas_ ";
    public static final String NOT_FOUND = "Not Found this _ID_ : ";
    public static final String BAD_LENGTH = IDS+"length !="+DATAS;
    public Exceptions(String message){
        super(message);
    }

}
