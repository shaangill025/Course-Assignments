package Factory;

public interface ElevatorControlSystemFactory {
	  public void pickUp(int pickUpFloor);
	  public void destination(int destinationFloor);
	  public void step();

}