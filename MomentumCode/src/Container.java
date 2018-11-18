public abstract class Container {
	private String name;

	public Container(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public abstract String getDataType();
}