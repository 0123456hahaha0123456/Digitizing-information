public enum FOODS {
    SOUP(1), RICE(2), CAKE(3), CANDY(4);
    private int code;
    FOODS(int code){
        this.code = code;
    }

    public static FOODS getFoodbyCode(int code){
        for(FOODS tmp : FOODS.values()){
            if (tmp.code == code) return tmp;
        }
        return null;
    }
}