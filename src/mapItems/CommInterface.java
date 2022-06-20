package mapItems;

public interface CommInterface {
	public void transmit(CommInterface reciever, String data);
	public void recieve(CommInterface transmitter, String data);
}
