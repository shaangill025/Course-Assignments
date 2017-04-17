package control;

import java.util.LinkedList;

import Factory.ElevatorFactory;
import enums.ElevatorDirection;
import enums.ElevatorStatus;

public class Elevator implements ElevatorFactory {
  private int currentFloor;
  private LinkedList<Integer> destinationFloors;

  public Elevator(Integer currentFloor) {
    this.currentFloor = currentFloor;
    this.destinationFloors = new LinkedList<Integer>();
  }

  public int nextDestionation(){
    return this.destinationFloors.peek();
  }

  public int currentFloor(){
    return this.currentFloor;
  }

  public void popDestination(){
    this.destinationFloors.remove();
  }
  @Override
  public void addNewDestinatoin(int destination) {
    this.destinationFloors.add(destination);
  }

  @Override
  public void moveUp() {
    currentFloor++;
  }

  @Override
  public void moveDown() {
    currentFloor--;
  }

  @Override
  public ElevatorDirection direction() {
    if (destinationFloors.size() > 0){
      if (currentFloor < destinationFloors.peek()){
        return ElevatorDirection.ELEVATOR_UP;
      } else if (currentFloor > destinationFloors.peek()) {
        return ElevatorDirection.ELEVATOR_DOWN;
      }
    }
    return ElevatorDirection.ELEVATOR_HOLD;
  }

  @Override
  public ElevatorStatus status() {
    return (destinationFloors.size() > 0)?ElevatorStatus.ELEVATOR_OCCUPIED:ElevatorStatus.ELEVATOR_EMPTY;
  }
}