public class jsonChecker {
    private boolean jsonEnd = false;
    private String json ="";

    public void addString(String s){
        json += tabKiller(s);
        checkJson();
    }

    private void checkJson(){
        int a = 0;
        for(int i = 0; i < json.length(); i++){
            if(json.charAt(i) == '{') a++;
            else if(json.charAt(i) == '}'){
                if(a == 0) throw new IllegalArgumentException(" Wrong Json Format! ");
                a--;
            }
        }
        if((a == 0) && (json.length() > 0)) jsonEnd = true;
        else jsonEnd = false;
    }

    public boolean endCheck(){
        return jsonEnd;
    }
    public String getJson(){
        return json;
    }
    public static String tabKiller(String s){
        int l = 0, r = s.length() - 1;
        while((l <= r) && (s.charAt(l) == ' ')){
            l++;
        }
        while((l <= r) && (s.charAt(r) == ' ')){
            r--;
        }
        if(l <= r) return s.substring(l, r + 1);
        return "";
    }
}
