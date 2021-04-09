# Java Based Traffic Simulator
## Program Working Document

### Specification
the client needs a program that can simulate traffic in city areas; needing to simulate different vechicle types with traffic light, roads and intersections. The user can enter input into the program to customized the city, and the program will display the objects and their status to the user. This version of the program will not display the UI, but the user can enter input for the customization of their city. The objects that are available for the users are cars, traffic lights and single lane roads. the user can choose how many cars, traffic lights and roads will the city has. This version of the simulator will simulate cars moving along single lane roads connected to each other, and move to the end of that road.

### Decomposition
The problem can be broken into operate objects that interact with each other to achieve desired behaviour.
These objects include;
### Car
Car class with represent a medium size vehicle. The class has the following attributes;
- *id* - an unique indentifier that will differentiate each car.
- *length* - the length of the car, the space the car occupies longways.
- *breadth* - the width of the car, the space the car occupies widthways, half the car's length. 
- *speed* - how far the car moves for each simulation turn.
- *position* - where the car will be place on the road. 
- *currentRoad* - the road the car is currently on.

