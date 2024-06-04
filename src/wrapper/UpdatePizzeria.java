package wrapper;

public interface UpdatePizzeria {
	void updateOptionSetName(String pizzeriaName, String optionSetname, String newName)throws Exception;
	void updateBasePrice(String pizzeriaName, double newPrice) throws Exception;
	void updateOptionPrice(String pizzeriaName, String optionName, String Option, int newPrice) throws Exception;
}
