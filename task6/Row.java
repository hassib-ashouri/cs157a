public class Row
{
    private int product_id, option_id;
    private String name;

    public Row(int id, int option, String name) {
        super();
        this.product_id = id;
        this.option_id = option;
        this.name = name;
    }

	public int getProduct_id() {
		return product_id;
	}

	public int getOption_id() {
		return option_id;
	}

	public String getName() {
		return name;
	}
    

//    public Integer prdouct_id() {return this.product_id;}
//    public Integer getOption_id() {return this.option_id;}
//    public String getName() {return this.name;}
}