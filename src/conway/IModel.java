package conway;

public interface IModel {
	public void tick();
	public boolean at(int x, int y);
	public int x();
	public int y();
}
